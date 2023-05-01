package capstone_design_1.ssmps_backend.repository;

import capstone_design_1.ssmps_backend.domain.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LoginRepository {
    private final EntityManager em;
    public Manager join(Manager manager){
        em.persist(manager);
        return manager;
    }
    public Manager getManager(String id, String password){
        return null;
    }
}