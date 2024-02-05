import java.awt.*;

abstract class Truck extends Car{



    protected platform ramp = new platform(this);
    protected boolean hasRamp;


    public Truck(int nrDoor, double enginePower, Color color, String modelName, boolean hasRamp){
        super(nrDoor, enginePower, color, modelName);
        this.hasRamp = hasRamp;
    }

    @Override
    public void gas(double amount){
        if (amount < 0){
            throw new RuntimeException("no negative amounts!!!");
        }
        else if (hasRamp && ramp.rampOpen) {
            throw new RuntimeException("no driving when the platform is raised!");
        }
        else {
            incrementSpeed(Math.min(1, amount));
            currentSpeed = Math.min(enginePower, currentSpeed);

        }
    }

}
