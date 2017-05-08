package bem7trainsim;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kristóf on 2017. 05. 07..
 * Stores the layers to be drawn to a field
 */
public class FieldDrawData {

    /**
     * Stores string representation of each layer to be drawn
     */
    private List<String> layers;

    /**
     * FieldDrawData constructor
     * @param l The layers to be drawn
     */
    public FieldDrawData(List<String> l) {
        layers=l;
    }

    /**
     * Layer string getter
     * @param i The index of the layer
     * @return layer string representation
     */
    public String getLayer(int i) {
        return layers.get(i);
    }

    /**
     * Layers length getter
     * @return The number of layers
     */
    public int getNumberOfLayers(){
        return layers.size();
    }

    /**
     * Draws the field
     * @param g Graphics object
     * @param x The horizontal coordinates
     * @param y The vertical coordinates
     * @param width The width of a field
     * @param height The height of a field
     */
    public void draw(Graphics g, int x, int y, int width, int height) {
        for (String layer : layers) {
            g.drawImage(getImage(layer), x * width, y * height, width, height, null);
        }
    }

    /**
     * Loaded image cache
     */
    static HashMap<String, BufferedImage> images;

    static {
        try {
            String path = "Piskels/";
            images = new HashMap<>();
            images.put("═",ImageIO.read(new File(path + "/SimpleRails/SimpleRailEW.png")));
            images.put("║",ImageIO.read(new File(path + "/SimpleRails/SimpleRailNS.png")));

            images.put("╚",ImageIO.read(new File(path + "/Curves/CurveNE.png")));
            images.put("╝",ImageIO.read(new File(path + "/Curves/CurveNW.png")));
            images.put("╔",ImageIO.read(new File(path + "/Curves/CurveSE.png")));
            images.put("╗",ImageIO.read(new File(path + "/Curves/CurveSW.png")));

            images.put("╬",ImageIO.read(new File(path + "/CrossRail.png")));

            images.put(" 1",ImageIO.read(new File(path + "/Decoration/Grass1.png")));
            images.put(" 2",ImageIO.read(new File(path + "/Decoration/Grass2.png")));

            images.put("═É",ImageIO.read(new File(path + "/Stations/downStationHorizontalBlue.png")));
            images.put("═Ö",ImageIO.read(new File(path + "/Stations/downStationHorizontalGreen.png")));
            images.put("═I",ImageIO.read(new File(path + "/Stations/downStationHorizontalRed.png")));
            images.put("═Á",ImageIO.read(new File(path + "/Stations/downStationHorizontalYellow.png")));
            images.put("║É",ImageIO.read(new File(path + "/Stations/downStationVerticallBlue.png")));
            images.put("║Ö",ImageIO.read(new File(path + "/Stations/downStationVerticalGreen.png")));
            images.put("║I",ImageIO.read(new File(path + "/Stations/downStationVerticalRed.png")));
            images.put("║Á",ImageIO.read(new File(path + "/Stations/downStationVerticalYellow.png")));

            images.put("═é",ImageIO.read(new File(path + "/Stations/upStationHorizontalBlue.png")));
            images.put("═ö",ImageIO.read(new File(path + "/Stations/upStationHorizontalGreen.png")));
            images.put("═i",ImageIO.read(new File(path + "/Stations/upStationHorizontalRed.png")));
            images.put("═á",ImageIO.read(new File(path + "/Stations/upStationHorizontalYellow.png")));
            images.put("║é",ImageIO.read(new File(path + "/Stations/upStationVerticallBlue.png")));
            images.put("║ö",ImageIO.read(new File(path + "/Stations/upStationVerticalGreen.png")));
            images.put("║i",ImageIO.read(new File(path + "/Stations/upStationVerticalRed.png")));
            images.put("║á",ImageIO.read(new File(path + "/Stations/upStationVerticalYellow.png")));

            images.put("┘│",ImageIO.read(new File(path + "/Switches/NorthRightCurve.png")));
            images.put("│┘",ImageIO.read(new File(path + "/Switches/NorthRightStraight.png")));
            images.put("└│",ImageIO.read(new File(path + "/Switches/NorthLeftCurve.png")));
            images.put("│└",ImageIO.read(new File(path + "/Switches/NorthLeftStraight.png")));
            images.put("┌│",ImageIO.read(new File(path + "/Switches/SouthRightCurve.png")));
            images.put("│┌",ImageIO.read(new File(path + "/Switches/SouthRightStraight.png")));
            images.put("┐│",ImageIO.read(new File(path + "/Switches/SouthLeftCurve.png")));
            images.put("│┐",ImageIO.read(new File(path + "/Switches/SouthLeftStraight.png")));
            images.put("┐─",ImageIO.read(new File(path + "/Switches/WestRightCurve.png")));
            images.put("─┐",ImageIO.read(new File(path + "/Switches/WestRightStraight.png")));
            images.put("┘─",ImageIO.read(new File(path + "/Switches/WestLeftCurve.png")));
            images.put("─┘",ImageIO.read(new File(path + "/Switches/WestLeftStraight.png")));
            images.put("└─",ImageIO.read(new File(path + "/Switches/EastRightCurve.png")));
            images.put("─└",ImageIO.read(new File(path + "/Switches/EastRightStraight.png")));
            images.put("┌─",ImageIO.read(new File(path + "/Switches/EastLeftCurve.png")));
            images.put("─┌",ImageIO.read(new File(path + "/Switches/EastLeftStraight.png")));

            images.put("─┌",ImageIO.read(new File(path + "/switchEastLeftStraight.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an image by key
     * @param layer The key of the image
     * @return The requested Image object
     */
    static private Image getImage(String layer) {
        if (images.containsKey(layer))
            return images.get(layer);
        return images.get(" ");
    }

}
