
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import static java.lang.System.out;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    protected CarView frame;
    // A list of cars, modify if needed
    protected ArrayList<Car> cars = new ArrayList<>();



    //methods:

    public static void main(String[] args) {
        Volvo240 car1 = new Volvo240();
        car1.position.x = 100;
        car1.direction = 3;

        Scania car2 = new Scania();
        car2.position.x = 0;
        car2.position.y = 100;

        Saab95 car3 = new Saab95();
        car3.position.x = 0;
        car3.position.y = 200;

        workshop<Volvo240> volvoworkshop = new workshop<>(5);
        volvoworkshop.position.x = 300;
        volvoworkshop.position.y = 300;


        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(car1);
        cc.cars.add(car2);
        cc.cars.add(car3);


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
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


    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.position.x);
                int y = (int) Math.round(car.position.y);
                int borderX = frame.drawPanel.getWidth();
                int borderY = frame.drawPanel.getHeight();
                frame.drawPanel.moveit(x, y, car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                if(car.position.x + frame.drawPanel.volvoImage.getWidth() > borderX || car.position.x < 0){
                    car.turnLeft();
                    car.turnLeft();
                }
                if(car.position.y + frame.drawPanel.volvoImage.getHeight() > borderY || car.position.y < 0){
                    car.turnLeft();
                    car.turnLeft();
                }
            }
        }
    }
}