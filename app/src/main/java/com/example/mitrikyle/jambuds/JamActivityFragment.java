package com.example.mitrikyle.jambuds;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class JamActivityFragment extends Fragment {
    private String mId;
    private Jam mJam;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getActivity().getIntent().getStringExtra(JamListFragment.EXTRA_JAM_ID);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_jam, container, false);
        final TextView jamTitleTextView = (TextView) v.findViewById(R.id.jamTitleTextView);
        final TextView jamNameTextView = (TextView) v.findViewById(R.id.jamNameTextView);
        final TextView jamDescriptionTextView = (TextView) v.findViewById(R.id.jamDescriptionTextView);
        final TextView jamLocationTextView = (TextView) v.findViewById(R.id.jamLocationTextView);
        final TextView jamDateTextView = (TextView) v.findViewById(R.id.jamDateTextView);
        final TextView jamGenreTextView = (TextView) v.findViewById(R.id.jamGenreTextView);
        Button jamJoinButton = (Button)v.findViewById(R.id.jamJoinButton);
        jamJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().put("current_jam", mJam);
                ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.d("JAM","USER HAS JAM");
                    }
                });
                Intent intent = new Intent(getActivity(),MyJamsActivity.class);
                startActivity(intent);
            }
        });
        ParseQuery<Jam> query = ParseQuery.getQuery(Jam.class);
        query.getInBackground(mId, new GetCallback<Jam>() {
            @Override
            public void done(Jam object, ParseException e) {
                mJam = object;
                jamTitleTextView.setText(mJam.getTitle());
                mJam.getCreator().fetchIfNeededInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        jamNameTextView.setText(((ParseUser)object).getUsername());
                    }
                });
                jamDescriptionTextView.setText(mJam.getDescription());
                jamLocationTextView.setText(mJam.getLocation());
                jamDateTextView.setText(mJam.getDate());
                jamGenreTextView.setText(mJam.getGenre());
            }
        });

        return v;
    }
}
