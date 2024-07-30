package my.vehicle.management.sapmle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.vehicle.management.sapmle.dao.MaintenanceRepository;
import my.vehicle.management.sapmle.dao.UserRepository;
import my.vehicle.management.sapmle.dao.VehicleRepository;
import my.vehicle.management.sapmle.entity.Maintenance;
import my.vehicle.management.sapmle.entity.User;
import my.vehicle.management.sapmle.entity.Vehicle;

import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRecordRepository;

    @Autowired
    private VehicleRepository vehicleRepository;
    
    public List<Maintenance> getMaintenanceRecordsByVehicle(Long vehicleId) {
        return maintenanceRecordRepository.findByVehicleId(vehicleId);
    }
    
    public Maintenance addMaintenanceRecord(Long vehicleId, Maintenance record) {
      Vehicle vehicle = vehicleRepository.findById(vehicleId)
              .orElseThrow(() -> new RuntimeException("Vehicle not found"));
      record.setVehicle(vehicle);
      return maintenanceRecordRepository.save(record);
    }
    

    
    public Maintenance updateMaintenanceRecord(Long vehicleId, Maintenance record) {

        // Check owner
        Vehicle theVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        
        Maintenance theMaintenance = maintenanceRecordRepository.findById(record.getId())
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));
        
        if( !theMaintenance.getVehicle().getId().equals(theVehicle.getId())) {
            throw new RuntimeException("Your account don't have permission for this maintenance.");
        }
        
        if (record.getTaskTitle() != null) {
            theMaintenance.setTaskTitle(record.getTaskTitle());
        }
        if (record.getServiceDate() != null) {
            theMaintenance.setServiceDate(record.getServiceDate());
        }
        theMaintenance.setCost(record.getCost());
        if (record.getDescription() != null) {
            theMaintenance.setDescription(record.getDescription());
        }
        
        return maintenanceRecordRepository.save(theMaintenance);
    }

}
