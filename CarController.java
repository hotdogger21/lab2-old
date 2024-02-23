
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static java.lang.System.out;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {

    protected ArrayList<Car> cars;

    private CarModel model;

    public CarController(CarModel model) {
        this.cars = model.cars;
        this.model = model;
    }


    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    public void TurboOn(){
        for (Car car : cars) {
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    public void TurboOff(){
        for (Car car : cars) {
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

    public void LiftBed(){
        for (Car car : cars) {
            if (car instanceof Scania){
                ((Scania) car).openRamp();
            }
        }
    }

    public void LowerBed(){
        for (Car car : cars) {
            if (car instanceof Scania){
                ((Scania) car).closeRamp();
            }
        }
    }

    public void StartAll() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    public void StopAll() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    public void AddCar() {
        model.addCar();
    }

    public void RemoveCar() {
        // TODO check if empty

    }


    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */

}