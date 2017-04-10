package bem7trainsim;
/**
 * Created by marci on 2017.03.18..
 */
public class Switch extends Rail {
    private Rail toChangeLink;
    private SwitchState state;

    public Switch() {
        super();
        state = new SwitchStateStraight();
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
        if(state.nextStraight()) state = new SwitchStateCurve();
        else state = new SwitchStateStraight();
    }

    public void addLinkToChange(Rail rail) {
        this.toChangeLink = rail;
    }

    @Override
    String getDrawData() {
        return "│┐└┘┌─"; //TODO: needs data from ctor
    }
}
