package bem7trainsim;

/**
 * Created by marci on 5/7/17.
 */
public abstract class MenuEntry {
    protected String label;

    /**
     * The Controller which connects with the instance of the MenuEntry
     * */
    private MainMenuControllerState mcs;

    public MenuEntry(String label) {
        this.label = label;
    }

    public abstract void click();
}
