import java.awt.geom.Point2D;
import java.util.ArrayList;
public class Workshop <T extends Car> implements HasPosition {

    protected ArrayList<T> carlist;
    private final int size;

    protected Point2D.Double position;


    public Workshop(int size){
        carlist = new ArrayList<>();
        this.size = size;
        this.position = new Point2D.Double(0,0);
    }

    public void addCar(T a){
        if(carlist.size() < size){
            carlist.add(a);
        }
        else throw new RuntimeException("over capacity");
    }
    public T  releaseCar(){
        return carlist.removeLast();
    }

    @Override
    public Point2D.Double getPosition() {
        return position;
    }
}
