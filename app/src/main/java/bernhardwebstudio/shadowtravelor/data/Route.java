package bernhardwebstudio.shadowtravelor.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * Created by Tim on 16.09.2017.
 */
public class Route {

    private ArrayList<LocationTimeConnection> route;
    private double score;
    private GregorianCalendar date;

    public Route() {
        this.route = new ArrayList<LocationTimeConnection>();
        this.score = 0.0;
        this.date = new GregorianCalendar();
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

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void sort(){
        Collections.sort(route, new Comparator<LocationTimeConnection>() {
            @Override
            public int compare(LocationTimeConnection locationTimeConnection, LocationTimeConnection t1) {
                return locationTimeConnection.getDatetime().compareTo(t1.getDatetime());
            }
        });
    }
}
