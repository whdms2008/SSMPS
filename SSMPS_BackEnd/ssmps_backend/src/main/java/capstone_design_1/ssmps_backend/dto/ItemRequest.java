package capstone_design_1.ssmps_backend.dto;


import capstone_design_1.ssmps_backend.dto.store.AddStoreRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemRequest {
    private Long id;
    private AddStoreRequest store;
    private String name;
    private String type;
    private int price;
    private int quantity;
    private String image;
    private String location;
    private String barcode;

    public ItemRequest(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}