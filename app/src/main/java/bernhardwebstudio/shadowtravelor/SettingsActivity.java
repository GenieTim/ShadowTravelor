package bernhardwebstudio.shadowtravelor;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import static android.net.ConnectivityManager.*;
import static java.security.AccessController.getContext;

/**
 * Created by lenovo on 16.09.2017.
 */
@TargetApi(23)
public class SettingsActivity extends Activity {
    public SettingsActivity(Context context, Intent i){
    NetworkInfo netInfo = i.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
    if( ConnectivityManager.TYPE_WIFI == netInfo.getType()) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        SharedPreferences preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("ssid", info.getSSID());
    }
    }
}