/**
 * Created by marci on 2017.03.17..
 */
public class TunnelEntrance extends Rail {
    private TunnelEntranceState state;
    private Tunnel tunnel;
    private Rail linkToTunnel;

    public TunnelEntrance(Tunnel tunnel) {
        super();
        this.tunnel = tunnel;
        state = new TunnelEntranceStateStraight();
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

    public void addLinkToTunnel(Rail rail) {
        this.linkToTunnel = rail;
    }

    public void click() {
        // TODO
    }

    public void setState(TunnelEntranceState state) {
        this.state = state;
    }

    public boolean redLight() {
        return state.redLight();
    }

    public void arrive(Train train) throws TrainCollisionException {
        super.arrive(train);
        // tunnel.enter(this);
    }

    public void leave() {
        // tunnel.leave(this);
    }
}
