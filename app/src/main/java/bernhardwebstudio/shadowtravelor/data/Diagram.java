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

    public Diagram(Route route) {
        this.route = route;
    }

    public Diagram(RouteHistory routeHistory) {
        this.routeHistory = routeHistory;
    }

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

    private DataPoint[] drawRoute() {
        this.route.sort();
        DataPoint[] datapoints = new DataPoint[this.route.getRoute().size()];
        for (int i = 0; i < this.route.getRoute().size(); i++) {
            datapoints[i] = new DataPoint(this.route.getRoute().get(i).getDatetime().getTimeInMillis() / 10000, this.route.getRoute().get(i).getVelocity());
        }
        return datapoints;
    }

    private DataPoint[] drawRouteHistory() {
        DataPoint[] datapoints = new DataPoint[this.routeHistory.getRoutes().size()];
        for (int i = 0; i < this.routeHistory.getRoutes().size(); i++) {
            datapoints[i] = new DataPoint(this.routeHistory.getRoutes().get(i).getDate().getTimeInMillis() / 10000, this.routeHistory.getRoutes().get(i).getScore());
        }
        return datapoints;

    }
}
