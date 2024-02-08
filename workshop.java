import java.awt.geom.Point2D;
import java.util.ArrayList;
public class workshop <T> {

    protected ArrayList<T> carlist;
    private final int size;

    protected Point2D.Double position;


    public workshop(int size){
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

}
