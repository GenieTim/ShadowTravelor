package bernhardwebstudio.shadowtravelor.data;

import java.util.GregorianCalendar;

import bernhardwebstudio.shadowtravelor.database.DBHelper;

/**
 * Created by Cihad on 16.09.17.
 */

public class SampleData {

    public SampleData(DBHelper helper) {

        Route rotas = new Route();

        Location technoPark = new Location(47.389161, 8.5150677);
        Location zurichHB = new Location(47.3783, 8.52);
        Location zurichAirport = new Location(47.4504, 8.5619);
        Location zurichLindenhof = new Location(47.3721811, 8.5413182);
        Location zurichZoo = new Location(47.3845, 8.5747);


        GregorianCalendar Morning = new GregorianCalendar(2017, 9, 16, 8, 00);
        GregorianCalendar Noon = new GregorianCalendar(2017, 9, 16, 13, 00);
        GregorianCalendar afterNoon = new GregorianCalendar(2017, 9, 16, 16, 00);
        GregorianCalendar Evening = new GregorianCalendar(2017, 9, 16, 19, 00);
        GregorianCalendar Midnight = new GregorianCalendar(2017, 9, 16, 23, 00);


        LocationTimeConnection lct1 = new LocationTimeConnection(technoPark, Morning);
        LocationTimeConnection lct2 = new LocationTimeConnection(zurichHB, Noon);
        LocationTimeConnection lct3 = new LocationTimeConnection(zurichAirport, afterNoon);
        LocationTimeConnection lct4 = new LocationTimeConnection(zurichLindenhof, Evening);
        LocationTimeConnection lct5 = new LocationTimeConnection(zurichZoo, Midnight);

        rotas.add(lct1);
        rotas.add(lct2);
        rotas.add(lct3);
        rotas.add(lct4);
        rotas.add(lct4);
        rotas.add(lct5);

        helper.insertRoute(rotas);
    }

}
