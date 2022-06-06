package com.example.teamproject2;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        private Button button;
        private EditText Text;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_schedule);

            Text = (EditText) findViewById(R.id.searchTxt);
            button = (Button)findViewById(R.id.searchBtn);

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

            EditText title = (EditText)findViewById(R.id.title);
            if(time == null)
                title.setText(year+"년 "+month+"월 "+day+"일");
            else
                title.setText(year+"년 "+month+"월 "+day+"일 "+time+"시");

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

        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String setText = Text.getText().toString();
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocationName(setText, 10);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(addresses.get(0).toString());
                String []splitStr = addresses.get(0).toString().split(",");
                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2); // 주소
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
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));
            }
        });

        LatLng seoul = new LatLng(37.5666, 126.9784);
        mMap.addMarker(new MarkerOptions().position(seoul).title("Seoul"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));
    }
}
