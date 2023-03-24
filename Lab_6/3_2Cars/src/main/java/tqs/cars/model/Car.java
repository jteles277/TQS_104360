package tqs.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    private static long idCache = 1;  

    @Id 
    Long carId;

    String maker;
    String model;

    
    public Car() {
        carId = idCache;
        idCache +=1;
    }
    public Car(String maker, String model) {
        this.carId = idCache;
        this.maker = maker;
        this.model = model;
        idCache +=1; 
    }  

    @Override
    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass()) return false;
        
        Car car = (Car) o;

        return carId.equals(car.carId) && Objects.equals(maker, car.maker) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, maker, model);
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
