public class Event {

    public final void call() {
        Main.getInstance().getEventManager().call(this);
    }

}
