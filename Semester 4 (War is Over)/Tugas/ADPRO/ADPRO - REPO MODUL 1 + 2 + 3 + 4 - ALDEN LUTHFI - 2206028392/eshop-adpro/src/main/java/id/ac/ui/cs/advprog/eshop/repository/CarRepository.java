package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {

    private final List<Car> carData = new ArrayList<>();

    public Car create(Car car){
        if (car.getCarId() == null){
            car.setCarId(String.valueOf(UUID.randomUUID()));
        }
        carData.add(car);
        return car;
    }

    public Iterator<Car> findAll(){
        return  carData.iterator();
    }

    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                return car;
            }
        }
        return null;
    }
    public Car update(String id, Car updatedCar) {
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                // Update the existing car with the new information
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                return car;
            }
        }
        return null; // Handle the case where the car is not found
    }

    public void delete(String id) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }

}