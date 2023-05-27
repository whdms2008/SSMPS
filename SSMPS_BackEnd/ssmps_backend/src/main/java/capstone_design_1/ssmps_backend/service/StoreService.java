package capstone_design_1.ssmps_backend.service;

import capstone_design_1.ssmps_backend.domain.Location;
import capstone_design_1.ssmps_backend.domain.Manager;
import capstone_design_1.ssmps_backend.domain.Store;
import capstone_design_1.ssmps_backend.repository.LoginRepository;
import capstone_design_1.ssmps_backend.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final LoginRepository loginRepository;
    public List<Location> getLocationList(Long id) {
        return storeRepository.findLocationList(id);
    }

    public List<Store> findAllStoreById(Long managerId) {
        Manager findManager = loginRepository.findManagerById(managerId);
        return storeRepository.findAllStoreById(findManager);
    }

    public Store findStoreById(Long id){
        return storeRepository.findStore(id);
    }

//    @Transactional
//    public Store updateStore(Store store){
//        Store findStore = storeRepository.findStore(store.getId());
//        findStore = store;
//    }

    @Transactional
    public List<Location> registStoreLocation(Long storeId, List<Location> locationList){
        Store changeStore = storeRepository.findStore(storeId);
        for(Location l : locationList){
            storeRepository.saveLocation(l);
        }
        changeStore.setLocationList(locationList);
        return locationList;
    }

    public List<Store> findAllStore(){
        return storeRepository.findAllStore();
    }
}
