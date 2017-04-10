package bem7trainsim;
/**
 * Created by marci on 2017.03.17..
 */
public class Table {
    private Field[][] fields;
    private Tunnel tunnel;

    /**
     * Creates table with the given fields
     * @param fields The array of fields
     */
    public Table(Field[][] fields) {
        this.fields = fields;
        //TODO: initialization of tunnel
        tunnel = null;
    }

    /**
     * Gets the difference in length between two tunnel entrances
     * @param te1 TunnelEntrance1
     * @param te2 TunnelEntrance2
     * @return Length
     */
    public int howFar(TunnelEntrance te1, TunnelEntrance te2) {
        int height = fields.length;
        if(height == 0) return 0;
        int width = fields[0].length;
        int te1x = 0, te1y=0, te2x=0, te2y=0;
        boolean te1found = false, te2found = false;
        // searches for the given tunnel entrances
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
        // returns the difference between the coordinates
        return Math.abs(te2x - te1x) + Math.abs(te2y - te1y);
    }

    /**
     * Gets the data to be drawn
     * @return Data as String
     */
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
