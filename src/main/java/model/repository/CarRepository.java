package model.repository;

import model.vehicles.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByTypeAndMake(String type, String make);

    Car findCarById(Integer id);

    boolean removeCarById(Integer id);
}
