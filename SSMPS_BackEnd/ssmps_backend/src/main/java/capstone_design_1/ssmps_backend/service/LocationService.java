package capstone_design_1.ssmps_backend.service;

import capstone_design_1.ssmps_backend.domain.Location;
import capstone_design_1.ssmps_backend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocationService {
    private final LocationRepository locationRepository;
    public List<Location> getLocationList(Long id) {
        return locationRepository.findLocationList(id);
    }
}
