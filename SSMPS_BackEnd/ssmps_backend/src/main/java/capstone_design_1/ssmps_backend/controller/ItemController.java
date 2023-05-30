package capstone_design_1.ssmps_backend.controller;

import capstone_design_1.ssmps_backend.domain.CenterItem;
import capstone_design_1.ssmps_backend.domain.Item;
import capstone_design_1.ssmps_backend.domain.Store;
import capstone_design_1.ssmps_backend.dto.ItemRequest;
import capstone_design_1.ssmps_backend.dto.CenterItemResponse;
import capstone_design_1.ssmps_backend.dto.ItemResponse;
import capstone_design_1.ssmps_backend.dto.store.AddStoreRequest;
import capstone_design_1.ssmps_backend.dto.store.StoreRequest;
import capstone_design_1.ssmps_backend.service.CenterItemService;
import capstone_design_1.ssmps_backend.service.ItemService;
import capstone_design_1.ssmps_backend.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ItemController {
    private final ItemService itemService;
    private final CenterItemService centerItemService;
    private final StoreService storeService;

    // 바코드 촬영 후 물건 정보 요청
    @GetMapping("api/item/{barcode}")
    public ResponseEntity<Object> getItemByBarcode(@PathVariable String barcode){
        try{
            CenterItem findItem = itemService.getItemByBarcode(barcode);
            return ResponseEntity.ok(findItem);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 재고 추가 (센터에서 우리 매장에 추가)
    @PostMapping("api/item")
    public ResponseEntity<Object> addItem(@RequestParam(name = "store") Long storeId, @RequestParam(name = "item") Long itemId){
        CenterItem centerItem = centerItemService.findCenterItem(itemId);
        Store nowStore = storeService.findStoreById(storeId);
        Item item = new Item(null, centerItem, nowStore, 0);
        Item addedItem = itemService.addItem(item);
        ItemResponse resultItem = new ItemResponse(addedItem);
        return ResponseEntity.ok(resultItem);
    }

    // 매장내 물건 전체 리스트 요청
    @GetMapping("api/itemList")
    public ResponseEntity<Object> findAllItem(@RequestParam Long storeId){
        Store findStore = storeService.findStoreById(storeId);
        List<Item> itemList = itemService.getItemList(findStore);
        List<ItemResponse> responseList = itemList.stream()
                .map(i -> new ItemResponse(i))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    // 물건 조회 (검색. 추가 요청 때)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   )
    @GetMapping("api/item/{name}")
    public ResponseEntity<Object> findItemByName(@PathVariable String name, @RequestParam("store_id") Long storeId){
        Store findStore = storeService.findStoreById(storeId);
        List<Item> findItemList = itemService.getItemByName(name, findStore);
        List<ItemResponse> resultList = findItemList.stream()
                .map(i -> new ItemResponse(i))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }

    // 재고 수량 변경
    @PutMapping("api/item")
    public ResponseEntity<Object> updateItemQuantity(@RequestBody ItemRequest updateItem){
        Item findItem = itemService.getItemById(updateItem.getId());
        Item updatedItem = itemService.updateQuantity(findItem, updateItem.getQuantity());
        ItemResponse resultItem = new ItemResponse(updatedItem);
        return ResponseEntity.ok(resultItem);
    }

    @DeleteMapping("api/item")
    public ResponseEntity<Object> deleteItem(@RequestBody ItemRequest deleteItem){
        Item findItem = itemService.getItemById(deleteItem.getId());
        Item deleteditem = itemService.deleteItem(findItem);
        ItemResponse resultItem = new ItemResponse(deleteditem);
        return ResponseEntity.ok(resultItem);
    }


}