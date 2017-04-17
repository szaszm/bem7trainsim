package bem7trainsim;

import bem7trainsim.SimpleRail.Orientation;

/**
 * Created by marci on 2017.03.18..
 */
public class Switch extends Rail {
    /**
     * Gives the orientation. The compass point is where coming from. The sides are which side is available to be chosen
     */
    public enum Orientation {
        NorthRight, EastRight, SouthRight, WestRight,
        NorthLeft, EastLeft, SouthLeft, WestLeft
    };
    private Orientation orientation;
    private Rail toChangeLink;
    private SwitchState state;

    public Switch(Orientation orientation) {
        super();
        state = new SwitchStateStraight();
        this.orientation = orientation;
    }
    
    public void setOrientation(Orientation orientation){
    	this.orientation = orientation;
    }

    @Override
    public Rail next(Rail from) {
        if(!links.contains(from) || links.indexOf(from) == 1) {
            return links.get(0);
        }
        if(state.nextStraight()) return links.get(1);
        else return toChangeLink;
    }

    public void change() throws CannotSwitchException {
        if(train == null){
            if(state.nextStraight()) state = new SwitchStateCurve();
            else state = new SwitchStateStraight();
        } else {
            throw new CannotSwitchException("There is a train on that switch.");
        }
    }

    public void addLinkToChange(Rail rail) {
        this.toChangeLink = rail;
    }

    /**
     * Returns a string from the orientation and the state of the switch
     * @return the string representing the switch
     */
    @Override
    public String getDrawData() {
        if (coach != null)
            return coach.getDrawData();
        if (orientation == Orientation.NorthLeft) {
            return (state.nextStraight())  ? "│" : "└";
        }
        if (orientation == Orientation.NorthRight) {
            return (state.nextStraight())  ? "│" : "┘";
        }
        if (orientation == Orientation.EastLeft) {
            return (state.nextStraight())  ? "─" : "┌";
        }
        if (orientation == Orientation.EastRight) {
            return (state.nextStraight())  ? "─" : "└";
        }
        if (orientation == Orientation.SouthRight) {
            return (state.nextStraight())  ? "│" : "┌";
        }
        if (orientation == Orientation.SouthLeft) {
            return (state.nextStraight())  ? "│" : "┐";
        }
        if (orientation == Orientation.WestLeft) {
            return (state.nextStraight())  ? "─" : "┘";
        }
        if (orientation == Orientation.WestRight) {
            return (state.nextStraight())  ? "─" : "┐";
        }
        // this will never happen:
        return "?";
    }
}
