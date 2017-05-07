package bem7trainsim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marci on 2017.03.18.
 * Represents the decoration on the table
 */
public class Decoration extends Field implements ActionListener {
    /**
     * Stores the state of the decoration
     */
    private boolean state;

    /**
     * Periodically redraws the field
     */
    protected Timer timerDraw = new Timer(10000, this);

    /**
     * Decoration constructor
     */
    public Decoration() {
        state = false;
    }

    /**
     * Changes the state of the decoration
     */
    public void changeState() {
        state = !state;
    }

    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    public FieldDrawData getDrawData() {
        List<String> layers = new ArrayList<>();
        if (state) {
            layers.add(" 1");
        }
        else {
            layers.add(" 2");
        }
        return new FieldDrawData(layers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        changeState();
    }
}
