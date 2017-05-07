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
    static private BufferedImage straightHorizontal;
    static private BufferedImage straightHorizontal;
    static private BufferedImage straightHorizontal;
    static private BufferedImage straightHorizontal;
    static private BufferedImage straightHorizontal;
    static private BufferedImage straightHorizontal;



    static {
        try {
            valto = ImageIO.read(new File("random.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static private Image getImage(String layer) {
        if (layer == "Á")
            return valto;
        return valto;
    }

}
