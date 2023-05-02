package capstone_design_1.ssmps_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddItemRequest {
    private AddStoreRequest store;
    private String name;
    private String type;
    private int price;
    private int quantity;
    private String image;
    private String location;
    private String barcode;
}
