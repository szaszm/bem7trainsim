package bem7trainsim;
/**
 * Created by marci on 2017.03.17..
 */
public class TunnelEntrance extends Rail {
    private TunnelEntranceState state;
    private Rail linkToTunnel;
    private Tunnel tunnel;

    public TunnelEntrance() {
        super();
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
        tunnel.checkEntrance(this);
    }

    public void setState(TunnelEntranceState state) {
        this.state = state;
    }

    public boolean redLight() {
        return state.redLight();
    }

    public void arrive(Train train) throws TrainCollisionException {
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

    public void setTunnel(Tunnel tunnel) {
        this.tunnel = tunnel;
    }
}
