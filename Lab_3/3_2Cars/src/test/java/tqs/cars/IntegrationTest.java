package tqs.cars;
 
//Boundary Imports
import tqs.cars.boundary.CarController;
//Services Imports
import tqs.cars.services.CarManagerService;
//Model Imports
import tqs.cars.model.Car;
import tqs.cars.data.CarRepository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
 
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;




@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)  
@AutoConfigureTestDatabase

//@TestPropertySource( locations = "application-integrationtest.properties")
class IntegrationTest {
 
    @LocalServerPort
    int randomServerPort;
 
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository; 

   
    Car car1, car2, car3, car4;
    long id_1, id_2, id_3, id_4; 
    List<Car> allCars;

    @BeforeEach
    public void setUpTestCars() throws Exception {
        car1 = new Car("maker_1", "Model_1");
        car2 = new Car("maker_2", "Model_2");
        car3 = new Car("maker_3", "Model_3");
        car4 = new Car("maker_4", "Model_4");
        allCars = Arrays.asList(car1, car2, car3, car4);  
    } 

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

 
    @Test
    void whenValidInput_thenCreateCar() { 

        ResponseEntity<Car> posted_car = restTemplate.postForEntity("/api/cars", car1, Car.class);

        assertThat(posted_car.getBody()).isEqualTo(car1);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("maker_1");
    } 

    @Test
    void whenGetById() { 
 

        ResponseEntity<Car> posted_car = restTemplate.postForEntity("/api/cars", car2, Car.class);  

        assertThat(posted_car.getBody()).isEqualTo(car2); 

        ResponseEntity<Car> fetched_car = restTemplate.getForEntity("/api/cars/"+car2.getCarId(), Car.class); 

        assertThat(posted_car.getBody()).isEqualTo(fetched_car.getBody()); 

    }  

    @Test
    void whenGetAll() { 

        ResponseEntity<Car> posted_car_1 = restTemplate.postForEntity("/api/cars", car1, Car.class);
        ResponseEntity<Car> posted_car_2 = restTemplate.postForEntity("/api/cars", car2, Car.class);
        ResponseEntity<Car> posted_car_3 = restTemplate.postForEntity("/api/cars", car3, Car.class);
        ResponseEntity<Car> posted_car_4 = restTemplate.postForEntity("/api/cars", car4, Car.class);

        ResponseEntity<List<Car>> resp  = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {}); 

        assertThat(resp.getBody()).isEqualTo(allCars);

    } 
}