public class Main {

    private static final Main instance = new Main();

    private final EventManager eventManager;

    public Main() {
        this.eventManager = new EventManager();
        this.eventManager.register(this);
    }

    public static void main(String[] args) {
        /*
         * Setup event manager
         */
        new Main();

        /*
         * Call event
         */
        new MainInitEvent().call();
    }

    @Listener
    public void onMain(MainInitEvent event) {
        System.out.println(event.message);
    }

    public static Main getInstance() {
        return instance;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

}
