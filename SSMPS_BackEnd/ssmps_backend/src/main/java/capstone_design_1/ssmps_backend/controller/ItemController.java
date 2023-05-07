package capstone_design_1.ssmps_backend.controller;

import capstone_design_1.ssmps_backend.domain.CenterItem;
import capstone_design_1.ssmps_backend.domain.Item;
import capstone_design_1.ssmps_backend.dto.ItemRequest;
import capstone_design_1.ssmps_backend.dto.CenterItemResponse;
import capstone_design_1.ssmps_backend.dto.ItemResponse;
import capstone_design_1.ssmps_backend.service.ItemService;
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
    public ResponseEntity<Object> addItem(@RequestBody ItemRequest newItem){
        CenterItem newCenterItem = new CenterItem(newItem.getId(), newItem.getName(), newItem.getType(),
                newItem.getPrice(), newItem.getImage(), newItem.getBarcode());
        Item item = new Item(null, newCenterItem, null, newItem.getQuantity(), newItem.getLocation());
        Item addItem = itemService.addItem(item);
        ItemResponse resultItem = new ItemResponse(addItem);
        return ResponseEntity.ok(resultItem);
    }

//    // 물건 전체 리스트 요청
//    @GetMapping("api/items")
//    public ResponseEntity<Object> getItemList(){
//        List<CenterItem> itemList = itemService.getItemList();
//        itemList.stream()
//                .map(i -> new ItemResponse(i))
//        return ResponseEntity.ok(itemService.getItemList());
//    }

    // 물건 조회 (검색. 추가 요청 때)
    @GetMapping("api/item/search/{name}")
    public ResponseEntity<Object> findItemByName(@PathVariable String name){
        List<CenterItem> findItemList = itemService.getItemByName(name);
        List<CenterItemResponse> resultList = findItemList.stream()
                .map(i -> new CenterItemResponse(i))
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