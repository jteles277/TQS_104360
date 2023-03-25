package tqs.cars.boundary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.cars.model.Car;
import tqs.cars.model.CarDTO;
import tqs.cars.services.CarManagerService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarManagerService carManagerService;

    public CarController(CarManagerService injectedCarManagerService) {
        this.carManagerService = injectedCarManagerService;
    }


    @PostMapping("/cars") public ResponseEntity<Car> createCar(@RequestBody CarDTO car) {
        HttpStatus status = HttpStatus.CREATED;
        
        Car new_Car = new Car(car.getMaker(), car.getModel());
        
        Car saved = carManagerService.save(new_Car);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping(path = "/cars",  produces = "application/json")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) {
        System.out.println("\n\n\n" + " Id: " + carId + "\n\n\n");
        Car car = carManagerService.getCarDetails(carId);
        return ResponseEntity.ok().body(car);
    }

}
