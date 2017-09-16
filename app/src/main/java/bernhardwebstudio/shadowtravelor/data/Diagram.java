package bernhardwebstudio.shadowtravelor.data;

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
        DataPoint[] datapoints;
        if (this.route != null) {
            datapoints = this.drawRoute();
        } else if (this.routeHistory != null) {
            datapoints = this.drawRouteHistory();
        } else {
            throw new RuntimeException();
        }

        return new LineGraphSeries<>(datapoints);
    }

    private DataPoint[] drawRoute() {
        DataPoint[] datapoints = new DataPoint[this.route.getRoute().size()];
        for (int i = 0; i < this.route.getRoute().size(); i++) {
            datapoints[i] = new DataPoint(this.route.getRoute().get(i).getDatetime().getTimeInMillis(), this.route.getRoute().get(i).getVelocity());
        }
        return datapoints;
    }

    private DataPoint[] drawRouteHistory() {
        DataPoint[] datapoints = new DataPoint[this.route.getRoute().size()];
        for (int i = 0; i < this.route.getRoute().size(); i++) {
            datapoints[i] = new DataPoint(this.route.getRoute().get(i).getDatetime().getTimeInMillis(), this.route.getRoute().get(i).getVelocity());
        }
        return datapoints;

    }
}
