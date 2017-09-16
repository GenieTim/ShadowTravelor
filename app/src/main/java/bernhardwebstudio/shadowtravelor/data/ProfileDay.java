package bernhardwebstudio.shadowtravelor.data;

import java.util.ArrayList;

/**
 * Created by Tim on 16.09.2017.
 */

public class ProfileDay {

    private ArrayList<ProfileTarget> route;

    public void addTarget(ProfileTarget target) {
        this.route.add(target);
    }

    public ArrayList<ProfileTarget> getRoute() {
        return route;
    }
}
