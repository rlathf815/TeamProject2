package com.example.teamproject2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class WeekActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.action_monthactivity:
                getSupportFragmentManager().beginTransaction().replace(R.id.fg, contentFragment.newInstance(" ", " ")).commit();
            case R.id.action_weekactivity:
                getSupportFragmentManager().beginTransaction().replace(R.id.fg, WeekFragment.newInstance(" ", " ")).commit();

        }
        return super.onOptionsItemSelected(item);
    }
}
