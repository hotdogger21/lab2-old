import java.util.ArrayList;
public class workshop <T> {

    protected ArrayList<T> carlist;
    private final int size;

    public workshop(int size){
        carlist = new ArrayList<>();
        this.size = size;
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
