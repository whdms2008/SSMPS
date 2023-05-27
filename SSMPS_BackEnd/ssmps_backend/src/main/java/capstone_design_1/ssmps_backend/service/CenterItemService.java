package capstone_design_1.ssmps_backend.service;


import capstone_design_1.ssmps_backend.domain.CenterItem;
import capstone_design_1.ssmps_backend.repository.CenterItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CenterItemService {
    private final CenterItemRepository centerItemRepository;

    public List<CenterItem> findAllCenterItem(){
        return centerItemRepository.findAllCenterItem();
    }

    public List<CenterItem> findCenterItemByName(String itemName) {
        return centerItemRepository.findCenterItemByName(itemName);
    }

    public CenterItem findCenterItem(Long id){
        return  centerItemRepository.findCenterItem(id);
    }
}
