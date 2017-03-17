import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by marci on 2017.03.17..
 */
public class Tunnel {
    private Collection<Rail> rails;
    private Collection<TunnelEntrance> tunnelEntrances;

    private int howManyInside;

    public Tunnel() {
        howManyInside = 0;
        rails = new ArrayList<>();
    }

    public void enter(TunnelEntrance te) {
        howManyInside++;
    }

    public void leave(TunnelEntrance te) {
        howManyInside--;
    }

    public boolean checkEntrance(TunnelEntrance te) {
        // TODO
        return howManyInside <= 0;
    }
}
