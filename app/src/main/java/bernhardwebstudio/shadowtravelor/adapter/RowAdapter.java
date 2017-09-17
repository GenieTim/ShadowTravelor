package bernhardwebstudio.shadowtravelor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bernhardwebstudio.shadowtravelor.ImproveActivity;
import bernhardwebstudio.shadowtravelor.MainActivity;
import bernhardwebstudio.shadowtravelor.R;
import bernhardwebstudio.shadowtravelor.data.ProfileDay;

/**
 * Created by Benedict on 16.09.2017.
 */

public class RowAdapter extends ArrayAdapter<ProfileDay> {

    private String[] weekdays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    private ArrayList<ProfileDay> profileDays = new ArrayList<ProfileDay>();
    private Context context;
    private int resource;

    public RowAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    public RowAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
        this.resource = resource;

    }

    public RowAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<ProfileDay> objects) {
        super(context, resource, objects);
        profileDays = objects;
        this.context = context;
        this.resource = resource;
    }

    public RowAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull ArrayList<ProfileDay> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.resource = resource;
        this.profileDays = objects;
    }

    @Override
    public void add(ProfileDay pd){
        profileDays.add(pd);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(resource, parent, false);
        }
        TextView day = (TextView) convertView.findViewById(R.id.day_text_label);
        ListView pdl = (ListView) convertView.findViewById(R.id.profile_detail_list);

        pdl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ImproveActivity.class);
                getContext().startActivity(intent);
            }
        });

        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parent = (View) view.getParent();
                parent.callOnClick();
            }
        });

        ProfileDay pd = profileDays.get(position);
        day.setText(weekdays[pd.getWeekday()].toString());

        ArrayAdapter<String> profileAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        for(int i=0; i<pd.getRoute().size(); i++){


            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(pd.getRoute().get(i).getTime().getTimeInMillis());

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            String time = hour + ":";
            if(minute<10){
                time = time + "0" + minute;
            }else{
                time = time + minute;
            }
            String dest="";
            if(hour>10){
                dest = "ETH";
            }else{
                dest = "Home";
            }
            profileAdapter.add(time + " Uhr - " + dest);
        }
        pdl.setAdapter(profileAdapter);
        return convertView;
    }
}
