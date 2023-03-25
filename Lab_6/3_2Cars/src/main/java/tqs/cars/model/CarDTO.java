package tqs.cars.model;

import javax.persistence.*;
import java.util.Objects;
 
public class CarDTO {

    private static long idCache = 1;  
 
    long carId;
    String maker;
    String model;

    
    public CarDTO() {
        this.carId = idCache;
        idCache +=1;
    }
    public CarDTO(String maker, String model) {
        this.carId = idCache;
        this.maker = maker;
        this.model = model; 
    }   

    public Long getCarId() {
        return carId;
    } 
   
    public String getMaker() {
        return maker;
    } 

    public String getModel() {
        return model;
    }

    
}
