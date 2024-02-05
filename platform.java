public class platform {

    protected boolean rampOpen;
    protected Truck turk;

    public platform(Truck turk){
        this.rampOpen = false;
        this.turk = turk;
    }

    public void openRamp(){
        if (turk.currentSpeed > 0){
            throw new RuntimeException("truck must be still before lowering ramp");
        }
        else rampOpen = true;
    }

    public void closeRamp(){
        rampOpen = false;
    }
}
