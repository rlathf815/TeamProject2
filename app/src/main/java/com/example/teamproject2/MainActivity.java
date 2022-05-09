package com.example.teamproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements contentFragment.fragInterface {
    public static int[] current = new int[3];
    public static int gridviewWidth, gridviewHeight;
    public static Context mContext;
    private ViewPager2 vpPager;
    //PagerAdapter fragmentViewPagerAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mContext = this;

        vpPager = findViewById(R.id.vpPager);
        FragmentStateAdapter adapter = new PagerAdapter(this);
        vpPager.setAdapter(adapter);
        vpPager.setCurrentItem(10,false);

        TextView nowDate = (TextView) findViewById(R.id.YearMonth);
        nowDate.setText(current[0] + "년 " + current[1] + "월");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_weekactivity:

                // Do Activity menu item stuff here
                return true;

            default:
                break;
        }

        return false;
    }
    public void onDateSelected(String year, String month, String date) {
        Toast.makeText(MainActivity.this, year + "." + month + "." + date,
                Toast.LENGTH_SHORT).show();
    }
    public void getYearMonth(int year, int month, int day)
    {
        //int[] date = new int[3];
        System.out.println("-------------------------------------------------------------------getYearmonth호출됨-----------------------");
        current[0] = year;
        current[1] = month;
        current[2] = day;
        TextView nowDate = (TextView) findViewById(R.id.YearMonth);
        nowDate.setText(current[0] + "년 " + current[1] + "월");
    }


    @Override
    public void mainGetDisplay(int w, int h) {

        gridviewWidth = w;
        gridviewHeight = h;
    }

    //FirstFragment.getYearMonth();

}