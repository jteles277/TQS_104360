package tqs.cars;
 
//Boundary Imports
import tqs.cars.boundary.CarController;
//Services Imports
import tqs.cars.services.CarManagerService;
//Model Imports
import tqs.cars.model.Car;
import tqs.cars.data.CarRepository;


//java Imports
import java.util.Arrays;
import java.util.List;

//Spring Framework Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

//AssertJ Tests Imports
import static org.assertj.core.api.Assertions.assertThat;

//Junit Imports 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
  
@DataJpaTest 
public class RepTest {

   
    // get a test-friendly Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

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


    @Test
    void Find_By_Id() { 
 
        entityManager.persistAndFlush(car1);  
 
        Car found = carRepository.findByCarId(car1.getCarId());
        assertThat( found ).isEqualTo(car1);
    }


    @Test
    void Get_All_Cars() {
         

        List<Car> allCars = Arrays.asList(car2,car3, car4);

        entityManager.persistAndFlush(car2);  
        entityManager.persistAndFlush(car3);  
        entityManager.persistAndFlush(car4);  

        List<Car> found = carRepository.findAll();
        assertThat( found ).isEqualTo(allCars);



    }


}

 