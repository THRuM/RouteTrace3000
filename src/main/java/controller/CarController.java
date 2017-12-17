package controller;

import model.vehicles.Car;
import model.vehicles.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CarService;
import service.EventService;

import javax.validation.Valid;

/**
 * Created by user on 27.07.17.
 * Kontroler do obsługi zdarzeń(towrzenie/usuwanie/edycja) związannych z pojazdami
 */
@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "allCars", method = RequestMethod.GET)
    public String allCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "allCars";
    }

    @RequestMapping(value = "addCar", method = RequestMethod.GET)
    public String addCarGET(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("carTypes", VehicleType.values());
        return "addCar";
    }

    //Po dodaniu powrót na stronę ze wszystkimi pojazdami
    @RequestMapping(value = "addCar", method = RequestMethod.POST)
    public String addCarPOST(@ModelAttribute("car") @Valid Car car, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("carTypes", VehicleType.values());
            return "addCar";
        }

        carService.addCar(car);
        return "redirect:/allCars";
    }

    //Po usunięciu powrót na stronę ze wszystkimi pojazdami
    @RequestMapping(value = "deleteCar/{carId}", method = RequestMethod.GET)
    public String deleteCar(@PathVariable("carId") String carId) {
        carService.removeCar(Integer.parseInt(carId));
        return "redirect:/allCars";
    }

    @RequestMapping(value = "showCar/{carId}", method = RequestMethod.GET)
    public String showCar(@PathVariable("carId") String carId, Model model) {

        Car car = carService.getCarById(Integer.parseInt(carId));

        model.addAttribute("car", car);
        model.addAttribute("carTypes", VehicleType.values());

        return "showCar";
    }

    @RequestMapping(value = "updateCar", method = RequestMethod.POST)
    public String updateCar(@ModelAttribute("car") @Valid Car car, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("carTypes", VehicleType.values());
            return "showCar";
        }

        carService.addCar(car);

        return "redirect:/allCars";
    }

    @RequestMapping(value = "showEventsForCar/{carId}", method = RequestMethod.GET)
    public String showEventsForCar(@PathVariable("carId") String carId, Model model) {

        model.addAttribute("events", eventService.getAllEventsByCarId(Integer.parseInt(carId)));

        return "allEvents";
    }
}
