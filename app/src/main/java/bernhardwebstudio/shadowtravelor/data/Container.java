package bernhardwebstudio.shadowtravelor.data;

/**
 * Created by Benedict on 16.09.2017.
 */

public class Container {

    private static Container container = null;
    private Route route;

    private Container(){

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
}
