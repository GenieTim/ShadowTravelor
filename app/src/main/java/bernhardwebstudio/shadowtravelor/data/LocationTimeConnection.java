package bernhardwebstudio.shadowtravelor.data;

import java.util.GregorianCalendar;

/**
 * Created by Tim on 16.09.2017.
 */
public class LocationTimeConnection {

    public LocationTimeConnection () {

    }

    public LocationTimeConnection(Location location, GregorianCalendar datetime) {
        this.location = location;
        this.datetime = datetime;
    }

    public LocationTimeConnection(Location location, GregorianCalendar datetime, double velocity) {
        this.location = location;
        this.datetime = datetime;
        this.velocity = velocity;
    }

    private Location location;
    private GregorianCalendar datetime;
    private double velocity;

    public void setDatetime(GregorianCalendar datetime) {
        this.datetime = datetime;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public GregorianCalendar getDatetime() {
        return datetime;
    }

    public Location getLocation() {
        return location;
    }

    public double getVelocity() {
        return velocity;
    }
}
