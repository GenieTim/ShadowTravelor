package bernhardwebstudio.shadowtravelor.data;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Benedict on 16.09.2017.
 */

public class Container extends Observable{

    private static Container container = null;
    private Route route;
    public long timerStarted = 0;
    private boolean hasTimerStarted = false;
    public ArrayList<Route> allRoutes = new ArrayList<Route>();

    private Container(){
        new SampleData();
    }

    public ArrayList<Route> getAllRoutes(){
        return allRoutes;
    }

    public static Container instance(){
        if(container==null){
            container = new Container();
        }
        return container;
    }

    public void setRoute(Route route){
        this.route = route;
    }

    public Route getRoute(){
        return route;
    }

    public void startTimer(){
        hasTimerStarted = true;
        setChanged();
        notifyObservers();
    }

    public void stopTimer(){
        hasTimerStarted = false;
        setChanged();
        notifyObservers();
    }

    public boolean hasTimerStarted(){
        return hasTimerStarted;
    }
}
