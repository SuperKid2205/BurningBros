package my.vehicle.management.sapmle.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import my.vehicle.management.sapmle.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
