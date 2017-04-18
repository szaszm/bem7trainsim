package bem7trainsim;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marci on 2017.03.17.
 * Represents the table, where the player plays
 */
public class Table {

    /**
     * The fields contained by the table, in a two-dimensional grid
     */
    private Field[][] fields;

    /**
     * The tunnel on the table
     */
    private Tunnel tunnel;

    /**
     * Creates a table with the given fields and tunnel entrances
     * @param fields The array of fields of the table
     * @param tunnelEntrances The tunnel entrances of the table
     */
    public Table(Field[][] fields, List<TunnelEntrance> tunnelEntrances) {
        this.fields = fields;

         /**
         * In the beginning the tunnel is set without any entrances, because there is always a tunnel
         * but it is possible that it does not have any entrances.
         */
        this.tunnel = new Tunnel(this, new ArrayList<TunnelEntrance>());

        //Linking the entrances to the tunnel, so the entrances know about their tunnel.
        for(int i = 0; i < tunnelEntrances.size(); i++){
            tunnelEntrances.get(i).setTunnel(this.tunnel);
        }
    }

    /**
     * Gets the difference in length between two tunnel entrances
     * @param te1 TunnelEntrance1
     * @param te2 TunnelEntrance2
     * @return Length of the tunnel
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
        //(-1):because there is a field which is in both
        return Math.abs(te2x - te1x) + Math.abs(te2y - te1y) - 1;

    }

    /**
     * Gets the data to be drawn
     * @return Data as String
     */
    String getDrawData() {
        String result = "#";
        for(int i = 0; i < fields[0].length; i++){
            result += Integer.toString(i + 1);
        }
        result += "#\n";
        for (int y = 0; y < fields.length; y++) {
            result += Integer.toString(y + 1);
            for (int x = 0; x < fields[y].length; x++) {
                result += fields[y][x].getDrawData();
            }
            result += Integer.toString(y + 1);
            result += "\n";
        }
        result += "#";
        for(int i = 0; i < fields[0].length; i++){
            result += Integer.toString(i + 1);
        }
        result += "#\n";
        return result;
    }

    /**
     * The direction of the coaches
     */
    public enum Direction {
        Up, Left, Down, Right
    };

    /**
     * Computing the direction determined by two fields.
     * @param from The starting field
     * @param to The destination field
     * @return the direction determined by the two fields
     */
    Direction getDirection(Field from, Field to) {
        int x = -1, y = -1;

        outerloop:
        for(y = 0; y < fields.length; ++y) {
            for(x = 0; x < fields[y].length; ++x) {
                if(fields[y][x] == from) {
                    break outerloop;
                }
            }
        }

        if(y > 0 && to == fields[y - 1][x]) {
            return Direction.Up;
        }

        if(x > 0 && to == fields[y][x - 1]) {
            return Direction.Left;
        }

        if(y < fields.length - 1 && to == fields[y + 1][x]) {
            return Direction.Down;
        }

        if(x < fields[y].length - 1 && to == fields[y][x + 1]) {
            return Direction.Right;
        }

        return null;
    }

    /**
     * Trying to switch the field in the given coordinates
     * @param x x-coordinate of the field
     * @param y y-coordinate of the field
     * @throws CannotSwitchException thrown when the switch cannot switch
     */
    public void switchAt(int x, int y) throws CannotSwitchException{
        ((Switch)(fields[y][x])).change();
    }


    /**
     * Trying to build an entrance in the field in the given coordinates
     * @param x x-coordinate of the field
     * @param y y-coordinate of the field
     * @throws CannotBuildException  thrown when the entrance cannot be built
     */
    public void buildAt(int x, int y) throws CannotBuildException{
        ((TunnelEntrance)(fields[y][x])).click();
    }
}
