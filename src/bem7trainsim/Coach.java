package bem7trainsim;

/**
 * Created by Csuto on 4/10/2017.
 */
public abstract class Coach {
    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    public abstract String getDrawData();


    /**
     * A kocsi jelenlegi állását tárolja
     */
    protected Table.Direction direction;
}
