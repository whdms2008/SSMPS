package capstone_design_1.ssmps_backend.repository;


import capstone_design_1.ssmps_backend.domain.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class LocationRepository {

    private final EntityManager em;
    public List<Location> findLocationList(Long id) {
        return em.createQuery("select l from Location l where store.id = :store")
                .setParameter("store", id)
                .getResultList();
    }
}
