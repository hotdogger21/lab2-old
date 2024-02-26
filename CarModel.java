import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CarModel {
    private final int delay = 50;
    private Timer timer = new Timer(delay, new CarModel.TimerListener());
    protected ArrayList<Car> cars;
    protected ArrayList<Workshop> workshops;
    private ArrayList<observer> observers = new ArrayList<>();
    protected HashMap<HasPosition, GraphicsComponent> carmap;
    protected int borderX = 800;
    protected int borderY = 560;

    public CarModel(HashMap<HasPosition, GraphicsComponent> carmap ) {
        // Start the timer
        timer.start();
        this.carmap = carmap;
        cars = listCars(carmap);
        workshops = listWorkshops(carmap);
    }

    public void addObserver(observer l){
        observers.add(l);
    }

    protected void updateObservers (){
        for (observer l : observers){
            l.actOnUpdate();
        }
    }

    private ArrayList<Workshop> listWorkshops(HashMap<HasPosition, GraphicsComponent> CarMap){
        ArrayList<Workshop> workshops = new ArrayList<>();
        for(HasPosition s : CarMap.keySet() ){
            if( s instanceof Workshop<?>){
                workshops.add((Workshop) s);
            }
        }
        return workshops;
    }


    private ArrayList<Car> listCars(HashMap<HasPosition, GraphicsComponent> CarMap){
        ArrayList<Car> cars = new ArrayList<>();
        for(HasPosition s : CarMap.keySet() ){
            if( s instanceof Car){
                cars.add((Car) s);
            }
        }
        return cars;
    }

    protected void addCar(){
        GraphicsFactory factory = new GraphicsFactory();
        Volvo240 cartemp = CarFactory.createVolvo();
        carmap.put(cartemp, factory.createGraphics("pics/Volvo240.jpg", cartemp));
        this.cars = listCars(carmap);
    }

    protected void removeCar(){

        carmap.remove(cars.removeFirst());
    }

    private void CheckCarCollision (Car car, int borderX, int borderY){
        if(car.position.x + carmap.get(car).getpicWidth() > borderX || car.position.x < 0){
            car.turnLeft();
            car.turnLeft();
        }
        if(car.position.y + carmap.get(car).getpicHeight()  > borderY || car.position.y < 0){
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


                addCarToWorkshop(car, carIterator);
            }
            updateObservers();
        }
    }

    private void addCarToWorkshop(Car car, Iterator<Car> carIterator) {
        if (car instanceof Volvo240){
            for (Workshop<Volvo240> w : workshops){
                if (car.getPosition().distance(w.getPosition()) < 8){
                    w.addCar((Volvo240) car);
                    carmap.remove(car);
                    carIterator.remove();
                }
            }
        }
    }
}
