package bem7trainsim;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marci on 2017.03.18..
 */
public class CrossRail extends Rail {
    /**
     * The rails from the other side
     */
    private List<Rail> crossLinks;

    public CrossRail() {
        super();
        this.crossLinks = new ArrayList<>();
    }

    /**
     * Gets the next rail to go to, if the train comes from the given rail
     * @param from the rail before
     * @return the next rail
     */
    @Override
    public Rail next(Rail from) {
        if(links.contains(from)) {
            if(links.indexOf(from) == 0) return links.get(1);
            else return links.get(0);
        } else {
            if(crossLinks.indexOf(from) == 0) return crossLinks.get(1);
            else return crossLinks.get(0);
        }
    }

    /**
     * Adds a link to the crossing rail
     * @param rail the new link to add
     */
    public void addLinkToCross(Rail rail) {
        crossLinks.add(rail);
    }


    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    public FieldDrawData getDrawData() {
        List<String> layers = new ArrayList<>();
        layers.add("╬");
        if (coach != null)
            layers.add(coach.getDrawData());
        return new FieldDrawData(layers);
    }
}
