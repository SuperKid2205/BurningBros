package my.vehicle.management.sapmle.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import my.vehicle.management.sapmle.entity.Maintenance;
import my.vehicle.management.sapmle.entity.User;
import my.vehicle.management.sapmle.service.MaintenanceService;
import my.vehicle.management.sapmle.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private UserService userService;
    
    @GetMapping("/maintenances")
    public ResponseEntity<List<Maintenance>> getMaintenances() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User loginUser = userService.getUserLogin(username);
        
        List<Maintenance> maintenances = maintenanceService.getMaintenanceRecordsByVehicle(loginUser.getId());
        return ResponseEntity.ok(maintenances);
    }
    

    @PutMapping("/maintenances")
    public ResponseEntity<Maintenance> updateMaintenance(@RequestBody Maintenance theMaintenance) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User loginUser = userService.getUserLogin(username);
        
        Maintenance savedVehicle = maintenanceService.updateMaintenanceRecord(loginUser.getId(), theMaintenance);
        return ResponseEntity.ok(savedVehicle);
    }
    
    @PostMapping("/maintenances")
    public ResponseEntity<Maintenance> addMaintenance(@RequestBody Maintenance theMaintenance) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User loginUser = userService.getUserLogin(username);
        
        Maintenance savedVehicle = maintenanceService.addMaintenanceRecord(loginUser.getId(), theMaintenance);
        return ResponseEntity.ok(savedVehicle);
    }

}
