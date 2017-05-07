package bem7trainsim;

/**
 * Created by marci on 5/7/17.
 */
public class MenuEntry {
    protected String label;

    /**
     * The Controller which connects with the instance of the MenuEntry
     * */
    private MainMenuControllerState mcs;

    /**
     * Menu entry constructor
     * @param label The label of the menu entry
     */
    public MenuEntry(String label, MainMenuControllerState mcs) {
        this.label = label;
        this.mcs = mcs;
    }

    /**
     * Handles click on this menu entry
     */
    public void click(){
        mcs.click(this);
    }

    public String GetLabel(){
        return label;
    }
}
