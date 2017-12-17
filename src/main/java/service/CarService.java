package service;

import model.repository.CarRepository;
import model.vehicles.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serwis realizujący dodawanie/usuwanie/aktualizację samochodów w systemie
 */
@Service
public class CarService {

    @Autowired
    private CarRepository dao;

    @Autowired
    private EventService eventService;

    public List<Car> getAllCars() {

        return dao.findAll();
    }

    public Car getCarById(Integer id) {
        return dao.findCarById(id);
    }

    public void addCar(Car car) {
        dao.save(car);
    }

    @org.springframework.transaction.annotation.Transactional
    public void removeCar(Integer id) {
        Car carToDelete = dao.findCarById(id);
        eventService.deleteEventsByCarId(carToDelete.getId());
        dao.delete(carToDelete);
    }
}
