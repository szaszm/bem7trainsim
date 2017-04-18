package bem7trainsim;

/**
 * Created by marci on 2017.03.18.
 * Represents the decoration on the table
 */
public class Decoration extends Field {
    private boolean state;

    public Decoration() {
        state = false;
    }

    /**
     * Changes the state of the decoration
     */
    public void changeState() {
        state = !state;
    }

    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    public String getDrawData() {
        return " ";
    }
}
