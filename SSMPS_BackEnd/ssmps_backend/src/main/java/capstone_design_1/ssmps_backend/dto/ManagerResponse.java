package capstone_design_1.ssmps_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ManagerResponse {
    private String id;
    private List<StoreResponse> storeList;
}
