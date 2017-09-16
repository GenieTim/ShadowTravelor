package bernhardwebstudio.shadowtravelor.data;

import java.util.GregorianCalendar;

/**
 * Created by Cihad on 16.09.17.
 */

public class SampleData {

    Route rotas = new Route();

    Location technoPark = new Location(47.389161,8.5150677);
    Location zurichHB = new Location(47.3783,8.52);
    Location zurichAirport = new Location(47.4504,8.5619);
    Location zurichLindenhof = new Location(47.3721811,8.5413182);
    Location zurichZoo = new Location(47.3845,8.5747);


    GregorianCalendar Morning = new GregorianCalendar(2017,9,16,08,00);
    GregorianCalendar Noon = new GregorianCalendar(2017,9,16,13,00);
    GregorianCalendar afterNoon = new GregorianCalendar(2017,9,16,16,00);
    GregorianCalendar Evening = new GregorianCalendar(2017,9,16,19,00);
    GregorianCalendar Midnight = new GregorianCalendar(2017,9,16,23,00);


    LocationTimeConnection techParkNow = new LocationTimeConnection(technoPark, Morning );
    LocationTimeConnection techParkNow = new LocationTimeConnection(zurichHB, Noon );
    LocationTimeConnection techParkNow = new LocationTimeConnection(zurichAirport, afterNoon );
    LocationTimeConnection techParkNow = new LocationTimeConnection(zurichLindenhof, Evening );
    LocationTimeConnection techParkNow = new LocationTimeConnection(zurichZoo, Midnight );

}
