package capstone_design_1.ssmps_backend.controller;

import capstone_design_1.ssmps_backend.domain.CenterItem;
import capstone_design_1.ssmps_backend.dto.CenterItemResponse;
import capstone_design_1.ssmps_backend.service.CenterItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@RestController
public class CenterItemController {
    private final CenterItemService centerItemService;

    @GetMapping("api/centerItemList")
    public ResponseEntity<Object> findAllCenterItem(){
        List<CenterItem> findList = centerItemService.findAllCenterItem().stream()
                .map(ci -> new CenterItem(ci.getId(), ci.getName(), ci.getType(), ci.getPrice(), ci.getImage(), ci.getBarcode()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(findList);
    }

    @GetMapping("api/centerItem/{name}")
    public ResponseEntity<Object> findCenterItemByName(@PathVariable(name = "name") String itemName){
        List<CenterItemResponse> findList = centerItemService.findCenterItemByName(itemName).stream()
                .map(ci -> new CenterItemResponse(ci.getId(), ci.getName(), ci.getType(), ci.getPrice(), ci.getImage(), ci.getBarcode()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(findList);
    }
}


