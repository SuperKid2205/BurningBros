package my.vehicle.management.sapmle.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.vehicle.management.sapmle.entity.User;
import my.vehicle.management.sapmle.entity.Vehicle;
import my.vehicle.management.sapmle.service.UserService;
import my.vehicle.management.sapmle.service.VehicleService;


@RestController
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getVehicles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User loginUser = userService.getUserLogin(username);
        
        List<Vehicle> vehicles = vehicleService.getVehiclesByOwner(loginUser.getId());
        return ResponseEntity.ok(vehicles);
    }
    

    @PutMapping("/vehicles")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle theVehicle) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User loginUser = userService.getUserLogin(username);
        
        Vehicle savedVehicle = vehicleService.updateVehicle(loginUser, theVehicle);
        return ResponseEntity.ok(savedVehicle);
    }
    
    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle theVehicle) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User loginUser = userService.getUserLogin(username);
        
        Vehicle savedVehicle = vehicleService.addVehicle(loginUser.getId(), theVehicle);
        return ResponseEntity.ok(savedVehicle);
    }
    


}
