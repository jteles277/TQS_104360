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
import java.util.Optional;

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

// Rest Assured Imports
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;   
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given; 
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(CarController.class)
class ControllerTest {

     
     
    Car car1, car2, car3, car4;
    CarController controller;

    @MockBean
    CarManagerService service;
    
    @Autowired
    private MockMvc mvc;    

    @BeforeEach
    public void setUpTestCars() throws Exception {
        car1 = new Car("maker_1", "Model_1");
        car2 = new Car("maker_2", "Model_2");
        car3 = new Car("maker_3", "Model_3");
        car4 = new Car("maker_4", "Model_4");
        controller = new CarController(service);

        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    void Test_createCar() throws Exception  {
       
        when( service.save(Mockito.any()) ).thenReturn(car2);


        RestAssuredMockMvc
        .given() 
            .contentType("application/json")
            .body(JsonUtils.toJson(car2))
        .when()
            .post("/api/cars")
        .then() 
            .body("$", is(<{carId=6, maker=maker_2, model=Model_2}>))
            .body("$.maker", is("maker_2"));


        mvc.perform(
            post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car2)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.model", is("<{carId=6, maker=maker_2, model=Model_2}>")))
            .andExpect(jsonPath("$.maker",is("maker_2")));

        verify(service, times(1)).save(Mockito.any());

    }
    @Test
    void Test_getAllCars()  throws Exception { 

        List<Car> allCars = Arrays.asList(car1, car2, car3, car4);

        when( service.getAllCars()).thenReturn(allCars); 

        mvc.perform(
                get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].maker", is(car1.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(car2.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(car3.getMaker())))
                .andExpect(jsonPath("$[3].maker", is(car4.getMaker())));

    }
    @Test
    void Test_getCarByIdr()  throws Exception { 
  
        when(service.getCarDetails(car4.getCarId())).thenReturn(car4);

        mvc.perform(
            get("/api/cars/"+car4.getCarId()+"").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker", is(car4.getMaker())))
            .andExpect(jsonPath("$.model", is(car4.getModel())));
       
    }

     

}
