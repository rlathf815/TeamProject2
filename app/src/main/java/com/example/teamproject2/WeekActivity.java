package com.example.teamproject2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class WeekActivity extends AppCompatActivity implements WeekFragment.wfragInterface{
    public static int[] current = new int[3];

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
        ActionBar ab = getSupportActionBar();
        switch (item.getItemId()) {
            case R.id.action_monthactivity:
                getSupportFragmentManager().beginTransaction().replace(R.id.fg, contentFragment.newInstance(current[0], current[1])).commit();
                ab.setTitle(current[0] + "년 " + current[1] + "월");
                return true;
            case R.id.action_weekactivity:
                startActivity(new Intent(this,WeekActivity.class));
                getSupportFragmentManager().beginTransaction().replace(R.id.fg, WeekFragment.newInstance(current[0], current[1])).commit();
                ab.setTitle(current[0] + "년 " + current[1] + "월");

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onPosSelected(int pos) {
        Toast.makeText(WeekActivity.this, pos,
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public void getYearMonth(int year, int month, int day) {
        current[0] = year;
        current[1] = month;
        current[2] = day;
        ActionBar ab = getSupportActionBar();
        ab.setTitle(current[0] + "년 " + current[1] + "월");
    }

    @Override
    public void mainGetDisplay(int w, int h) {

    }

    @Override
    public void setAppbar(int year, int month) {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(year + "년 " + month + "월");
    }
}
