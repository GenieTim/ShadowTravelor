package bernhardwebstudio.shadowtravelor.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class NetworkChangedService extends Service{

    public NetworkChangedService(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
