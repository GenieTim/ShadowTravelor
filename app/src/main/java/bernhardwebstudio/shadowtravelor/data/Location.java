package bernhardwebstudio.shadowtravelor.data;

/**
 * Created by Tim on 16.09.2017.
 */
public class Location {
    public Location() {

    }

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistanceTo(Location location) {
        return Math.sqrt(
                Math.pow(this.latitude - location.getLatitude(), 2)
                + Math.pow(this.longitude - location.getLongitude(), 2)
        );
    }
}
