package capstone_design_1.ssmps_backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Data
public class LocationResponse {
    private Long id;
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private List<ItemResponse> itemList;

    public LocationResponse(Long id, float startX, float startY, float endX, float endY, List<ItemResponse> itemList) {
        this.id = id;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.itemList = itemList;
    }
}