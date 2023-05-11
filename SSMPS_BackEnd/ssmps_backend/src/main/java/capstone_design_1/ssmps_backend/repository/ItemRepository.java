package capstone_design_1.ssmps_backend.repository;


import capstone_design_1.ssmps_backend.domain.CenterItem;
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
    public Optional<CenterItem> findItemByBarcode(String barcode){
        List<CenterItem> findItems = em.createQuery("select i from CenterItem i where i.barcode = :barcode", CenterItem.class)
                .setParameter("barcode", barcode)
                .getResultList();
        return findItems.stream().findFirst();
    }
    public Item addNewItem(Item item){
        em.persist(item);
        return item;
    }
    public List<CenterItem> findAllList() {
        List findItemList = em.createQuery("select i from Item i")
                .getResultList();
        return findItemList;
    }

    public Item findItemById(Long id){
        return em.find(Item.class, id);
    }

    public List<CenterItem> findItemByName(String name) {
        List findItemList = em.createQuery("select i from CenterItem i where i.name like :name")
                .setParameter("name", "%" + name + "%")
                .getResultList();
        return findItemList;
    }

    public Item deleteItem(Item deleteItem) {
        em.remove(deleteItem);
        return deleteItem;
    }
}