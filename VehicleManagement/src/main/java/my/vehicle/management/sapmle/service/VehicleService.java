package my.vehicle.management.sapmle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.vehicle.management.sapmle.dao.UserRepository;
import my.vehicle.management.sapmle.dao.VehicleRepository;
import my.vehicle.management.sapmle.entity.User;
import my.vehicle.management.sapmle.entity.Vehicle;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public Vehicle addVehicle(Long theId, Vehicle theVehicle) {
        User owner = userRepository.findById(theId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        theVehicle.setOwner(owner);
        return vehicleRepository.save(theVehicle);
    }
    
    public Vehicle updateVehicle(User loginUser, Vehicle requestVehicle) {

        // Check owner
        Vehicle theVehicle = vehicleRepository.findById(requestVehicle.getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        
        if(!"ROLE_ADMIN".equals(loginUser.getRole())
                && !theVehicle.getOwner().getId().equals(loginUser.getId())) {
            throw new RuntimeException("Your account don't have permission for this vehicle.");
        }
        
        if (requestVehicle.getModel() != null) {
            theVehicle.setModel(requestVehicle.getModel());
        }
        if (requestVehicle.getYear() != null) {
            theVehicle.setYear(requestVehicle.getYear());
        }
        if (requestVehicle.getType() != null) {
            theVehicle.setType(requestVehicle.getType());
        }
        
        return vehicleRepository.save(theVehicle);
    }
    
    public List<Vehicle> getVehiclesByOwner(Long ownerId) {
        return vehicleRepository.findByOwnerId(ownerId);
    }

}
