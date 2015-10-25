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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ExperienceListFragment extends Fragment {


    private static final String TAG =  "ExperienceListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View convertView = inflater.inflate(R.layout.fragment_experience, container, false);

            TextView textView = (TextView) convertView.findViewById(R.id.instrumentTextView);
            textView.setText((String)ParseUser.getCurrentUser().get("Instrument"));
            final RadioButton beginnerCheckBox = (RadioButton)convertView.findViewById(R.id.beginnerCheckBox);
            final RadioButton intermediateCheckBox = (RadioButton)convertView.findViewById(R.id.intermediateCheckBox);
            final RadioButton advancedCheckBox = (RadioButton)convertView.findViewById(R.id.advancedCheckBox);
            Button experienceSubmitButton = (Button)convertView.findViewById(R.id.experienceSubmitButton);
            experienceSubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (beginnerCheckBox.isChecked()) {
                        ParseUser.getCurrentUser().put("experience", "Beginner");
                    } else if (intermediateCheckBox.isChecked()) {
                        ParseUser.getCurrentUser().put("experience", "Intermediate");
                    } else if (advancedCheckBox.isChecked()) {
                        ParseUser.getCurrentUser().put("experience", "Advanced");
                    }

                    startActivity(new Intent(getActivity(), JamListActivity.class));

                    ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Log.d("TAG", "EXPERIENCE SAVED");
                        }
                    });
                }
            });
            return convertView;
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

    }
}
