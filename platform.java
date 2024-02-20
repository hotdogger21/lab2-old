public class platform {

    protected boolean rampOpen;

    public platform(Truck turk){
        this.rampOpen = false;
    }

    public void openRamp(double speed){
        if (speed > 0){
            throw new RuntimeException("truck must be still before lowering ramp");
        }
        else rampOpen = true;
    }

    public void closeRamp(){
        rampOpen = false;
    }
}
