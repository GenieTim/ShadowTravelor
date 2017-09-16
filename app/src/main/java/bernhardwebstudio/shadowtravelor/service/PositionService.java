package bernhardwebstudio.shadowtravelor.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import bernhardwebstudio.shadowtravelor.data.Container;
import bernhardwebstudio.shadowtravelor.data.LocationTimeConnection;

import static java.security.AccessController.getContext;

public class PositionService implements Observer {

    Context context;
    private Thread t;

    public PositionService(Context context) {
        this.context = context;
    }


    @Override
    public void update(Observable observable, Object o) {
        if(Container.instance().hasTimerStarted()){
            t = new LocationThread(context);
            t.start();
        }else{
            t.interrupt();
        }
    }

    public class LocationThread extends Thread{

        Context context;

        public LocationThread(Context context){
            this.context = context;
        }

        @Override
        public void run(){
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    Container.instance().getRoute().
                            add(new LocationTimeConnection(new bernhardwebstudio.shadowtravelor.data.Location(location.getLatitude(), location.getLongitude()), new GregorianCalendar()));
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {}

                public void onProviderEnabled(String provider) {}

                public void onProviderDisabled(String provider) {}
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }
}

/*AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NetworkChangedService.class);
        PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 3000, pending);*/