package com.example.teamproject2;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
<<<<<<< HEAD
import androidx.fragment.app.Fragment;
=======
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
>>>>>>> 9846d0ce794a44cfd33c3723d6fd26dedc1de22c
=======
>>>>>>> parent of 9846d0c (Viewpager2)
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements contentFragment.fragInterface {
    public static int[] current = new int[3];
    public static int gridviewWidth, gridviewHeight;
    public static Context mContext;
<<<<<<< HEAD
<<<<<<< HEAD

=======
    private ViewPager2 vpPager;
    //PagerAdapter fragmentViewPagerAdapter;
>>>>>>> 9846d0ce794a44cfd33c3723d6fd26dedc1de22c
=======
>>>>>>> parent of 9846d0c (Viewpager2)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nowDate;
        nowDate = (TextView)findViewById(R.id.YearMonth);

        //System.out.println("-------------------------------------------------------------------Main current[0]="+current[0]+" [1]="+current[1]);
        // System.out.println("------------------------------------------------------------------호출됨?------------------");
        mContext = this;
        ViewPager2 vpPager = findViewById(R.id.vpPager);
        FragmentStateAdapter adapter = new PagerAdapter(this);
        vpPager.setAdapter(adapter);
        nowDate.setText(current[0] + "년 " + current[1] + "월");
        System.out.println("-------------------------------------------------------------------Main current[0]="+current[0]+" [1]="+current[1]);

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
        TextView nowdate = (TextView) findViewById(R.id.YearMonth);
        nowdate.setText(current[0] + "년 " + current[1] + "월");
    }


    @Override
    public void mainGetDisplay(int w, int h) {
        System.out.println("-------------------------------------------------------------------w="+w+" h="+h);

        gridviewWidth = w;
        gridviewHeight = h;
    }

    //FirstFragment.getYearMonth();

}