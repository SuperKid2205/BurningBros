package my.vehicle.management.sapmle.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import my.vehicle.management.sapmle.entity.Maintenance;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByVehicleId(Long vehicleId);
}
