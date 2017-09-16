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
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.net.ConnectivityManager.*;

/**
 * Created by lenovo on 16.09.2017.
 */
@TargetApi(23)
public class SettingsActivity extends Activity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

    }

    /**
     * Saves SSID of used Wifi Hotspot in SharedPreferences
     */
    @Override
    protected void onStart() {
        super.onStart();
        Context context = this.getApplicationContext();
        final SharedPreferences preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        TextView tv = (TextView) this.findViewById(R.id.ssidInput);
        tv.setText(preferences.getString("SSID", "HackZurich2017"));

        //SharedPreferences.Editor editor = preferences.edit();
        //editor.putString("ssid", "HackZurich2017");

        tv.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("ssid", "HackZurich2017");
                    // TODO: navigate back
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Resets SSID in SharedPreferences
     */
    protected void reset() {
        Context context = this.getApplicationContext();
        Intent intent = getIntent();
        try {
            WifiManager netManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = netManager.getConnectionInfo();

            SharedPreferences preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("ssid", info.getSSID());
        } catch (Exception e) {
            Log.e("SHIT", e.getMessage());
        }
    }
}