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

            images.put("═É",ImageIO.read(new File(path + "/Stations/DownStationHorizontalBlue.png")));
            images.put("═Ö",ImageIO.read(new File(path + "/Stations/DownStationHorizontalGreen.png")));
            images.put("═I",ImageIO.read(new File(path + "/Stations/DownStationHorizontalRed.png")));
            images.put("═Á",ImageIO.read(new File(path + "/Stations/DownStationHorizontalYellow.png")));
            images.put("║É",ImageIO.read(new File(path + "/Stations/DownStationVerticalBlue.png")));
            images.put("║Ö",ImageIO.read(new File(path + "/Stations/DownStationVerticalGreen.png")));
            images.put("║I",ImageIO.read(new File(path + "/Stations/DownStationVerticalRed.png")));
            images.put("║Á",ImageIO.read(new File(path + "/Stations/DownStationVerticalYellow.png")));

            images.put("═é",ImageIO.read(new File(path + "/Stations/UpStationHorizontalBlue.png")));
            images.put("═ö",ImageIO.read(new File(path + "/Stations/UpStationHorizontalGreen.png")));
            images.put("═i",ImageIO.read(new File(path + "/Stations/UpStationHorizontalRed.png")));
            images.put("═á",ImageIO.read(new File(path + "/Stations/UpStationHorizontalYellow.png")));
            images.put("═g",ImageIO.read(new File(path + "/Stations/UpStationHorizontalGrey.png")));
            images.put("║é",ImageIO.read(new File(path + "/Stations/UpStationVerticalBlue.png")));
            images.put("║ö",ImageIO.read(new File(path + "/Stations/UpStationVerticalGreen.png")));
            images.put("║i",ImageIO.read(new File(path + "/Stations/UpStationVerticalRed.png")));
            images.put("║á",ImageIO.read(new File(path + "/Stations/UpStationVerticalYellow.png")));
            images.put("║g",ImageIO.read(new File(path + "/Stations/UpStationVerticalGrey.png")));

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

            images.put("t═",ImageIO.read(new File(path + "/TunnelEntrances/TunnelEntranceNotConstructedEastWest.png")));
            images.put("T═",ImageIO.read(new File(path + "/TunnelEntrances/TunnelEntranceBuiltEastWest.png")));
            images.put("TU═",ImageIO.read(new File(path + "/TunnelEntrances/TunnelEntranceUnderConstructionEastWest.png")));
            images.put("TR═",ImageIO.read(new File(path + "/TunnelEntrances/TunnelEntranceExitWest.png")));
            images.put("t║",ImageIO.read(new File(path + "/TunnelEntrances/TunnelEntranceNotConstructedNorthSouth.png")));
            images.put("T║",ImageIO.read(new File(path + "/TunnelEntrances/TunnelEntranceBuiltNorthSouth.png")));
            images.put("TU║",ImageIO.read(new File(path + "/TunnelEntrances/TunnelEntranceUnderConstructionNorthSouth.png")));
            images.put("TR║",ImageIO.read(new File(path + "/TunnelEntrances/TunnelEntranceExitNorth.png")));

            images.put("p═",ImageIO.read(new File(path + "/Wagons/RedWagonEastWest.png")));
            images.put("s═",ImageIO.read(new File(path + "/Wagons/YellowWagonEastWest.png")));
            images.put("z═",ImageIO.read(new File(path + "/Wagons/GreenWagonEastWest.png")));
            images.put("k═",ImageIO.read(new File(path + "/Wagons/BlueWagonEastWest.png")));
            images.put("x═",ImageIO.read(new File(path + "/Wagons/GreyWagonEastWest.png")));
            images.put("P═",ImageIO.read(new File(path + "/Wagons/RedWagonEastWest.png")));
            images.put("S═",ImageIO.read(new File(path + "/Wagons/YellowWagonEastWest.png")));
            images.put("Z═",ImageIO.read(new File(path + "/Wagons/GreenWagonEastWest.png")));
            images.put("K═",ImageIO.read(new File(path + "/Wagons/BlueWagonEastWest.png")));
            images.put("F═",ImageIO.read(new File(path + "/Wagons/BlackWagonEastWest.png")));

            images.put("p║",ImageIO.read(new File(path + "/Wagons/RedWagonNorthSouth.png")));
            images.put("s║",ImageIO.read(new File(path + "/Wagons/YellowWagonNorthSouth.png")));
            images.put("z║",ImageIO.read(new File(path + "/Wagons/GreenWagonNorthSouth.png")));
            images.put("k║",ImageIO.read(new File(path + "/Wagons/BlueWagonNorthSouth.png")));
            images.put("x║",ImageIO.read(new File(path + "/Wagons/GreyWagonNorthSouth.png")));
            images.put("P║",ImageIO.read(new File(path + "/Wagons/RedWagonNorthSouth.png")));
            images.put("S║",ImageIO.read(new File(path + "/Wagons/YellowWagonNorthSouth.png")));
            images.put("Z║",ImageIO.read(new File(path + "/Wagons/GreenWagonNorthSouth.png")));
            images.put("K║",ImageIO.read(new File(path + "/Wagons/BlueWagonNorthSouth.png")));
            images.put("F║",ImageIO.read(new File(path + "/Wagons/BlackWagonNorthSouth.png")));

            images.put("p└",ImageIO.read(new File(path + "/Wagons/RedWagonNorthLeft.png")));
            images.put("s└",ImageIO.read(new File(path + "/Wagons/YellowWagonNorthLeft.png")));
            images.put("z└",ImageIO.read(new File(path + "/Wagons/GreenWagonNorthLeft.png")));
            images.put("k└",ImageIO.read(new File(path + "/Wagons/BlueWagonNorthLeft.png")));
            images.put("x└",ImageIO.read(new File(path + "/Wagons/GreyWagonNorthLeft.png")));
            images.put("P└",ImageIO.read(new File(path + "/Wagons/RedWagonNorthLeft.png")));
            images.put("S└",ImageIO.read(new File(path + "/Wagons/YellowWagonNorthLeft.png")));
            images.put("Z└",ImageIO.read(new File(path + "/Wagons/GreenWagonNorthLeft.png")));
            images.put("K└",ImageIO.read(new File(path + "/Wagons/BlueWagonNorthLeft.png")));
            images.put("F└",ImageIO.read(new File(path + "/Wagons/BlackWagonNorthLeft.png")));

            images.put("p┘",ImageIO.read(new File(path + "/Wagons/RedWagonNorthRight.png")));
            images.put("s┘",ImageIO.read(new File(path + "/Wagons/YellowWagonNorthRight.png")));
            images.put("z┘",ImageIO.read(new File(path + "/Wagons/GreenWagonNorthRight.png")));
            images.put("k┘",ImageIO.read(new File(path + "/Wagons/BlueWagonNorthRight.png")));
            images.put("x┘",ImageIO.read(new File(path + "/Wagons/GreyWagonNorthRight.png")));
            images.put("P┘",ImageIO.read(new File(path + "/Wagons/RedWagonNorthRight.png")));
            images.put("S┘",ImageIO.read(new File(path + "/Wagons/YellowWagonNorthRight.png")));
            images.put("Z┘",ImageIO.read(new File(path + "/Wagons/GreenWagonNorthRight.png")));
            images.put("K┘",ImageIO.read(new File(path + "/Wagons/BlueWagonNorthRight.png")));
            images.put("F┘",ImageIO.read(new File(path + "/Wagons/BlackWagonNorthRight.png")));

            images.put("p┌",ImageIO.read(new File(path + "/Wagons/RedWagonSouthRight.png")));
            images.put("s┌",ImageIO.read(new File(path + "/Wagons/YellowWagonSouthRight.png")));
            images.put("z┌",ImageIO.read(new File(path + "/Wagons/GreenWagonSouthRight.png")));
            images.put("k┌",ImageIO.read(new File(path + "/Wagons/BlueWagonSouthRight.png")));
            images.put("x┌",ImageIO.read(new File(path + "/Wagons/GreyWagonSouthRight.png")));
            images.put("P┌",ImageIO.read(new File(path + "/Wagons/RedWagonSouthRight.png")));
            images.put("S┌",ImageIO.read(new File(path + "/Wagons/YellowWagonSouthRight.png")));
            images.put("Z┌",ImageIO.read(new File(path + "/Wagons/GreenWagonSouthRight.png")));
            images.put("K┌",ImageIO.read(new File(path + "/Wagons/BlueWagonSouthRight.png")));
            images.put("F┌",ImageIO.read(new File(path + "/Wagons/BlackWagonSouthRight.png")));

            images.put("p┐",ImageIO.read(new File(path + "/Wagons/RedWagonSouthLeft.png")));
            images.put("s┐",ImageIO.read(new File(path + "/Wagons/YellowWagonSouthLeft.png")));
            images.put("z┐",ImageIO.read(new File(path + "/Wagons/GreenWagonSouthLeft.png")));
            images.put("k┐",ImageIO.read(new File(path + "/Wagons/BlueWagonSouthLeft.png")));
            images.put("x┐",ImageIO.read(new File(path + "/Wagons/GreyWagonSouthLeft.png")));
            images.put("P┐",ImageIO.read(new File(path + "/Wagons/RedWagonSouthLeft.png")));
            images.put("S┐",ImageIO.read(new File(path + "/Wagons/YellowWagonSouthLeft.png")));
            images.put("Z┐",ImageIO.read(new File(path + "/Wagons/GreenWagonSouthLeft.png")));
            images.put("K┐",ImageIO.read(new File(path + "/Wagons/BlueWagonSouthLeft.png")));
            images.put("F┐",ImageIO.read(new File(path + "/Wagons/BlackWagonSouthLeft.png")));

            images.put(">═",ImageIO.read(new File(path + "/Wagons/LocomotiveEast.png")));
            images.put("<═",ImageIO.read(new File(path + "/Wagons/LocomotiveWest.png")));
            images.put("A║",ImageIO.read(new File(path + "/Wagons/LocomotiveNorth.png")));
            images.put("V║",ImageIO.read(new File(path + "/Wagons/LocomotiveSouth.png")));
            images.put("A└",ImageIO.read(new File(path + "/Wagons/LocomotiveEastRight.png")));
            images.put(">└",ImageIO.read(new File(path + "/Wagons/LocomotiveNorthLeft.png")));
            images.put("A┘",ImageIO.read(new File(path + "/Wagons/LocomotiveWestLeft.png")));
            images.put("<┘",ImageIO.read(new File(path + "/Wagons/LocomotiveNorthRight.png")));
            images.put("V┌",ImageIO.read(new File(path + "/Wagons/LocomotiveEastLeft.png")));
            images.put(">┌",ImageIO.read(new File(path + "/Wagons/LocomotiveSouthRight.png")));
            images.put("V┐",ImageIO.read(new File(path + "/Wagons/LocomotiveWestRight.png")));
            images.put("<┐",ImageIO.read(new File(path + "/Wagons/LocomotiveSouthLeft.png")));

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
