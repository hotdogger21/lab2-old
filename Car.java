import java.awt.*;
import java.awt.geom.Point2D;

abstract class Car implements Movable{
    private final int nrDoors; // Number of doors on the car
    protected final double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name

    //movement saker
    protected int direction;
    protected Point2D.Double position;


    public Car(int nrDoor, double enginePower, Color color, String modelName){
        this.nrDoors = nrDoor;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        currentSpeed = 0;
        stopEngine();

        // movement saker
        this.direction = 1;
        this.position = new Point2D.Double(0,0);
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public double speedFactor(){
        return enginePower * 0.01;
    }

    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    public void gas(double amount){
        if (amount < 0){
            throw new RuntimeException("no negative amounts!!!");
        }
        else {
            incrementSpeed(Math.min(1, amount));
            currentSpeed = Math.min(enginePower, currentSpeed);
        }
    }
    public void brake(double amount){
        if (amount < 0){
            throw new RuntimeException("no negative amounts!!!");
        }
        else {
            decrementSpeed(Math.min(1, amount));
            currentSpeed = Math.max(0, currentSpeed);
        }
    }

    // movement saker

    public Point2D getPos(){
        return this.position;
    }
    @Override
    public void move() {
        if (direction == 1){
            this.position.y += currentSpeed;
        }
        if (direction == 3){
            this.position.y -= currentSpeed;
        }
        if (direction == 2){
            this.position.x += currentSpeed;
        }
        if (direction == 4){
            this.position.x -= currentSpeed;
        }
    }

    @Override
    public void turnLeft() {
        direction = (direction + 1) % 4;
    }

    @Override
    public void turnRight() {
        direction--;
        if (direction < 1){
            direction = 4;
        }
    }
}
