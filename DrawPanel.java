import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize

    CarController carC;

    BufferedImage volvoImage;
    BufferedImage volvoWorkshopImage;
    BufferedImage ScaniaImage;
    BufferedImage SaabImage;



    // To keep track of a single car's position


    ArrayList<Point> pointlist = new ArrayList<>();

    protected HashMap<Car, Point> ImagePoints = new HashMap<>();

    Point volvoWorkshopPoint = new Point(300,300);

    // TODO: Make this general for all cars
    void moveit(int x, int y, Car c){
        int p = carC.cars.indexOf(c);
        pointlist.get(p).x = x;
        pointlist.get(p).y = y;
    }


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarController cc) {
        carC = cc;

        for (int i = 0; i < cc.cars.size(); i++) {
            pointlist.add(new Point((int) Math.round(cc.cars.get(i).position.x), (int) Math.round(cc.cars.get(i).position.y)));
        }

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);



        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            ScaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            SaabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < carC.cars.size(); i++) {
            BufferedImage image = volvoImage;

            if (carC.cars.get(i) instanceof Saab95){
                image = SaabImage;
            }
            else if (carC.cars.get(i) instanceof Scania){
                image = ScaniaImage;
            }

            g.drawImage(image, pointlist.get(i).x, pointlist.get(i).y, null);
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
