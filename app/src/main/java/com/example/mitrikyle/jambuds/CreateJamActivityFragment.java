package com.example.mitrikyle.jambuds;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
        final CheckBox pianoCheckBox = (CheckBox)v.findViewById(R.id.createPianoCheckBox);
        final CheckBox violinCheckBox = (CheckBox)v.findViewById(R.id.createViolinCheckBox);
        final CheckBox guitarCheckBox = (CheckBox)v.findViewById(R.id.createGuitarCheckBox);
        final CheckBox classicalCheckBox = (CheckBox)v.findViewById(R.id.createClassicalCheckBox);
        final CheckBox popCheckBox = (CheckBox)v.findViewById(R.id.createPopCheckbox);
        final CheckBox jazzCheckBox = (CheckBox)v.findViewById(R.id.createJazzCheckbox);
        final String genre, instrument;
        if (pianoCheckBox.isChecked()){
            instrument = "Piano";
        } else if (guitarCheckBox.isChecked()){
            instrument = "Guitar";
        } else {
            instrument = "Violin";
        }
        if (classicalCheckBox.isChecked()){
            genre = "Classical";
        } else if (popCheckBox.isChecked()){
            genre = "Pop";
        }else  {
            genre = "Jazz";
        }

        final EditText locationEditText = (EditText)v.findViewById(R.id.locationEditText);
        final EditText dateEditText = (EditText)v.findViewById(R.id.dateEditText);
        final EditText timeEditText = (EditText)v.findViewById(R.id.timeEditText);
        Button createJamButton = (Button)v.findViewById(R.id.jamCreateButton);
        createJamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jamTitle = jamTitleEditText.getText().toString();
                Jam jam = new Jam();
                jam.setTitle(jamTitle);
                jam.setCreator(ParseUser.getCurrentUser());
                jam.setInstrument(instrument);
                jam.setGenre(genre);
                jam.setLocation(locationEditText.getText().toString());
                jam.setDate(dateEditText.getText().toString());
                jam.setTime(timeEditText.getText().toString());
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
