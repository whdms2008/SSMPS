package capstone_design_1.ssmps_backend.repository;


import capstone_design_1.ssmps_backend.domain.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Repository
public class ItemRepository {
    private final EntityManager em;

    public Optional<Item> findItemByBarcode(String barcode){
        List<Item> findItems = em.createQuery("select i from Item i where i.barcode = :barcode", Item.class)
                .setParameter("barcode", barcode)
                .getResultList();
        return findItems.stream().findFirst();
    }
    public Item addNewItem(Item item){
        em.persist(item);
        return item;
    }
}