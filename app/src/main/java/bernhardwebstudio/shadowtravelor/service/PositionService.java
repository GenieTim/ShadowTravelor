package bernhardwebstudio.shadowtravelor.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.view.Display;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import bernhardwebstudio.shadowtravelor.data.Container;
import bernhardwebstudio.shadowtravelor.data.LocationTimeConnection;

import static java.security.AccessController.getContext;

@RequiresApi(20)
public class PositionService implements Observer {

    Context context;
    private Thread t;

    public PositionService(Context context) {
        this.context = context;
    }


    @Override
    public void update(Observable observable, Object o) {
        if (Container.instance().hasTimerStarted()) {
            t = new LocationThread(context);
            t.start();
        } else {
            t.interrupt();
        }
    }

    public class LocationThread extends Thread {

        Context context;

        public LocationThread(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    LocationTimeConnection point = new LocationTimeConnection(new bernhardwebstudio.shadowtravelor.data.Location(location.getLatitude(), location.getLongitude()), new GregorianCalendar());

                    int phoneUsage = LocationTimeConnection.NOT_USED;
                    DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
                    AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    if (am.isMusicActive()) {
                        phoneUsage = LocationTimeConnection.MUSIC;
                        point.setVolume(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
                    }
                    for (Display display : dm.getDisplays()) {
                        if (display.getState() != Display.STATE_OFF) {
                            phoneUsage = LocationTimeConnection.SCREEN;
                        }
                    }

                    point.setUsedSmartphone(phoneUsage);
                    Container.instance().getRoute().
                            add(point);

                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };

            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            } catch (SecurityException e) {

            }
        }
    }
}

/*AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NetworkChangedService.class);
        PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 3000, pending);*/