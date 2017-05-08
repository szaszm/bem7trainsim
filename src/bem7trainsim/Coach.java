package bem7trainsim;

import java.util.List;

/**
 * Created by Csuto on 4/10/2017.
 * Represents the coach
 */
public abstract class Coach {
    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    public abstract List<String> getDrawData();


    /**
     * Stores the currrent direction of the coach
     */
    protected Table.Direction direction;
}
