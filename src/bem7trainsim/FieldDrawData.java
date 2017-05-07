package bem7trainsim;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
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
        float[] scales = {1f, 1f, 1f, 0.5f};
        float[] offsets = new float[4];
        RescaleOp rop = new RescaleOp(scales, offsets, null);
        for (String layer :
                layers) {
            g.drawImage(getImage(layer), x * width, y * height, width, height, null);
        }
    }

    static private final BufferedImage valto = new BufferedImage(50,50, TYPE_CUSTOM);

    static private Image getImage(String layer) {
        if (layer == "Á")
            return valto;
        return valto;
    }

}
