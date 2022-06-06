package com.example.teamproject2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WeekActivity extends AppCompatActivity implements WeekFragment.wfragInterface{
    public static int[] current = new int[3];
    public static Context mContext;
    private ViewPager2 weekPager;
    private FloatingActionButton fab;
    public boolean clicked = false;
    public String[] selectedDateTime = new String[4];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        mContext = this;
        weekPager = findViewById(R.id.weekPager);
        FragmentStateAdapter adapter = new weekPagerAdapter(this);
        weekPager.setAdapter(adapter);
        weekPager.setCurrentItem(10,false);
        fab = findViewById(R.id.Weekfab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "일정추가",Toast.LENGTH_SHORT).show();
                if(clicked==true)
                {
                    Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                    //EditText title = findViewById(R.id.title);
                    intent.putExtra("year",selectedDateTime[0]);
                    intent.putExtra("month",selectedDateTime[1]);
                    intent.putExtra("day",selectedDateTime[2]);
                    intent.putExtra("time",selectedDateTime[3]);

                    startActivity(intent);
                }
                else
                    Toast.makeText(WeekActivity.this, "날짜를 선택해주세요",Toast.LENGTH_SHORT).show();

            }
        });
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
        Fragment wf = fragmentManager.findFragmentById(R.id.wf);
        Fragment fg = fragmentManager.findFragmentById(R.id.fg);
        switch (item.getItemId()) {
            case R.id.action_monthactivity:
                startActivity(new Intent(this,MainActivity.class));
                return true;
            case R.id.action_weekactivity:
                startActivity(new Intent(this,WeekActivity.class));
                if (wf != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.wf, WeekFragment.newInstance(current[0], current[1],current[2])).commit();
                    ab.setTitle(current[0] + "년 " + current[1] + "월");
                    break;
                }
                else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fg, WeekFragment.newInstance(current[0], current[1],current[2])).commit();
                    ab.setTitle(current[0] + "년 " + current[1] + "월");
                    break;
                }
        }
        return super.onOptionsItemSelected(item);
    }
    public void onPosSelected(int pos) {
        System.out.println("-------------------------------------------------------------------weekActivity onposselected-position"+pos);
        Toast.makeText(WeekActivity.this, "pos="+String.valueOf(pos),Toast.LENGTH_SHORT).show();
        clicked = true;
        MonthCalc mva = new MonthCalc();
        int weekday = mva.calcWeekDay(current)-1;
        int startWeek = current[2] - weekday;
        selectedDateTime[0]= String.valueOf(current[0]);
        selectedDateTime[1]= String.valueOf(current[1]);
        selectedDateTime[3]= String.valueOf(pos/7);
        switch (pos%7)
        {
            case 0:
                selectedDateTime[2]=String.valueOf(startWeek);
                break;
            case 1:
                selectedDateTime[2]=String.valueOf(startWeek+1);
                break;
            case 2:
                selectedDateTime[2]=String.valueOf(startWeek+2);
                break;
            case 3:
                selectedDateTime[2]=String.valueOf(startWeek+3);
                break;
            case 4:
                selectedDateTime[2]=String.valueOf(startWeek+4);
                break;
            case 5:
                selectedDateTime[2]=String.valueOf(startWeek+5);
                break;
            case 6:
                selectedDateTime[2]=String.valueOf(startWeek+6);
                break;
        }



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
