public class WorkshopFactory {

    public static Workshop<Car> createWorkshop (int size){
        return new Workshop<>(size);
    }

    public static Workshop<Volvo240> createVolvoWorkshop (int size){
        return new Workshop<>(size);
    }
}
