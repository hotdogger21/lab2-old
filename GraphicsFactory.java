public class GraphicsFactory<T extends HasPosition> {


    public GraphicsComponent<T> createGraphics(String path, T owner ){


        return new GraphicsComponent<>(owner, path);
    }

}
