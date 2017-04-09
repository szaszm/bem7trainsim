package bem7trainsim;
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

    public double howFar(TunnelEntrance te1, TunnelEntrance te2) {
        int height = fields.length;
        if(height == 0) return 0f;
        int width = fields[0].length;
        int te1x = 0, te1y=0, te2x=0, te2y=0;
        boolean te1found = false, te2found = false;
        for(int y = 0; y < height && (!te1found || !te2found); ++y) {
            for (int x = 0; x < width && (!te1found || !te2found); ++x) {
                if (fields[x][y] == te1) {
                    te1found = true;
                    te1x = x;
                    te1y = y;
                }

                if (fields[x][y] == te2) {
                    te2found = true;
                    te2x = x;
                    te2y = y;
                }
            }
        }

        return Math.sqrt(Math.pow(te2x - te1x, 2) + Math.pow(te2y - te1y, 2));
    }

    String getDrawData() {
        String result = "";
        for (int y = 0; y < fields.length; y++) {
            for (int x = 0; x < fields[y].length; x++) {
                result += fields[y][x].getDrawData();
            }
            result += "\n";
        }
        return result;
    }
}
