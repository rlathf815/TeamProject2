package com.example.teamproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FirstFragment.fragInterface {
    public static int[] current = new int[3];
    public static int gridviewWidth, gridviewHeight;
    public static Context mContext;
    public String a;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nowDate;
        nowDate = (TextView)findViewById(R.id.YearMonth);
        nowDate.setText(current[0] + "년 " + current[1] + "월");

        System.out.println("------------------------------------------------------------------호출됨?------------------");
        mContext = this;
    }
    public void onDateSelected(String year, String month, String date) {
        Toast.makeText(MainActivity.this, year + "." + month + "." + date,
                Toast.LENGTH_SHORT).show();
    }
    public void getYearMonth(int year, int month, int day)
    {
        //int[] date = new int[3];
        current[0] = year;
        current[1] = month;
        current[2] = day;

    }

    @Override
    public void mainGetDisplay(int w, int h) {
        System.out.println("-------------------------------------------------------------------main에서호출됨-----------------------");
        System.out.println("-------------------------------------------------------------------w="+w+" h="+h);

        gridviewWidth = w;
        gridviewHeight = h;
    }

    //FirstFragment.getYearMonth();

}