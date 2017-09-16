package bernhardwebstudio.shadowtravelor.data;

import java.util.GregorianCalendar;

/**
 * Created by root on 16.09.17.
 */

public class SampleData {

    Route rotas = new Route();

    Location technoPark = new Location(47.389161,8.5150677);
    GregorianCalendar now = new GregorianCalendar(2017,9,16,13,38);
    LocationTimeConnection sampLtc = new LocationTimeConnection(technoPark,now );
}
