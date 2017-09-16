package bernhardwebstudio.shadowtravelor.data;

import java.util.ArrayList;

/**
 * Created by Tim on 16.09.2017.
 */

public class Profile {

    private ArrayList<ProfileDay> calendar;

    public ArrayList<ProfileDay> getCalendar() {
        return calendar;
    }

    public void addDay(ProfileDay day) {
        this.calendar.add(day);
    }
}
