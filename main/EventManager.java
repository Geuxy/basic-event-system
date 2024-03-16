import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class EventManager {

    /*
     * Map of class instances and methods to call
     */
    private final HashMap<Object, Method> data = new HashMap<>();

    /*
     * Better error handling
     */
    private final Consumer<Throwable> errorHandler = Throwable::printStackTrace;

    /*
     * Adds a Set with the given class instance and method that is annotated with Listener
     */
    public void register(Object listener) {
        Stream.of(listener.getClass().getMethods()).filter(m -> m.isAnnotationPresent(Listener.class)).forEach(m -> data.put(listener, m));
    }

    /*
     * Removes all Sets from the given class instance
     */
    public void unregister(Object listener) {
        this.data.entrySet().removeIf(e -> e.getKey().equals(listener));
    }

    /*
     * Calls all methods with the given event
     */
    public void call(Event event) {
        data.forEach((c, m) -> {
            try {
                m.invoke(c, event);
            } catch(Throwable t) {
                errorHandler.accept(t);
            }
        });

    }

}
