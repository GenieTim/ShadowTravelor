package bernhardwebstudio.shadowtravelor.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class BootService extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, NetworkChangedService.class);
        PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 6000, pending);

        /*Intent startServiceIntent = new Intent(context, PositionService.class);
        context.startService(startServiceIntent);*/
    }

}
