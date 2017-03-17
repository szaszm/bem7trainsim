/**
 * Created by marci on 2017.03.17..
 */
public class Table {
    private Field[][] fields;
    private Tunnel tunnel;

    public Table(Field[][] fields) {
        this.fields = fields;
        tunnel = null;
    }

    public float howFar(TunnelEntrance te1, TunnelEntrance te2) {
        return 1.0f;
    }
}
