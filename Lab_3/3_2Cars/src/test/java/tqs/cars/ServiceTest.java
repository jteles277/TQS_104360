package tqs.cars;
 
//Boundary Imports
import tqs.cars.boundary.CarController;
//Services Imports
import tqs.cars.services.CarManagerService;
//Model Imports
import tqs.cars.model.Car;


//java Imports
import java.util.Arrays;
import java.util.List;

//Spring Framework Imports
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//Hamcrest Tests Imports
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

//Junit Imports 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

//Mockito Imports
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito; 
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when; 

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;
    Car car1, car2, car3, car4;
    long id_1, id_2, id_3, id_4; 

  
     

    List<Car> allCars = Arrays.asList(bmw, volvo, mercedes);

    @BeforeEach
    public void setUp() {

        car1 = new Car("maker_1", "Model_1");
        id_1 = 1;
        car2 = new Car("maker_2", "Model_2");
        id_2 = 2;
        car3 = new Car("maker_3", "Model_3");
        id_3 = 3;
        car4 = new Car("maker_4", "Model_4");
        id_4 = 4;

        car1.setCarId(id_1); 
        car2.setCarId(id_2); 
        car3.setCarId(id_3);



        Mockito.when(carRepository.findById(car1.getCardId())).thenReturn(car1);
        Mockito.when(carRepository.findById(car2.getCardId())).thenReturn(car2);
        Mockito.when(carRepository.findById(44444444)).thenReturn(null);
        Mockito.when(carRepository.findById(car3.getCardId())).thenReturn(car3);
        Mockito.when(carRepository.findAll()).thenReturn(allCars); 
    }

    @Test
    public void GetCarDetails(){
        
        assertThat(carService.getCarDetails(id_1)).isEqualTo(car1);
        assertThat(carService.getCarDetails(id_2)).isEqualTo(car2);


        assertThat(carService.getCarDetails(44444444)).isEqualTo(null);

    }

    @Test
    public void GetAllCars(){

        assertThat(carService.getAllCars()).isEqualTo(allCars);

    }


    @Test
    public void SaveCar(){ 
 
        Mockito.when(carRepository.save(id_4)).thenReturn(car4); 

        assertThat(carService.save(car4)).isEqualTo(car4);


    }

}
