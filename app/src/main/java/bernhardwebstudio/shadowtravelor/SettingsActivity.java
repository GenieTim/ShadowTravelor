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

/**
 * Created by lenovo on 16.09.2017.
 */
@TargetApi(23)
public class SettingsActivity extends Activity {

    @Override
    protected void onStart() {
        super.onStart();

        Context context = this;
        Intent intent = getIntent();
        NetworkInfo netInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if (ConnectivityManager.TYPE_WIFI == netInfo.getType()) {
            WifiManager netManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = netManager.getConnectionInfo();

            SharedPreferences preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("ssid", info.getSSID());
        }
    }
}