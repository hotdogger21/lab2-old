import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CarModel {
    private final int delay = 50;
    private Timer timer = new Timer(delay, new CarModel.TimerListener());
    protected ArrayList<Car> cars = new ArrayList<>();
    protected ArrayList<Workshop> workshops = new ArrayList<>();
    private ArrayList<observer> observers = new ArrayList<>();
    protected HashMap<HasPosition, GraphicsComponent> carmap;
    int borderX;
    int borderY;

    public CarModel(int x , int y,HashMap<HasPosition, GraphicsComponent> carmap ) {
        // Start the timer
        timer.start();
        borderX = x;
        borderY = y;
        this.carmap = carmap;
    }

    public void addObserver(observer l){
        observers.add(l);
    }

    protected void updateObservers (){
        for (observer l : observers){
            l.actOnUpdate();
        }
    }

    private void CheckCarCollision (Car car, int borderX, int borderY){
        if(car.position.x + 100 > borderX || car.position.x < 0){
            car.turnLeft();
            car.turnLeft();
        }
        if(car.position.y + 60 > borderY || car.position.y < 0){
            car.turnLeft();
            car.turnLeft();
        }
    }

    private class TimerListener implements ActionListener {

        //tillämpa functional decomposition på denna metod
        public void actionPerformed(ActionEvent e) {

            Iterator<Car> carIterator = cars.iterator();

            while (carIterator.hasNext()){
                Car car = carIterator.next();
                {
                    car.move();
                    updateObservers();
                    CheckCarCollision(car,borderX,borderY);
                }

                if (car instanceof Volvo240){
                    for (Workshop<Volvo240> w : workshops){
                        if (car.getPosition().distance(w.getPosition()) < 8){
                            w.addCar((Volvo240) car);
                            carIterator.remove();
                        }
                    }
                }
            }
        }
    }
}
