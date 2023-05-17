package capstone_design_1.ssmps_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_item_id")
    private CenterItem item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    private int quantity;
    private String location;
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    // 같은 물건인지 체크
    @Override
    public boolean equals(Object obj) {
        if(this.item.getId() == ((Item)obj).getItem().getId()){
            return true;
        }
        return false;
    }
}