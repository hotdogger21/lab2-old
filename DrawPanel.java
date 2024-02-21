import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    // Just a single image, TODO: Generalize

    CarController carC;

    BufferedImage volvoImage;

    HashMap<HasPosition, GraphicsComponent> graphicComponents;

    // To keep track of a single car's position


    Point volvoWorkshopPoint = new Point(300, 300);

    // TODO: Make this general for all cars
    void moveit(int x, int y, Car c) {
        /*
        int p = carC.cars.indexOf(c);
        pointlist.get(p).x = x;
        pointlist.get(p).y = y;

         */
    }


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, HashMap<HasPosition, GraphicsComponent> graphicsComponents) {
        this.graphicComponents = graphicsComponents;

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (GraphicsComponent graphic : graphicComponents.values()) {
            graphic.draw(g);
        }
    }
}
