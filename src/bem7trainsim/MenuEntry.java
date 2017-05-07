package bem7trainsim;

/**
 * Created by marci on 5/7/17.
 */
public abstract class MenuEntry {
    protected String label;

    public MenuEntry(String label) {
        this.label = label;
    }

    public abstract void click();
}
