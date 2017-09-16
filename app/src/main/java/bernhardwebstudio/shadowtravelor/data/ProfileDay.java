package bernhardwebstudio.shadowtravelor.data;

import java.util.ArrayList;

/**
 * Created by Tim on 16.09.2017.
 */

public class ProfileDay {

    public ProfileDay(){

    }

    private int weekday;

    private ArrayList<ProfileTarget> route = new ArrayList<ProfileTarget>();

    public void addTarget(ProfileTarget target) {
        this.route.add(target);
    }

    public ArrayList<ProfileTarget> getRoute() {
        return route;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }
}
