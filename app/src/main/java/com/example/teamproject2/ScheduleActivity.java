package com.example.teamproject2;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements OnMapReadyCallback {

        private GoogleMap mMap;
        private Geocoder geocoder;
        private Button searchBtn, saveBtn, cancelBtn, delBtn;
        private EditText searchTxt, title, memo;
        private DBHelper mDBHelper;
        public String StartTime, FinTime, date;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_schedule);

            title = (EditText)findViewById(R.id.title);
            searchTxt = (EditText) findViewById(R.id.searchTxt);
            memo = (EditText) findViewById(R.id.memo);
            searchBtn = (Button)findViewById(R.id.searchBtn);
            saveBtn = (Button)findViewById(R.id.saveBtn);
            cancelBtn = (Button)findViewById(R.id.cancelBtn);
            delBtn = (Button)findViewById(R.id.delBtn);

            mDBHelper = new DBHelper(this);

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

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
            date = year+month+day;
            if(time == null)
                title.setText(year+"년 "+month+"월 "+day+"일");
            else
                title.setText(year+"년 "+month+"월 "+day+"일 "+time+"시");

            saveBtn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    int SThour = SThourpicker.getValue();
                    int STmin = STminpicker.getValue();
                    int FINhour = FINhourpicker.getValue();
                    int FINmin = FINminpicker.getValue();
                    StartTime = SThour+"시 "+STmin+"분";
                    FinTime = FINhour+"시 "+FINmin+"분";

                    insertRecord();

                }
            });
            delBtn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view)
                {

                }
            });

        }
    private void insertRecord() {

        mDBHelper.insertSchBySQL(date, title.getText().toString(), StartTime, FinTime, searchTxt.getText().toString(), memo.getText().toString());
        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm inserted?"+StartTime);
    }
    private void deleteRecord()
    {

    }
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this);


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions mOptions = new MarkerOptions();

                mOptions.title("마커 좌표");
                Double latitude = point.latitude; // 위도
                Double longitude = point.longitude; // 경도
                mOptions.snippet(latitude.toString() + ", " + longitude.toString());
                mOptions.position(new LatLng(latitude, longitude));
                googleMap.addMarker(mOptions);
            }
        });

        searchBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String setText = searchTxt.getText().toString();
                List<Address> state = null;
                try {
                    state = geocoder.getFromLocationName(setText, 10);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                if (state.size() != 0) {
                    System.out.println(state.get(0).toString());
                    String[] splitStr = state.get(0).toString().split(",");
                    String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); // 주소
                    System.out.println(address);

                    String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                    String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                    System.out.println(latitude);
                    System.out.println(longitude);

                    LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                    MarkerOptions mOptions2 = new MarkerOptions();
                    mOptions2.title("search result");
                    mOptions2.snippet(address);
                    mOptions2.position(point);
                    mMap.addMarker(mOptions2);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
                }
                else {
                    Toast.makeText(getApplicationContext(), "다시 입력", Toast.LENGTH_LONG).show();
                }
            }
        });

        LatLng seoul = new LatLng(37.5666, 126.9784);
        mMap.addMarker(new MarkerOptions().position(seoul).title("Seoul"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));

    }
}
