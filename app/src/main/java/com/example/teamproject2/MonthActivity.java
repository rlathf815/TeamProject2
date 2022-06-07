package com.example.teamproject2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MonthActivity extends AppCompatActivity implements MonthFragment.fragInterface {
    public static int[] current = new int[3];
    public static int gridviewWidth, gridviewHeight;
    public static Context mContext;
    private ViewPager2 vpPager;
    private FloatingActionButton fab;
    public boolean clicked = false;
    public String[] selectedDate = new String[3];
    private DBHelper mDBHelper;
    public View pressedView;
    public String title;
    Dialog dialog2;
    //PagerAdapter fragmentViewPagerAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog2  = new Dialog(this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.delete_dialog);
        mDBHelper = new DBHelper(this);
        mContext = this;
        vpPager = findViewById(R.id.vpPager);
        FragmentStateAdapter adapter = new MonthPagerAdapter(this);
        vpPager.setAdapter(adapter);
        vpPager.setCurrentItem(10,false);
        fab = findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "일정추가",Toast.LENGTH_SHORT).show();
                if(clicked==true)
                {
                    Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                    //EditText title = findViewById(R.id.title);
                    intent.putExtra("year",selectedDate[0]);
                    intent.putExtra("month",selectedDate[1]);
                    intent.putExtra("day",selectedDate[2]);
                    //intent.putExtra("view", (Parcelable) pressedView);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MonthActivity.this, "날짜를 선택해주세요",Toast.LENGTH_SHORT).show();

            }
        });
        //Intent getIntent = getIntent();
       //title = getIntent.getStringExtra("title");
        //System.out.println("000000000000000000000000000000  받은title값 "+title);
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //MonthFragment frag = MonthFragment.newInstance(current[0],current[1],title);
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragpos, frag).commit();
    }
    public void showDialog_all() {
        dialog2.show();
        mDBHelper = new DBHelper(this);

        Button yesBtn = dialog2.findViewById(R.id.delete_yes);
        Button noBtn = dialog2.findViewById(R.id.delete_no);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBHelper.deleteAllBySQL();
                startActivity(new Intent(getApplicationContext(), MonthActivity.class));
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MonthActivity.class));

            }
        });
    }

    public void onDateSelected(String year, String month, String date) {
        Toast.makeText(MonthActivity.this, year + "." + month + "." + date,
                Toast.LENGTH_SHORT).show();
        clicked = true;
        selectedDate[0] = year;
        selectedDate[1] = month;
        selectedDate[2] = date;
    }
    public void getYearMonth(int year, int month, int day)
    {
        //int[] date = new int[3];
        System.out.println("-------------------------------------------------------------------getYearmonth호출됨-----------------------");
        current[0] = year;
        current[1] = month;
        current[2] = day;
        ActionBar ab = getSupportActionBar();
        ab.setTitle(current[0] + "년 " + current[1] + "월");
        // TextView nowDate = (TextView) findViewById(R.id.YearMonth);
        //nowDate.setText(current[0] + "년 " + current[1] + "월");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ActionBar ab = getSupportActionBar();
        Fragment wf = fragmentManager.findFragmentById(R.id.wf);
        Fragment fg = fragmentManager.findFragmentById(R.id.fg);
        switch (item.getItemId()) {
            case R.id.action_monthactivity:
                startActivity(new Intent(this, MonthActivity.class));
                if (wf != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.wf, MonthFragment.newInstance(current[0], current[1])).commit();
                    ab.setTitle(current[0] + "년 " + current[1] + "월");
                    break;
                }
                else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fg, MonthFragment.newInstance(current[0], current[1])).commit();
                    ab.setTitle(current[0] + "년 " + current[1] + "월");
                    break;
                }
            case R.id.action_weekactivity:
                //fragmentManager.removeOnBackStackChangedListener();
                startActivity(new Intent(this,WeekActivity.class));
                return true;
                /*if (wf != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.wf, WeekFragment.newInstance(current[0], current[1],current[2])).commit();
                    ab.setTitle(current[0] + "년 " + current[1] + "월");
                    break;
                }
                else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fg, WeekFragment.newInstance(current[0], current[1],current[2])).commit();
                    ab.setTitle(current[0] + "년 " + current[1] + "월");
                    break;
                }*/
            case R.id.action_deleteAll:
                showDialog_all();
                //startActivity(new Intent(this, MonthActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void mainGetDisplay(int w, int h) {

        gridviewWidth = w;
        gridviewHeight = h;
    }
    public void setAppbar(int year, int month)
    {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(year + "년 " + month + "월");
    }


}