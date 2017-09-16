package bernhardwebstudio.shadowtravelor.data;

import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Tim on 16.09.2017.
 */
public class Diagram {
    private Route route;
    private RouteHistory routeHistory;

    /**
     * Diagram Constructor with a single route
     * @param route
     */
    public Diagram(Route route) {
        this.route = route;
    }

    /**
     * Diagram Constructor for multiple routes, a route history
     * @param routeHistory
     */
    public Diagram(RouteHistory routeHistory) {
        this.routeHistory = routeHistory;
    }

    /**
     * translates route data in a format that is easier to work with Graphview
     * @return
     */
    public LineGraphSeries draw() {
        DataPoint[] datapoints = null;
        if (this.route != null) {
            datapoints = this.drawRoute();
        } else if (this.routeHistory != null) {
            datapoints = this.drawRouteHistory();
        } else {
            Log.e("TEST", "both drawable null");
            //throw new RuntimeException();
        }

        Log.d("TEST datapoints length", String.valueOf(datapoints.length));

        if (datapoints != null) {
            return new LineGraphSeries<>(datapoints);
        } else {
            return new LineGraphSeries<>();
        }
    }

    /**
     * Translates each entry in route into a DataPoint
     * @return
     */
    private DataPoint[] drawRoute() {
        this.route.sort();
        DataPoint[] datapoints = new DataPoint[this.route.getRoute().size()];
        for (int i = 0; i < this.route.getRoute().size(); i++) {
            datapoints[i] = new DataPoint(this.route.getRoute().get(i).getDatetime().getTimeInMillis() / 10000, this.route.getRoute().get(i).getVelocity());
        }
        return datapoints;
    }

    /**
     * Does the same as drawRoute but with route history
     * @return
     */
    private DataPoint[] drawRouteHistory() {
        DataPoint[] datapoints = new DataPoint[this.routeHistory.getRoutes().size()];
        for (int i = 0; i < this.routeHistory.getRoutes().size(); i++) {
            datapoints[i] = new DataPoint(this.routeHistory.getRoutes().get(i).getScore(), this.routeHistory.getRoutes().get(i).getDate().getTimeInMillis() / 10000);
        }
        return datapoints;

    }
}
