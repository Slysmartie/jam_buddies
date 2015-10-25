package com.example.mitrikyle.jambuds;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyJamsActivityFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // if user not logged in ,send to login
        if (ParseUser.getCurrentUser() == null){
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        setupAdapter();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent jamIntent = new Intent(getActivity(), JamActivity.class);
        Jam jam = (Jam) getListAdapter().getItem(position);
        jamIntent.putExtra(JamListFragment.EXTRA_JAM_ID, jam.getObjectId() );
        startActivity(jamIntent); // MIGHT BE BROKEN RIP <-- boss sux

    }

    private class JamItemAdapter extends ArrayAdapter<Jam> {
        public JamItemAdapter(List<Jam> jamItems){
            super(getActivity(), 0, jamItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_jam, null);
            }

            Jam item = getItem(position);

            TextView textView = (TextView) convertView.findViewById(R.id.jamTitleTextView);
            textView.setText(item.getTitle());
            return convertView;
        }
    }

    public void setupAdapter() {
        /*ParseQuery<Jam> query = ParseQuery.getQuery(Jam.class);
        query.whereEqualTo()
        query.findInBackground(new FindCallback<Jam>() {
            @Override
            public void done(List<Jam> objects, ParseException e) {
                JamItemAdapter adapter = new JamItemAdapter(objects);
                setListAdapter(adapter);
            }
        });*/
        final ArrayList<Jam> list = new ArrayList<>();
        Jam jam = (Jam)ParseUser.getCurrentUser().get("current_jam");
        jam.fetchIfNeededInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                list.add( (Jam) object);
            }
        });
        JamItemAdapter adapter = new JamItemAdapter(list);
        setListAdapter(adapter);
    }
}
