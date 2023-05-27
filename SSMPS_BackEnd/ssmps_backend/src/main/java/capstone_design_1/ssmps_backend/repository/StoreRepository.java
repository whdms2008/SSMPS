package capstone_design_1.ssmps_backend.repository;


import capstone_design_1.ssmps_backend.domain.Location;
import capstone_design_1.ssmps_backend.domain.Manager;
import capstone_design_1.ssmps_backend.domain.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class StoreRepository {

    private final EntityManager em;
    public List<Location> findLocationList(Long id) {
        return em.createQuery("select l from Location l where store.id = :store")
                .setParameter("store", id)
                .getResultList();
    }

    public List<Store> findAllStoreById(Manager manager) {
        return em.createQuery("select s from Store s where s.manager = :manager")
                .setParameter("manager", manager)
                .getResultList();
    }

    public Store findStore(Long id) {
        return em.find(Store.class, id);
    }

    public List<Store> findAllStore(){
        return em.createQuery("select s from Store s").getResultList();
    }

    public Store registStoreLocation(Store store){
        for(Location l : store.getLocationList()){
            em.persist(l); // Location 저장
        }
        return store;
    }

    public void saveLocation(Location l) {
        em.persist(l);
    }
}
