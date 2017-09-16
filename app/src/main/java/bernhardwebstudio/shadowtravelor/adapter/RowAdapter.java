package bernhardwebstudio.shadowtravelor.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bernhardwebstudio.shadowtravelor.R;
import bernhardwebstudio.shadowtravelor.data.ProfileDay;

/**
 * Created by Benedict on 16.09.2017.
 */

public class RowAdapter extends ArrayAdapter<ProfileDay> {

    private ArrayList<ProfileDay> profileDays = new ArrayList<ProfileDay>();

    public RowAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public RowAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public RowAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<ProfileDay> objects) {
        super(context, resource, objects);
        profileDays = objects;
    }

    public RowAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public void add(ProfileDay pd){
        profileDays.add(pd);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
        }
        TextView day = (TextView) convertView.findViewById(R.id.day_text_label);
        ListView pdl = (ListView) convertView.findViewById(R.id.profile_detail_list);

        ProfileDay pd = profileDays.get(position);
        day.setText(pd.getWeekday());
        day.setText("Hello");

        ArrayAdapter<String> profileAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        for(int i=0; i<pd.getRoute().size(); i++){
            profileAdapter.add(pd.getRoute().get(i).getTime() + " - " + pd.getRoute().get(i).getLocation().getLatitude());
        }
        pdl.setAdapter(profileAdapter);
        return convertView;
    }
}
