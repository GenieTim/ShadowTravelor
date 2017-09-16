package bernhardwebstudio.shadowtravelor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import bernhardwebstudio.shadowtravelor.data.Diagram;
import bernhardwebstudio.shadowtravelor.data.LocationTimeConnection;
import bernhardwebstudio.shadowtravelor.data.Route;
import bernhardwebstudio.shadowtravelor.data.RouteHistory;
import bernhardwebstudio.shadowtravelor.data.SampleData;
import bernhardwebstudio.shadowtravelor.database.DBHelper;


public class MainActivity extends ActionBarActivity {

    private Spinner selectDateSpinner;
    private ListView profileList;
    private DBHelper helper;
    private ArrayList<Route> allRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectDateSpinner = (Spinner) findViewById(R.id.select_date_spinner);


    }

    @Override
    protected void onStart(){
        super.onStart();

        ArrayAdapter<String> dateSelection = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item);

        this.helper = new DBHelper(MainActivity.this, DBHelper.DB_NAME, null, DBHelper.currentVersion);
        allRoutes = helper.getAllRoutes();
        for(int i=0; i<allRoutes.size(); i++){
            dateSelection.add(allRoutes.get(i).getDate().toString());
        }
        selectDateSpinner.setAdapter(dateSelection);
        selectDateSpinner.setOnItemSelectedListener(selectedListener);

        profileList = (ListView) findViewById(R.id.profile_list_view);
        //profileList.setOnItemClickListener(itemClickListener);

        // draw Graph of RouteHistory
        View v = getLayoutInflater().inflate(R.layout.statistics_graphic, null);
        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        TextView title = (TextView) v.findViewById(R.id.graph_title);
        title.setText(R.string.route_history_title);
        RouteHistory rh = helper.getRouteHistory();
        Diagram diagram = new Diagram(rh);
        graph.addSeries(diagram.draw());
        graph.getGridLabelRenderer().setVerticalAxisTitle(getResources().getString(R.string.route_history_vertical_axis));
        graph.getGridLabelRenderer().setHorizontalAxisTitle(getResources().getString(R.string.route_history_horizontal_axis));
        View oldView = findViewById(R.id.view_route_history_stats);
        if (oldView != null) {
            ViewGroup parent = (ViewGroup) oldView.getParent();
            parent.removeView(oldView);
            parent.addView(v);
        } else {
            Log.e("SHIT", "No parent");
        }
    }

/*
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(i==0){
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        }
    };
*/
    AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            // load graphic of selected day
            View v = getLayoutInflater().inflate(R.layout.statistics_graphic, null);
            GraphView graph = (GraphView) v.findViewById(R.id.graph);
            TextView title = (TextView) v.findViewById(R.id.graph_title);
            Route route = allRoutes.get(i);
            title.setText(String.valueOf(route.getScore()));
            Diagram diagram = new Diagram(route);
            graph.addSeries(diagram.draw());
            View oldView = view.findViewById(R.id.view_route_stats);
            if (oldView != null) {
                ViewGroup parent = (ViewGroup) oldView.getParent();
                parent.removeView(oldView);
                parent.addView(v);
            } else {
                Log.d("SHIT", "nop old view");
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //return true;
            Intent intent = new Intent(getBaseContext(), SettingsActivity.class);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
