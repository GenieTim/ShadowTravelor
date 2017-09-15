package bernhardwebstudio.shadowtravelor.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Tim on 16.09.2017.
 */
public class RouteHistory {
    private ArrayList<Route> routes;

    public RouteHistory(){
        this.routes = new ArrayList<Route>();
    }

    public void add(Route route){
        this.routes.add(route);
    }

    public void sort(){
        Collections.sort(routes, new Comparator<Route>() {
            @Override
            public int compare(Route route, Route t1) {
                return route.getDate().compareTo(t1.getDate());
            }
        });
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public Route getRoute(int i) {
        return this.routes.get(i);
    }
}
