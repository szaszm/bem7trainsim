package bem7trainsim;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marci on 2017.03.17..
 */
public class TunnelEntrance extends Rail {
    /**
     * The orientation of the field.
     */
    protected SimpleRail.Orientation orientation;
    /**
     * The state
     */
    private TunnelEntranceState state;
    /**
     * The linking rail to the tunnel if built
     */
    private Rail linkToTunnel;
    /**
     * The tunnel linked to
     */
    private Tunnel tunnel;

    /**
     * Creates entrance with the given orientation
     * @param orientation The orientation of the entrance
     */
    public TunnelEntrance(SimpleRail.Orientation orientation) {
        super();
        state = new TunnelEntranceStateStraight();
        this.orientation = orientation;
    }

    /**
     * sets the tunnel of the entrance
     * @param tunnel The tunnel which is accessible through this entrance
     */
    public void setTunnel(Tunnel tunnel){
        this.tunnel = tunnel;
    }


    /**
     * Gets the next rail if the rail is given where the train comes from
     * @param from The rail where the train comes from
     * @return The next rail
     */
    @Override
    public Rail next(Rail from) {
        if(from == links.get(1)) {
            if(state.nextStraight())
                return links.get(0);
            else return linkToTunnel;
        } else if(from == links.get(0)) {
            if(state.nextStraight())
                return links.get(1);
            else return linkToTunnel;
        } else {
            return links.get(0);
        }
    }

    /**
     * Connects to a tunnel
     * @param rail The starting rail
     */
    public void addLinkToTunnel(Rail rail) {
        this.linkToTunnel = rail;
    }

    /**
     * Handles the click event
     * @throws CannotBuildException Thrown if cannot be built
     */
    public void click() throws CannotBuildException {
        if(train == null){
            tunnel.checkEntrance(this);
        } else {
            throw new CannotBuildException("There is a train in the tunnel. You cannot build until it leaves!");
        }
    }

    /**
     * Sets the state of the tunnel entrance
     * @param state the new state.
     */
    public void setState(TunnelEntranceState state) {
        this.state = state;
    }

    /**
     * Gets if the red light is on.
     * @return Whether the red light is on or not.
     */
    public boolean redLight() {
        return state.redLight();
    }

    /**
     * Indicated the arrival of a train to the field
     * @param train The train which is arrived
     * @throws CollisionException When there is a collision
     */
    public void arrive(Train train) throws CollisionException {
        super.arrive(train);
        //Ha a vonat kintről jött és az alagúrba terel a vágány, akkor belépünk az alagútba
        if(tunnel != null && !tunnel.hasTrain(train, this)) {
            if(!state.nextStraight())
                tunnel.enter(this, train);
        } else {
            //ha nem kintről jött, akkor újra a felszínre lépett
            train.onSurface = true;
        }
    }

    /**
     * the train leaves the tunnel
     */
    public void leave() {
        //ha bentről jött a vonat, akkor elhagyja az alagutat
        if(tunnel != null && tunnel.hasTrain(train, this)) {
            tunnel.leave(this, train);
        }
        super.leave();
    }

    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    public FieldDrawData getDrawData() {
        String s = state.getDrawData();
        String c = "";
        if(orientation == SimpleRail.Orientation.HORIZONTAL){
            s += "═";
            c += "═";
        } else {
            s += "║";
            c += "║";
        }
        List<String> layers = new ArrayList<>();
        layers.add(s);
        if(coach != null) {
            List<String> drawData = coach.getDrawData();
            for (String dd : drawData) {
                layers.add(dd + c);
            }
        }
        return new FieldDrawData(layers);
    }
}
