package capstone_design_1.ssmps_backend.dto;

import capstone_design_1.ssmps_backend.domain.Item;
import capstone_design_1.ssmps_backend.domain.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemResponse {
    private Long id;
    private String name;
    private String type;
    private int price;
    private String image;
    private String barcode;
    private int quantity;
    private Location location;

    public ItemResponse(Item item) {
        this.id = item.getId();
        this.name = item.getItem().getName();
        this.type = item.getItem().getType();
        this.price = item.getItem().getPrice();
        this.image = item.getItem().getImage();
        this.barcode = item.getItem().getBarcode();
        this.quantity = item.getQuantity();
        this.location = item.getLocation();
    }
//    public ItemResponse(Item item) {
//        id =
//    }
}