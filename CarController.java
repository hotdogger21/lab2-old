
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)

    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.

    // The frame that represents this instance View of the MVC pattern

    // A list of cars, modify if needed
    protected ArrayList<Car> cars = new ArrayList<>();

    //flytta dessa till carview kanske

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

}