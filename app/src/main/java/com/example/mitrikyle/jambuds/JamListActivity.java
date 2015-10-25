package com.example.mitrikyle.jambuds;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.appevents.AppEventsLogger;

public class JamListActivity extends AppCompatActivity {
// muh comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.create_jam){
            startActivity(new Intent(getApplicationContext(), CreateJamActivity.class));
            return true;
        }

        if (id == R.id.jam){
            startActivity(new Intent(getApplicationContext(), JamActivity.class));
            return true;
        }

        if (id == R.id.login){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            return true;
        }

        if (id == R.id.main){
            startActivity(new Intent(getApplicationContext(), JamListActivity.class));
            return true;
        }

        if (id == R.id.profile){
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            return true;

        }

        if (id== R.id.experience){
            startActivity(new Intent(getApplicationContext(), ExperienceActivity.class));
            return true;
        }

        if (id == R.id.my_jams){
            startActivity(new Intent(getApplicationContext(), MyJamsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
