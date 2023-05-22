package capstone_design_1.ssmps_backend.controller;

import capstone_design_1.ssmps_backend.domain.Location;
import capstone_design_1.ssmps_backend.dto.LocationResponse;
import capstone_design_1.ssmps_backend.dto.RequestLocation;
import capstone_design_1.ssmps_backend.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/api/location")
    public ResponseEntity<Object> getLocationList(@RequestParam Long storeId){
        List<Location> locationList = locationService.getLocationList(storeId);
//        locationList.stream()
//                .map(l -> new LocationResponse(l.getId(), l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY(), l.getItemList())))
        return ResponseEntity.ok(null);
    }

    @PostMapping("/api/location")
    public ResponseEntity<Object> addLocation(@RequestBody RequestLocation location){
//        locationService.addLocation();

        return ResponseEntity.ok(null);
    }


}