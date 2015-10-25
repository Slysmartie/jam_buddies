package com.example.mitrikyle.jambuds;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class JamListFragment extends ListFragment {
    public static final String EXTRA_JAM_ID = "com.exmaple.mitrikyle.jambuds.JAM_ID";
    private static final String TAG =  "JamListFragment";

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
        jamIntent.putExtra(EXTRA_JAM_ID, jam.getObjectId() );
        startActivity(jamIntent); // MIGHT BE BROKEN RIP

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
        /*
        int location = getActivity().getIntent().getIntExtra("location", 1); // defaults to best dining hall
        Bundle bundle = getArguments();
        mQuery = bundle.getString("query", null);
        ParseQuery<FoodItem> query = ParseQuery.getQuery(FoodItem.class);
        query.whereEqualTo("location", location);
        if (mQuery!=null) {
            Log.d(TAG, "Query: " + mQuery);
            query.whereStartsWith("title", mQuery);
            // TODO query parse with search
        }
        query.findInBackground(new FindCallback<FoodItem>() {
            @Override
            public void done(List<FoodItem> list, ParseException e) {
                if (e == null) {
                    FoodItemAdapter adapter = new FoodItemAdapter(list);
                    setListAdapter(adapter);
                    Log.d(TAG, "Query is done");
                } else {
                    Log.d("FoodItem", "Error: " + e.getMessage());
                }
            }
        });
    } */
        ParseQuery<Jam> query = ParseQuery.getQuery(Jam.class);
        query.findInBackground(new FindCallback<Jam>() {
            @Override
            public void done(List<Jam> objects, ParseException e) {
                JamItemAdapter adapter = new JamItemAdapter(objects);
                setListAdapter(adapter);
            }
        });
        /*
        ArrayList<String> stuff = new ArrayList<String>();
        stuff.add("Smarties");
        stuff.add("Electric Scooter");
        stuff.add("Piano and Viola");
        setListAdapter(new JamItemAdapter(stuff)); */
    }

}
