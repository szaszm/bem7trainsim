package bem7trainsim;
/**
 * Created by marci on 2017.03.17..
 */
public class TunnelEntrance extends Rail {
    /**
     * The orientation of the field.
     */
    private Switch.Orientation orientation;
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

    public TunnelEntrance(Switch.Orientation orientation) {
        super();
        state = new TunnelEntranceStateStraight();
        this.orientation = orientation;
    }

    @Override
    public Rail next(Rail from) {
        if(from == links.get(1)) {
            return links.get(0);
        } else if(linkToTunnel == null || state.nextStraight()) {
            return links.get(1);
        } else {
            return linkToTunnel;
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
        tunnel.checkEntrance(this);
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
     * @return
     */
    public boolean redLight() {
        return state.redLight();
    }

    public void arrive(Train train) throws CollisionException {
        super.arrive(train);
        if(tunnel != null && !tunnel.hasTrain(train)) {
            tunnel.enter(this, train);
        }
    }

    public void leave() {
        if(tunnel != null && tunnel.hasTrain(train)) {
            tunnel.leave(this, train);
        }
        super.leave();
    }

    // TODO: ezt kivenni, egy tunnel van midnenkihez azt beszeltuk, ezt ctorban kene megadni.
    public void setTunnel(Tunnel tunnel) {
        this.tunnel = tunnel;
    }

    public String getDrawData() {
        if (coach != null)
            return coach.getDrawData();
        return state.getDrawData();
    }
}
