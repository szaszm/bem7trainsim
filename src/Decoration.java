/**
 * Created by marci on 2017.03.18..
 */
public class Decoration extends Field {
    private boolean state;

    public Decoration() {
        state = false;
    }

    public void changeState() {
        state = !state;
    }
}
