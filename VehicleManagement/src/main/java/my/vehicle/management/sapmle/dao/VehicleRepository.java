package my.vehicle.management.sapmle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import my.vehicle.management.sapmle.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByOwnerId(Long ownerId);
}
