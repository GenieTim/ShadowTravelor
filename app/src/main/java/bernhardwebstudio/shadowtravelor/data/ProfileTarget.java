package bernhardwebstudio.shadowtravelor.data;

import java.util.GregorianCalendar;

/**
 * Created by Tim on 16.09.2017.
 */

public class ProfileTarget {

    private Location location;

    private GregorianCalendar time;

    public GregorianCalendar getTime() {
        return time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }

}
