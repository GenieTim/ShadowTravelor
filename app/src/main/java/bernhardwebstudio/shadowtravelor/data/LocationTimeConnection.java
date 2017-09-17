package bernhardwebstudio.shadowtravelor.data;

import java.util.GregorianCalendar;

/**
 * Created by Tim on 16.09.2017.
 *
 * LocationTimeConnection class consisting of location and time
 * in order to be able to calculate time between 2 locations
 */
public class LocationTimeConnection {

    public LocationTimeConnection () {

    }

    public LocationTimeConnection(Location location, GregorianCalendar datetime) {
        this.location = location;
        this.datetime = datetime;
    }

    public LocationTimeConnection(Location location, GregorianCalendar datetime, int usage) {
        this.usedSmartphone = usage;
        this.location = location;
        this.datetime = datetime;
    }

    public LocationTimeConnection(Location location, GregorianCalendar datetime, double velocity) {
        this.location = location;
        this.datetime = datetime;
        this.velocity = velocity;
    }

    public LocationTimeConnection(Location location, GregorianCalendar datetime, int usage, double velocity) {
        this.location = location;
        this.datetime = datetime;
        this.usedSmartphone = usage;
        this.velocity = velocity;
    }

    public static final int NOT_USED = 0;
    public static final int MUSIC = 1;
    public static final int SCREEN = 3;

    private Location location;
    private GregorianCalendar datetime;
    private int usedSmartphone = NOT_USED;
    private double volume = 0;
    private double velocity = 0.0;

    public void setDatetime(GregorianCalendar datetime) {
        this.datetime = datetime;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setUsedSmartphone(int usedSmartphone) {
        this.usedSmartphone = usedSmartphone;
    }

    public void setVolume(double volume) {
        this.volume = volume;
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

    public int getUsedSmartphone() {
        return usedSmartphone;
    }

    public double getVolume() {
        return volume;
    }
}
