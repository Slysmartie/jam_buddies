package com.example.mitrikyle.jambuds;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CreateJamActivity extends AppCompatActivity {

    /**
     * Trying to take message from CreateJamActivityFragment and display it
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // wtf setSupportActionBar(toolbar);

        // Get  the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(CreateJamActivityFragment.EXTRA_MESSAGE);
        setContentView(R.layout.activity_create_jam);
    }


}
