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

            images.put("╚",ImageIO.read(new File(path + "/Curves/curveNE.png")));
            images.put("╝",ImageIO.read(new File(path + "/Curves/curveNW.png")));
            images.put("╔",ImageIO.read(new File(path + "/Curves/curveSE.png")));
            images.put("╗",ImageIO.read(new File(path + "/Curves/curveSW.png")));

            images.put("╬",ImageIO.read(new File(path + "/crossRail.png")));

            images.put(" 1",ImageIO.read(new File(path + "/Decoration/Grass1.png")));
            images.put(" 2",ImageIO.read(new File(path + "/Decoration/Grass2.png")));

            images.put("═É",ImageIO.read(new File(path + "/downStationHorizontalBlue.png")));
            images.put("═Ö",ImageIO.read(new File(path + "/downStationHorizontalGreen.png")));
            images.put("═I",ImageIO.read(new File(path + "/downStationHorizontalRed.png")));
            images.put("═Á",ImageIO.read(new File(path + "/downStationHorizontalYellow.png")));
            images.put("║É",ImageIO.read(new File(path + "/downStationVerticallBlue.png")));
            images.put("║Ö",ImageIO.read(new File(path + "/downStationVerticalGreen.png")));
            images.put("║I",ImageIO.read(new File(path + "/downStationVerticalRed.png")));
            images.put("║Á",ImageIO.read(new File(path + "/downStationVerticalYellow.png")));

            images.put("═é",ImageIO.read(new File(path + "/upStationHorizontalBlue.png")));
            images.put("═ö",ImageIO.read(new File(path + "/upStationHorizontalGreen.png")));
            images.put("═i",ImageIO.read(new File(path + "/upStationHorizontalRed.png")));
            images.put("═á",ImageIO.read(new File(path + "/upStationHorizontalYellow.png")));
            images.put("║é",ImageIO.read(new File(path + "/upStationVerticallBlue.png")));
            images.put("║ö",ImageIO.read(new File(path + "/upStationVerticalGreen.png")));
            images.put("║i",ImageIO.read(new File(path + "/upStationVerticalRed.png")));
            images.put("║á",ImageIO.read(new File(path + "/upStationVerticalYellow.png")));
            
            images.put("┘│",ImageIO.read(new File(path + "/switchNorthRightCurve.png")));
            images.put("│┘",ImageIO.read(new File(path + "/switchNorthRightStraight.png")));
            images.put("└│",ImageIO.read(new File(path + "/switchNorthLeftCurve.png")));
            images.put("│└",ImageIO.read(new File(path + "/switchNorthLeftStraight.png")));
            images.put("┌│",ImageIO.read(new File(path + "/switchSouthRightCurve.png")));
            images.put("│┌",ImageIO.read(new File(path + "/switchSouthRightStraight.png")));
            images.put("┐│",ImageIO.read(new File(path + "/switchSouthLeftCurve.png")));
            images.put("│┐",ImageIO.read(new File(path + "/switchSouthLeftStraight.png")));
            images.put("┐─",ImageIO.read(new File(path + "/switchWestRightCurve.png")));
            images.put("─┐",ImageIO.read(new File(path + "/switchWestRightStraight.png")));
            images.put("┘─",ImageIO.read(new File(path + "/switchWestLeftCurve.png")));
            images.put("─┘",ImageIO.read(new File(path + "/switchWestLeftStraight.png")));
            images.put("└─",ImageIO.read(new File(path + "/switchEastRightCurve.png")));
            images.put("─└",ImageIO.read(new File(path + "/switchEastRightStraight.png")));
            images.put("┌─",ImageIO.read(new File(path + "/switchEastLeftCurve.png")));
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
