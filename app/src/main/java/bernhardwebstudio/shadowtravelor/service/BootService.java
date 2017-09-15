package bernhardwebstudio.shadowtravelor.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class BootService extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startServiceIntent = new Intent(context, PositionService.class);
        context.startService(startServiceIntent);
    }

}
