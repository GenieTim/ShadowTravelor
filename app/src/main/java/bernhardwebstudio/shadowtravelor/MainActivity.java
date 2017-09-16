package bernhardwebstudio.shadowtravelor;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import bernhardwebstudio.shadowtravelor.data.Diagram;
import bernhardwebstudio.shadowtravelor.data.LocationTimeConnection;
import bernhardwebstudio.shadowtravelor.data.Route;
import bernhardwebstudio.shadowtravelor.database.DBHelper;


public class MainActivity extends ActionBarActivity {

    private Spinner selectDateSpinner;
    private ListView profileList;
    private DBHelper helper;
    private ArrayList<GregorianCalendar> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        selectDateSpinner = (Spinner) findViewById(R.id.select_date_spinner);
        ArrayAdapter<String> dateSelection = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item);

        this.helper = new DBHelper(MainActivity.this, DBHelper.DB_NAME, null, DBHelper.currentVersion);
        ArrayList<LocationTimeConnection> al = helper.getLocationTimeConnection();
        for(int i=0; i<al.size(); i++){
            dateSelection.add(al.get(i).getDatetime().toString());
        }
        selectDateSpinner.setAdapter(dateSelection);
        selectDateSpinner.setOnItemSelectedListener(selectedListener);

        profileList = (ListView) findViewById(R.id.profile_list_view);
        profileList.setOnItemClickListener(itemClickListener);


    }


    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    };

    AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            // load graphic of selected day
            View v = getLayoutInflater().inflate(R.layout.statistics_graphic, null);
            GraphView graph = (GraphView) v.findViewById(R.id.graph);
            Route route = helper.getRouteById(i);
            Diagram diagram = new Diagram(route);
            graph.addSeries(diagram.draw());
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
