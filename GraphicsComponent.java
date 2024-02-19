import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicsComponent<T extends HasPosition> {


    T owner;

    BufferedImage graphics;

    Point position = new Point(0,0); // graphics position

    GraphicsComponent(T owner, String graphics){
        this.owner = owner;
        this.graphics = bufferImage(graphics);
    }



    BufferedImage bufferImage(String path){
        BufferedImage image = null;

        try {
            image = ImageIO.read(DrawPanel.class.getResourceAsStream(path));

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return image;
    }


    void draw(Graphics g){
        g.drawImage(graphics, (int)owner.getPosition().x + position.x, (int)owner.getPosition().y + position.y, null); // see javadoc for more info on the parameters
    }

    void setPosition(Point position){
        this.position = position;
    }



}
