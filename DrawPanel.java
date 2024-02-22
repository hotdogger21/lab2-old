import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel implements observer {

    HashMap<HasPosition, GraphicsComponent> graphicComponents;

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

    @Override
    public void actOnUpdate() {
        repaint();
    }
}
