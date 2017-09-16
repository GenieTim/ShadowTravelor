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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import bernhardwebstudio.shadowtravelor.adapter.RowAdapter;
import bernhardwebstudio.shadowtravelor.data.Container;
import bernhardwebstudio.shadowtravelor.data.Diagram;
import bernhardwebstudio.shadowtravelor.data.Location;
import bernhardwebstudio.shadowtravelor.data.LocationTimeConnection;
import bernhardwebstudio.shadowtravelor.data.ProfileDay;
import bernhardwebstudio.shadowtravelor.data.ProfileTarget;
import bernhardwebstudio.shadowtravelor.data.Route;
import bernhardwebstudio.shadowtravelor.data.RouteHistory;
import bernhardwebstudio.shadowtravelor.data.SampleData;
import bernhardwebstudio.shadowtravelor.data.SampleRoute;
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


        profileList = (ListView) findViewById(R.id.prof_list_view);
        setSpinnerAdapter();
        selectDateSpinner.setOnItemSelectedListener(selectedListener);
    }


    public void setSpinnerAdapter(){
        //for final version
        this.helper = new DBHelper(MainActivity.this, DBHelper.DB_NAME, null, DBHelper.currentVersion);
        this.allRoutes = helper.getAllRoutes();
        Log.d("TEST allRoutes size", String.valueOf(allRoutes.size()));
        //for testing and demo

        ArrayAdapter<String> dateSelection = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        for(int i=0; i<allRoutes.size(); i++){
            SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
            fmt.setCalendar(allRoutes.get(i).getDate());
            String dateFormatted = fmt.format(allRoutes.get(i).getDate().getTime());

            dateSelection.add(dateFormatted);
        }
        selectDateSpinner.setAdapter(dateSelection);

        ArrayAdapter<ProfileDay> profileAdapter = new RowAdapter(MainActivity.this, R.layout.row_item);
        for (int i = 0; i < 7; i++) {
            ProfileDay pd = new ProfileDay();
            pd.setWeekday(i);
            for (int j = 0; j < 2; j++) {
                ProfileTarget target = new ProfileTarget();
                target.setTime(new GregorianCalendar(2017, 9, i, 7, 0));
                target.setLocation(new Location(495, 284));
                pd.addTarget(target);
            }
            profileAdapter.add(pd);
        }
        profileList.setAdapter(profileAdapter);
        profileList.setOnItemClickListener(itemClickListener);
    }


    @Override
    protected void onStart(){
        super.onStart();

        // draw Graph of RouteHistory
        GraphView graph = (GraphView) findViewById(R.id.view_route_history_stats);
        //TextView title = (TextView) v.findViewById(R.id.graph_title);
        //title.setText(R.string.route_history_title);
        RouteHistory rh = helper.getRouteHistory();
        Diagram diagram = new Diagram(rh);
        graph.getGridLabelRenderer().setVerticalAxisTitle(getResources().getString(R.string.route_history_vertical_axis));
        graph.getGridLabelRenderer().setHorizontalAxisTitle(getResources().getString(R.string.route_history_horizontal_axis));
        // activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);
        graph.addSeries(diagram.draw());

        drawRouteGraph(0, findViewById(android.R.id.content));
    }


    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (i == 0) {
                Intent intent = new Intent(MainActivity.this, ImproveActivity.class);
                startActivity(intent);
            }
        }
    };

    AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            // load graphic of selected day
            if (allRoutes.size() > 0) {
                View v = getLayoutInflater().inflate(R.layout.statistics_graphic, null);
                GraphView graph = (GraphView) v.findViewById(R.id.graph);
                TextView title = (TextView) v.findViewById(R.id.graph_title);
                Route route = allRoutes.get(i);
                drawRouteGraph(i, view);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private void drawRouteGraph(int i, View view) {
        //View v = getLayoutInflater().inflate(R.layout.statistics_graphic, null);
        GraphView graph = (GraphView) findViewById(R.id.view_route_stats);
        graph.getViewport().setScalable(true);
        graph.getGridLabelRenderer().setVerticalAxisTitle(getResources().getString(R.string.route_vertical_axis));
        graph.getGridLabelRenderer().setHorizontalAxisTitle(getResources().getString(R.string.route_horizontal_axis));

        //TextView title = (TextView) v.findViewById(R.id.graph_title);
        Route route = allRoutes.get(i);
        if (route == null) {
            Log.e("TEST", "route null");
            return;
        } else {
            Log.d("AllRoute lenght", String.valueOf(allRoutes.size()));
            Log.d("Route length", String.valueOf(route.getRoute().size()));
        }
        //title.setText(String.valueOf(route.getScore()));
        Diagram diagram = new Diagram(route);
        graph.addSeries(diagram.draw());
        //View oldView = view.findViewById(R.id.view_route_stats);
        
        /*
        if (oldView != null) {
            ViewGroup parent = (ViewGroup) oldView.getParent();
            parent.removeView(oldView);
            parent.addView(v);
        } else {
            Log.d("SHIT", "nop old view");

        }*/
    }

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
