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

    static private BufferedImage valto;

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
