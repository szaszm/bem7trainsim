package bem7trainsim;

import java.util.ArrayList;
import java.util.List;

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
    public Table(Field[][] fields, List<TunnelEntrance> tunnelEntrances) {
        this.fields = fields;
        // az alagút kezdetben bejáratok nélkül kerül a pályára, DE MINDIG VAN EGY ALAGÚT CSAK MAX NINCS BEJÁRATA!
        this.tunnel = new Tunnel(this, new ArrayList<TunnelEntrance>());
        //beállítjuk az alagútbejáratokat, hogy ismerjék az alagutat
        for(int i = 0; i < tunnelEntrances.size(); i++){
            tunnelEntrances.get(i).setTunnel(this.tunnel);
        }
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
        return Math.abs(te2x - te1x) + Math.abs(te2y - te1y) - 1; //mert a sarok mindkettőben benne van
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

    public enum Direction {
        Up, Left, Down, Right
    };

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

    public void switchAt(int x, int y) throws CannotSwitchException{
        try {
            ((Switch) (fields[y][x])).change();
        } catch (ClassCastException e) {
            throw new CannotSwitchException("That is not a switch.");
        }
    }

    public void buildAt(int x, int y) throws CannotBuildException{
        try {
            ((TunnelEntrance)(fields[y][x])).click();
        } catch (ClassCastException e) {
            throw new CannotBuildException("That is not a tunnel entrance.");
        }
    }
}
