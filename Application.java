import java.util.ArrayList;
import java.util.HashMap;

public class Application {


    private CarView frame;
    private CarController cc;
    private CarModel model;
    private final int delay = 50;
    //private Timer timer = new Timer(delay, new TimerListener());




    public Application() {

        model = createModel();
        cc = new CarController(model);
        frame = new CarView("my car game 1",cc,model);

    }

    private CarModel createModel(){
        HashMap<HasPosition, GraphicsComponent> carsTest = createCarMap();
        ArrayList<Car> carstemp =  listCars(carsTest);
        ArrayList<Workshop> worktemp = listWorkshops(carsTest);

        CarModel temp = new CarModel(800, 560, carsTest);
        temp.cars = carstemp;
        temp.workshops = worktemp;
        return temp;
    }

    // Populates a hashmap where key is an object implementing HasPosition interface and value is a GraphicsComponent
    private HashMap<HasPosition, GraphicsComponent> createCarMap(){
        HashMap<HasPosition, GraphicsComponent> carsTest = new HashMap<>();
        GraphicsFactory factory = new GraphicsFactory();

        Workshop<Volvo240> volvoworkshop = WorkshopFactory.createVolvoWorkshop(5);
        volvoworkshop.position.x = 300;
        volvoworkshop.position.y = 300;
        carsTest.put(volvoworkshop, factory.createGraphics("pics/VolvoBrand.jpg", volvoworkshop));


        Volvo240 car1 = CarFactory.createVolvo();
        car1.position.x = 300;
        car1.direction = 2;
        carsTest.put(car1, factory.createGraphics("pics/Volvo240.jpg", car1));

        Scania car2 = CarFactory.createScania();
        car2.position.x = 0;
        car2.position.y = 100;
        carsTest.put(car2, factory.createGraphics("pics/Scania.jpg", car2));

        Saab95 car3 = CarFactory.createSaab();
        car3.position.x = 300;
        car3.position.y = 200;
        carsTest.put(car3, factory.createGraphics("pics/Saab95.jpg", car3));

        return carsTest;
    }

    // Finds all workshop keys and insert into a new list of all workshops in the scene
    private ArrayList<Workshop> listWorkshops(HashMap<HasPosition, GraphicsComponent> CarMap){
        ArrayList<Workshop> workshops = new ArrayList<>();
        for(HasPosition s : CarMap.keySet() ){
            if( s instanceof Workshop<?>){
                workshops.add((Workshop) s);
            }
        }
        return workshops;
    }

    // Finds all car keys and insert into a new list of all cars
    private ArrayList<Car> listCars(HashMap<HasPosition, GraphicsComponent> CarMap){
        ArrayList<Car> cars = new ArrayList<>();
        for(HasPosition s : CarMap.keySet() ){
            if( s instanceof Car){
                cars.add((Car) s);
            }
        }
        return cars;
    }
}
