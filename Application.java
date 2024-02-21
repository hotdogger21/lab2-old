import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Application {


    private CarView frame;
    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());
    private CarController cc;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Workshop> workshops = new ArrayList<>();

    static HashMap<HasPosition, GraphicsComponent> carsTest = new HashMap<>();


    public Application() {



        GraphicsFactory factory = new GraphicsFactory();


        Volvo240 car1 = CarFactory.createVolvo();
        car1.position.x = 300;
        car1.direction = 2;
        carsTest.put(car1, factory.createGraphics("pics/Volvo240.jpg", car1));


        Scania car2 = CarFactory.createScania();
        car2.position.x = 0;
        car2.position.y = 100;
        carsTest.put(car2, factory.createGraphics("pics/Scania.jpg", car2));

        Saab95 car3 = CarFactory.createSaab();
        car3.position.x = 300;
        car3.position.y = 200;
        carsTest.put(car3, factory.createGraphics("pics/Saab95.jpg", car3));

        Workshop<Volvo240> volvoworkshop = WorkshopFactory.createVolvoWorkshop(5);
        volvoworkshop.position.x = 300;
        volvoworkshop.position.y = 300;
        carsTest.put(volvoworkshop, factory.createGraphics("pics/VolvoBrand.jpg", volvoworkshop));

        for(HasPosition s : carsTest.keySet() ){
            if( s instanceof Car){
                cars.add((Car) s);
            }
        }

        cc = new CarController(cars);


        // Start a new view and send a reference of cc
        frame = new CarView("CarSim 1.0", cc, carsTest);

        // Start the timer
        timer.start();
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
                        int borderX = frame.drawPanel.getWidth();
                        int borderY = frame.drawPanel.getHeight();
                        frame.drawPanel.repaint();
                        CheckCarCollision(car,borderX,borderY);
                    }

                    if (car instanceof Volvo240){
                        for (Workshop<Volvo240> w : workshops){
                            if (car.getPosition().distance(w.getPosition()) < 25){
                                w.addCar((Volvo240) car);
                                carIterator.remove();
                            }
                        }
                    }
                }
            }
        }
}
