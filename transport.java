import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayDeque;
import java.util.Deque;

public class transport extends Truck{

    Deque<Car> carStack;

    public transport(){
        super(2, 90, Color.green, "car transporter", true);
        carStack = new ArrayDeque<>();
    }

    public void openRamp(){
        ramp.openRamp();
    }

    public void closeRamp(){
        ramp.closeRamp();
    }

    public void loadCar(Car a){
        if (ramp.rampOpen && a.position.distance(this.position) <= 5){
            carStack.push(a);
            a.position = this.position;
        }
    }

    public Car unloadCar(){
        if (!ramp.rampOpen || carStack.isEmpty()){
            throw new RuntimeException("transport is either empty or ramp is closed!!!");
        }
        Car car = carStack.pop();
        car.position = new Point2D.Double(this.position.x, this.position.y);
        return car;
    }
}
