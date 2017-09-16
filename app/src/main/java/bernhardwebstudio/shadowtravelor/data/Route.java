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

    public ArrayList<LocationTimeConnection> getRoute() {
        return this.route;
    }

    public void calculateVelocity() {
        double velocity = 0;
        // set velocity for every except the last point (0 anyway)
        for (int i = 1; i < this.route.size() - 1; i++) {
            Location target = this.route.get(i + 1).getLocation();
            double dS1 = this.route.get(i - 1).getLocation().getDistanceTo(target) ;
            double dT1 = this.route.get(i + 1).getDatetime().getTimeInMillis() - this.route.get(i - 1).getDatetime().getTimeInMillis();
            double y1 = dS1/dT1;
            velocity = y1 * (this.route.get(i).getDatetime().getTimeInMillis() - this.route.get(i - 1).getDatetime().getTimeInMillis()) / dT1;
            this.route.get(i).setVelocity(velocity);
        }
    }

    public void calculateScore() {
        // 1 point or every minute travelling
        double tmp_score = (this.getRoute().get(this.getRoute().size() - 1).getDatetime().getTimeInMillis() - this.getRoute().get(0).getDatetime().getTimeInMillis()) / 60000;

    }
}
