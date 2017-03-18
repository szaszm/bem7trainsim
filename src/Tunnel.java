import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by marci on 2017.03.17..
 */
public class Tunnel {
    private Collection<Rail> rails;
    private Collection<TunnelEntrance> tunnelEntrances;
    private Table table;

    private int howManyInside;

    public Tunnel(Table table) {
        howManyInside = 0;
        rails = new ArrayList<>();
        this.table = table;
    }

    public void enter(TunnelEntrance te) {
        howManyInside++;
    }

    public void leave(TunnelEntrance te) {
        howManyInside--;
    }

    public boolean checkEntrance(TunnelEntrance te) {
        // TODO: track trains inside with directions
        return howManyInside <= 0;
    }
}
