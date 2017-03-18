/**
 * Created by marci on 2017.03.18..
 */
public class SimpleRail extends Rail {

    public SimpleRail() {
        super();
    }

    @Override
    public Rail next(Rail from) {
        return links.indexOf(from) == 0 ? links.get(1) : links.get(0);
    }
}
