import java.awt.*;

public class Scania extends Car implements HasRamp{


    platform ramp = new platform();
    protected int platformangle;


    public Scania(){
        super(2, 100, Color.black, "Scania truck");
        this.platformangle = 0;
    }

    public void gas(double amount){
        if (amount < 0){
            throw new RuntimeException("no negative amounts!!!");
        }
        else if (ramp.rampOpen) {
            throw new RuntimeException("no driving when the platform is raised!");
        }
        else {
            incrementSpeed(Math.min(1, amount));
            currentSpeed = Math.min(enginePower, currentSpeed);

        }
    }

    public void startEngine(){
        if (!ramp.rampOpen){
            currentSpeed = 0.1;
        }
    }

    public void openRamp(){
        ramp.openRamp(currentSpeed);
        platformangle = Math.min(70, platformangle + 1);
    }

    public void closeRamp(){
        if (platformangle > 0){
            platformangle = Math.max(0, platformangle - 1);
            if (platformangle == 0){
                ramp.closeRamp();
            }
        }
    }
}
