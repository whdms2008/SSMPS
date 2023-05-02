package capstone_design_1.ssmps_backend.controller;

import capstone_design_1.ssmps_backend.domain.Item;
import capstone_design_1.ssmps_backend.domain.Store;
import capstone_design_1.ssmps_backend.dto.AddItemRequest;
import capstone_design_1.ssmps_backend.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ItemController {
    private final ItemService itemService;

    // 바코드 촬영 후 물건 정보 요청
    @GetMapping("api/item/{barcode}")
    public ResponseEntity<Object> getItemByBarcode(@PathVariable(name = "barcode") String barcode){
        try{
            Item findItem = itemService.getItemByBarcode(barcode);
            return ResponseEntity.ok(findItem);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        //return new ItemResponse(findItem.getName(), findItem.getPrice());
    }

    // 새로운 제품 추가 or 재고 수량 추가
    @PostMapping("api/item")
    public ResponseEntity<Object> addItem(@RequestBody AddItemRequest newItem){
        Store store = new Store(0L, newItem.getStore().getName(), newItem.getStore().getAddress(), null);
        Item item = new Item(0L, store, newItem.getName(), newItem.getType(), newItem.getPrice(), newItem.getQuantity(),
                newItem.getImage(), newItem.getLocation(), newItem.getBarcode());
        return ResponseEntity.ok(itemService.addItem(item));
    }

    // 물건 전체 리스트
    @GetMapping("api/items")
    public ResponseEntity<Object> getItemList(){
        itemService.getItemList();
        return null;
    }



    // 테스트용
    @PostMapping("api/manager/test")
    public String test(){
        return "good";
    }
}