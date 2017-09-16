package bernhardwebstudio.shadowtravelor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
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
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
    private ShareActionProvider mShareActionProvider;
    ArrayList<ProfileDay> profileDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectDateSpinner = (Spinner) findViewById(R.id.select_date_spinner);


        profileList = (ListView) findViewById(R.id.prof_list_view);
        setSpinnerAdapter();
        selectDateSpinner.setOnItemSelectedListener(selectedListener);
    }


    public void setSpinnerAdapter() {
        //for final version
        this.helper = new DBHelper(MainActivity.this, DBHelper.DB_NAME, null, DBHelper.currentVersion);
        this.allRoutes = helper.getAllRoutes();
        Log.d("TEST allRoutes size", String.valueOf(allRoutes.size()));
        //for testing and demo

        ArrayAdapter<String> dateSelection = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        for (int i = 0; i < allRoutes.size(); i++) {
            SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
            fmt.setCalendar(allRoutes.get(i).getDate());
            Log.d("DATETIME", String.valueOf(allRoutes.get(i).getDate().getTime()));
            String dateFormatted = fmt.format(allRoutes.get(i).getDate().getTime());
            dateSelection.add(dateFormatted);
        }
        selectDateSpinner.setAdapter(dateSelection);


        profileDays = new ArrayList<ProfileDay>();
        for (int i = 1; i < 6; i++) {
            ProfileDay pd = new ProfileDay();
            pd.setWeekday(i);
            for (int j = 0; j < 1; j++) {
                ProfileTarget target = new ProfileTarget();
                target.setTime(new GregorianCalendar(2017, 9, i, 7+j, 0));
                target.setLocation(new Location(495, 284));
                pd.addTarget(target);
            }
            for (int j = 0; j < 1; j++) {
                ProfileTarget target = new ProfileTarget();
                target.setTime(new GregorianCalendar(2017, 9, i, 16-j, 0));
                target.setLocation(new Location(395, 251));
                pd.addTarget(target);
            }
            profileDays.add(pd);

        }

        RowAdapter profileAdapter = new RowAdapter(this, R.layout.row_item, profileDays);
        profileList.setAdapter(profileAdapter);
        profileList.setOnItemClickListener(itemClickListener);
    }


    @Override
    protected void onStart() {
        super.onStart();

        // draw Graph of RouteHistory
        GraphView graph = (GraphView) findViewById(R.id.view_route_history_stats);
        //TextView title = (TextView) v.findViewById(R.id.graph_title);
        //title.setText(R.string.route_history_title);
        RouteHistory rh = helper.getRouteHistory();
        Diagram diagram = new Diagram(rh);
        graph.setTitleTextSize(75);
        graph.getGridLabelRenderer().setVerticalAxisTitle(getResources().getString(R.string.route_history_vertical_axis));
        graph.getGridLabelRenderer().setHorizontalAxisTitle(getResources().getString(R.string.route_history_horizontal_axis));
        graph.setTitle(getResources().getString(R.string.route_history_title));

        // activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);
        graph.addSeries(diagram.draw());

        GraphView dayGraph = (GraphView) findViewById(R.id.view_route_stats);
        drawRouteGraph(0, dayGraph);
    }


    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this, ImproveActivity.class);
            startActivity(intent);
        }
    };

    AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Log.d("TEST", "Something selected");
            // load graphic of selected day
            if (allRoutes.size() > 0) {
                //View v = getLayoutInflater().inflate(R.layout.statistics_graphic, null);
                GraphView graph = (GraphView) findViewById(R.id.view_route_stats);

                if (graph != null) {
                    drawRouteGraph(i, graph);
                } else {
                    Log.e("TEST", "NO Graph");
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    private void drawRouteGraph(int i, GraphView graph) {
        graph.getGridLabelRenderer().setVerticalAxisTitle(getResources().getString(R.string.route_vertical_axis));
        graph.getGridLabelRenderer().setHorizontalAxisTitle(getResources().getString(R.string.route_horizontal_axis));

        Route route = this.allRoutes.get(i);

        TextView last = (TextView) findViewById(R.id.last_score);
        last.setText(getResources().getString(R.string.last_score) + ": " + String.valueOf(route.getScore()));

        Diagram diagram = new Diagram(route);
        graph.getViewport().setScalable(true);

        graph.removeAllSeries();
        graph.addSeries(diagram.draw());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        // Return true to display menu
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
        } else if (id == R.id.action_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);

            mShareActionProvider.setShareIntent(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
