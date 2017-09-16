package bernhardwebstudio.shadowtravelor.broadcast;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import bernhardwebstudio.shadowtravelor.MainActivity;
import bernhardwebstudio.shadowtravelor.data.Container;
import bernhardwebstudio.shadowtravelor.data.Route;
import bernhardwebstudio.shadowtravelor.database.DBHelper;
import bernhardwebstudio.shadowtravelor.service.PositionService;

/**
 * Created by Benedict on 16.09.2017.
 */

public class NetworkBroadcast extends BroadcastReceiver{

    @TargetApi(23)
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        String homeNetwork = preferences.getString("ssid", "HackZurich2017");


        String action = intent.getAction();
        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if(wifiMgr.isWifiEnabled()){
                WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
                String name = wifiInfo.getSSID();
                Container container = Container.instance();
                container.addObserver(new PositionService(context));
                if(name.equals(homeNetwork)){
                    container.stopTimer();
                    DBHelper helper = new DBHelper(context, DBHelper.DB_NAME, null, DBHelper.currentVersion);
                    helper.insertRoute(container.getRoute());
                    // reset Route
                    container.setRoute(new Route());
                }else{
                    container.startTimer();
                }
            }
        }
    }
}
