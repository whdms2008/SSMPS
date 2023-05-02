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
    @JoinColumn(name = "store_id")
    private Store store;
    private String name;
    private String type;
    private int price;
    private int quantity;
    @Column(columnDefinition = "LONGBLOB")
    private String image;
    private String location;
    private String barcode;

    public void addQuantity(){
        quantity++;
    }

    // 같은 물건인지 체크
    public boolean checkRedundancy(Item item){
       if(this.store.equals(item.getStore()) && this.name.equals(item.getName()) && this.price == item.getPrice() &&
           this.image.equals(item.getImage()) && this.location.equals(item.getLocation()) && this.barcode.equals(item.getBarcode())){
           return true;
       }
       return false;
    }
}

