package capstone_design_1.ssmps_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
public class LocationRequest {
    private Long id;
    private List<ItemRequest> itemList;
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    private float centerX;
    private float centerY;

}
