package com.example.mitrikyle.jambuds;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class CreateJamActivityFragment extends Fragment {

    public final static String EXTRA_MESSAGE = "com.example.mitrikyle.jambuds.MESSAGE";

    public CreateJamActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_jam, container, false);
        final EditText jamTitleEditText = (EditText) v.findViewById(R.id.titleEditText);
        Button createJamButton = (Button)v.findViewById(R.id.jamCreateButton);
        createJamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jamTitle = jamTitleEditText.getText().toString();
                Jam jam = new Jam();
                jam.setTitle(jamTitle);
                jam.setCreator(ParseUser.getCurrentUser());
                jam.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.d("JAM", "THE JAM IS TOASTED");
                    }
                });
                Intent intent = new Intent(getActivity(),JamListActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
