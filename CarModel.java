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
    protected int borderX = 800;
    protected int borderY = 560;

    public CarModel(HashMap<HasPosition, GraphicsComponent> carmap ) {
        // Start the timer
        timer.start();
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

    protected void addCar(){
        GraphicsFactory factory = new GraphicsFactory();
        Volvo240 car1 = CarFactory.createVolvo();
        carmap.put(car1, factory.createGraphics("pics/Volvo240.jpg", car1));
    }

    private void CheckCarCollision (Car car, int borderX, int borderY){
        if(car.position.x + carmap.get(car).graphics.getWidth() > borderX || car.position.x < 0){
            car.turnLeft();
            car.turnLeft();
        }
        if(car.position.y + carmap.get(car).graphics.getHeight()  > borderY || car.position.y < 0){
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

                    car.move();
                    CheckCarCollision(car,borderX,borderY);


                if (car instanceof Volvo240){
                    for (Workshop<Volvo240> w : workshops){
                        if (car.getPosition().distance(w.getPosition()) < 8){
                            w.addCar((Volvo240) car);
                            carIterator.remove();
                        }
                    }
                }

                updateObservers();
            }
        }
    }
}
