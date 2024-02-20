import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    static ArrayList<GraphicsComponent> graphicsComponents = new ArrayList<>();

    static HashMap<HasPosition, GraphicsComponent> carsTest = new HashMap<>();
    protected ArrayList<Workshop<Volvo240>> volvowork = new ArrayList<>();

    public Application() {

        cc = new CarController();

        GraphicsFactory factory = new GraphicsFactory();


        Volvo240 car1 = CarFactory.createVolvo();
        car1.position.x = 300;
        car1.direction = 2;
        carsTest.put(car1, factory.createGraphics("pics/Volvo240.jpg", car1));


        Scania car2 = CarFactory.createScania();
        car2.position.x = 0;
        car2.position.y = 100;
        carsTest.put(car2, factory.createGraphics("pics/Scania.jpg", car1));

        Saab95 car3 = CarFactory.createSaab();
        car3.position.x = 300;
        car3.position.y = 200;
        carsTest.put(car3, factory.createGraphics("pics/Saab95.jpg", car3));

        Workshop<Volvo240> volvoworkshop = WorkshopFactory.createVolvoWorkshop(5);
        volvoworkshop.position.x = 300;
        volvoworkshop.position.y = 300;
        carsTest.put(volvoworkshop, factory.createGraphics("pics/Scania.jpg", volvoworkshop));



        // Instance of this classs
        CarController cc = new CarController();

        cars.add(car1);
        cars.add(car2);
        cars.add(car3);

        cc.cars = cars;

        volvowork.add(volvoworkshop);


        // Start a new view and send a reference of cc
        frame = new CarView("CarSim 1.0", cc);

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

                    //finns det något annat sätt att få tag i drawpanels höjd och bredd utan att ha en komposition av carview
                    int borderX = frame.drawPanel.getWidth();
                    int borderY = frame.drawPanel.getHeight();


                    //observer pattern (i framtiden)
                    frame.drawPanel.repaint();

                    CheckCarCollision(car,borderX,borderY);

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


}
