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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileActivityFragment extends Fragment {

    public ProfileActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        final CheckBox pianoCheckBox = (CheckBox)v.findViewById(R.id.profilePianoCheckBox);
        final CheckBox violinCheckBox = (CheckBox)v.findViewById(R.id.profileViolinCheckBox);
        final CheckBox guitarCheckBox = (CheckBox)v.findViewById(R.id.profileGuitarCheckBox);
        final CheckBox classicalCheckBox = (CheckBox)v.findViewById(R.id.profilePianoCheckBox);
        final CheckBox popCheckBox = (CheckBox)v.findViewById(R.id.profilePianoCheckBox);
        final CheckBox jazzCheckBox = (CheckBox)v.findViewById(R.id.profilePianoCheckBox);
        Button profileNextButton = (Button)v.findViewById(R.id.profileNextButton);
        profileNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = ParseUser.getCurrentUser();
                if (pianoCheckBox.isChecked()){
                    user.put("Instrument", "Piano");
                }
                if (violinCheckBox.isChecked()){
                    user.put("Instrument", "Violin");
                }
                if (guitarCheckBox.isChecked()){
                    user.put("Instrument", "Guitar");
                }
                if (classicalCheckBox.isChecked()){
                    user.put("Genre", "Classic");
                }
                if (popCheckBox.isChecked()){
                    user.put("Genre", "Pop");
                }
                if (jazzCheckBox.isChecked()){
                    user.put("Genre", "Jazz");
                }
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.d("USER INSTRUMENTS", "SAVEDF");
                    }
                });
                Intent intent = new Intent(getActivity(), ExperienceActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
