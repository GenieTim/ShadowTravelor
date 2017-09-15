package bernhardwebstudio.shadowtravelor.data;

import java.util.ArrayList;
/**
 * Created by Tim on 16.09.2017.
 */
public class Route {

    private ArrayList<LocationTimeConnection> route;
    private double score;

    public Route() {
        this.route = new ArrayList<LocationTimeConnection>();
        this.score = 0.0;
    }

    public void add(LocationTimeConnection element){
        this.route.add(element);
    }

    public void setScore(double score){
        this.score = score;
    }

    public double getScore(){
        return this.score;
    }
}
