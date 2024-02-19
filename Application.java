import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Application {


    private CarView frame;
    private Timer timer;
    private CarController cc;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Workshop> workshops = new ArrayList<>();
    private ArrayList<GraphicsComponent> graphicsComponents = new ArrayList<>();


    public Application() {

        cc = new CarController();
        frame = new CarView("Simulation", cc);


    }



    private class TimerListener implements ActionListener {

        //tillämpa functional decomposition på denna metod
        public void actionPerformed(ActionEvent e) {

            Iterator<Car> carIterator = cars.iterator();

            while (carIterator.hasNext()){
                Car car = carIterator.next();
                {
                    car.move();
                    int x = (int) Math.round(car.position.x);
                    int y = (int) Math.round(car.position.y);

                    //finns det något annat sätt att få tag i drawpanels höjd och bredd utan att ha en komposition av carview
                    int borderX = frame.drawPanel.getWidth();
                    int borderY = frame.drawPanel.getHeight();
                    //frame.drawPanel.moveit(x, y, car);
                    // repaint() calls the paintComponent method of the panel

                    //flytta till drawpanel kanske?
                    frame.drawPanel.repaint();


                    if(car.position.x + frame.drawPanel.volvoImage.getWidth() > borderX || car.position.x < 0){
                        car.turnLeft();
                        car.turnLeft();
                    }
                    if(car.position.y + frame.drawPanel.volvoImage.getHeight() > borderY || car.position.y < 0){
                        car.turnLeft();
                        car.turnLeft();
                    }

                    if (car instanceof Volvo240){
                        for (Workshop<Volvo240> w : volvowork){
                            if (car.position.distance(w.position) < 25){
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
