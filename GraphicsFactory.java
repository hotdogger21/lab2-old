public class GraphicsFactory<T extends HasPosition> {

    //går detta inte göras static?

    public GraphicsComponent<T> createGraphics(String path, T owner ){


        return new GraphicsComponent<>(owner, path);
    }

}
