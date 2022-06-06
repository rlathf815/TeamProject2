package com.example.teamproject2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

    public class ScheduleActivity extends AppCompatActivity{


        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_schedule);
            NumberPicker SThourpicker, STminpicker, FINhourpicker, FINminpicker;
            SThourpicker = findViewById(R.id.hourPicker);
            STminpicker = findViewById(R.id.minPicker);
            FINhourpicker = findViewById(R.id.FINhourPicker);
            FINminpicker = findViewById(R.id.FINminPicker);

            SThourpicker.setMinValue(0);
            SThourpicker.setMaxValue(23);
            SThourpicker.setValue(8);
            STminpicker.setMinValue(0);
            STminpicker.setMaxValue(59);
            FINhourpicker.setMinValue(0);
            FINhourpicker.setMaxValue(23);
            FINhourpicker.setValue(9);
            FINminpicker.setMinValue(0);
            FINminpicker.setMaxValue(59);

            Intent intent = getIntent();
            String year = intent.getStringExtra("year");
            String month = intent.getStringExtra("month");
            String day = intent.getStringExtra("day");
            String time = intent.getStringExtra("time");

            EditText title = (EditText)findViewById(R.id.title);
            if(time == null)
                title.setText(year+"년 "+month+"월 "+day+"일");
            else
                title.setText(year+"년 "+month+"월 "+day+"일 "+time+"시");

        }
    }
