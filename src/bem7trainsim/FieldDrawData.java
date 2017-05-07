package bem7trainsim;

import java.util.List;

/**
 * Created by Kristóf on 2017. 05. 07..
 */
public class FieldDrawData {

    private List<String> layers;

    public FieldDrawData(List<String> l){
        layers=l;
    }

    public String getLayer(){
       //TODO
    }

    public int getNumberOfLayers(){
        return layers.size();
    }



}
