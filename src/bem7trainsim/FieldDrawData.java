package bem7trainsim;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_CUSTOM;

/**
 * Created by Kristóf on 2017. 05. 07..
 */
public class FieldDrawData {

    private List<String> layers;

    public FieldDrawData(List<String> l) {
        layers = l;
    }

    public String getLayer(int i) {
        return layers.get(i);
    }

    public int getNumberOfLayers() {
        return layers.size();
    }

    public void draw(Graphics g, int x, int y, int width, int height) {
        for (String layer :
                layers) {
            g.drawImage(getImage(layer), x * width, y * height, width, height, null);
        }
    }

    static private BufferedImage straightHorizontal;
    static private BufferedImage straightVertical;
    static private BufferedImage curveNE;
    static private BufferedImage curveNW;
    static private BufferedImage curveSE;
    static private BufferedImage curveSW;
    static private BufferedImage cross;
    static private BufferedImage grass1;
    static private BufferedImage grass2;
    static private BufferedImage downStationHorizontalBlue;
    static private BufferedImage downStationHorizontalGreen;
    static private BufferedImage downStationHorizontalRed;
    static private BufferedImage downStationHorizontalYellow;
    static private BufferedImage downStationVerticallBlue;
    static private BufferedImage downStationVerticalGreen;
    static private BufferedImage downStationVerticalRed;
    static private BufferedImage downStationVerticalYellow;
    static private BufferedImage upStationHorizontalBlue;
    static private BufferedImage upStationHorizontalGreen;
    static private BufferedImage upStationHorizontalRed;
    static private BufferedImage upStationHorizontalYellow;
    static private BufferedImage upStationVerticallBlue;
    static private BufferedImage upStationVerticalGreen;
    static private BufferedImage upStationVerticalRed;
    static private BufferedImage upStationVerticalYellow;
    static private BufferedImage switchNorthRightCurve;
    static private BufferedImage switchNorthRightStraight;
    static private BufferedImage switchNorthLeftCurve;
    static private BufferedImage switchNorthLeftStraight;
    static private BufferedImage switchSouthRightCurve;
    static private BufferedImage switchSouthRightStraight;
    static private BufferedImage switchSouthLeftCurve;
    static private BufferedImage switchSouthLeftStraight;
    static private BufferedImage switchWestRightCurve;
    static private BufferedImage switchWestRightStraight;
    static private BufferedImage switchWestLeftCurve;
    static private BufferedImage switchWestLeftStraight;
    static private BufferedImage switchEastRightCurve;
    static private BufferedImage switchEastRightStraight;
    static private BufferedImage switchEastLeftCurve;
    static private BufferedImage switchEastLeftStraight;



    static {
        try {
            cross = ImageIO.read(new File("random.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static private Image getImage(String layer) {
        String[] parts = layer.split(" ");
        return cross; //valto;
    }

}
