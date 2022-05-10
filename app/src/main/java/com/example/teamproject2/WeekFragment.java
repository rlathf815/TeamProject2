package com.example.teamproject2;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class WeekFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    private int mParam3;

    static listAdapter Ladapter;
    public WeekFragment() {
        // Required empty public constructor
    }

    int[] info = new int[4];
    MonthCalc mva = new MonthCalc();
    public static int[] current = new int[3];
    static weekGridviewAdapter adapter;
    GridView gridView;

    // TODO: Rename and change types and number of parameters
    public static WeekFragment newInstance(int param1, int param2, int param3) {
        WeekFragment fragment = new WeekFragment();
        Bundle args = new Bundle();
        args.putInt("Year", param1);
        args.putInt("Month", param2);
        args.putInt("Day", param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getInt("Year");
            mParam2 = getArguments().getInt("Month");
            mParam3 = getArguments().getInt("Day");

        }
        current[0]= mParam1;
        current[1]= mParam2;
        current[2]=mParam3;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (getArguments() != null) {

            mParam1 = getArguments().getInt("Year");
            mParam2 = getArguments().getInt("Month");
            mParam3 = getArguments().getInt("Day");

            System.out.println("-------------------------------------------------------------------year"+mParam1);
            current[0]= mParam1;
            current[1]= mParam2;
            current[2]= mParam3;

        }

        View rootView = inflater.inflate(R.layout.fragment_week, container, false);
        ArrayList<timeline> time = new ArrayList<timeline>();
        //time.add(new timeline(""));
        for(int i=0;i<=24;i+=2) time.add(new timeline(i));
        Ladapter=new listAdapter(getActivity().getApplicationContext(),R.layout.timeline,time);
        ListView list = (ListView) rootView.findViewById(R.id.listview);
        list.setAdapter(Ladapter);
        //list.bringToFront();
        setHasOptionsMenu(true);
        int bd = (int) getArguments().getLong("yearMonth");

        System.out.println("-------------------------------------------------------------------bundle 전달받은 값은"+bd);
        if(bd!=0) {
            current[0] = bd / 10000;
            current[1] = (bd % 10000) / 100;
            current[2] = (bd % 10000) % 100;
        }

        if (getActivity() instanceof WeekFragment.wfragInterface) {
            ((WeekFragment.wfragInterface) getActivity()).getYearMonth(current[0], current[1], current[2]);
            System.out.println("-------------------------------------------------------------------yearmonthInterface 성공여부" + (getActivity() instanceof contentFragment.fragInterface));
            ((WeekFragment.wfragInterface) getActivity()).setAppbar(current[0], current[1]);
        }


        info = mva.calcInfo(current);
        int weekday = mva.calcWeekDay(current)-1;
        int startWeek = current[2] - weekday;
        System.out.println("-------------------------------------------------------------------요일"+weekday);

        System.out.println("-------------------------------------------------------------------날짜"+current[2]);
        TextView tv1 = rootView.findViewById(R.id.day1);
        TextView tv2 = rootView.findViewById(R.id.day2);
        TextView tv3 = rootView.findViewById(R.id.day3);
        TextView tv4 = rootView.findViewById(R.id.day4);
        TextView tv5 = rootView.findViewById(R.id.day5);
        TextView tv6 = rootView.findViewById(R.id.day6);
        TextView tv7 = rootView.findViewById(R.id.day7);
        tv1.setText(String.valueOf(startWeek));
        tv2.setText(String.valueOf(startWeek+1));
        tv3.setText(String.valueOf(startWeek+2));
        tv4.setText(String.valueOf(startWeek+3));
        tv5.setText(String.valueOf(startWeek+4));
        tv6.setText(String.valueOf(startWeek+5));
        tv7.setText(String.valueOf(startWeek+6));
        LinearLayout lo = rootView.findViewById(R.id.weekdaysLayout);
        int w = lo.getWidth()/7;
        tv1.setWidth(w);
        tv2.setWidth(w);
        tv3.setWidth(w);
        tv4.setWidth(w);
        tv5.setWidth(w);
        tv6.setWidth(w);
        tv7.setWidth(w);


        ArrayList<weekItem> data = new ArrayList<weekItem>();
        for (int i = 0; i<84; i++) {
            data.add(new weekItem("",i));
        }
        adapter = new weekGridviewAdapter(getActivity(), R.layout.schedule_layout, data);
        gridView = rootView.findViewById(R.id.weekGridview);
        gridView.setAdapter(adapter);
        // 리스트뷰 항목이 선택되었을 때, 항목 클릭 이벤트 처리
        ArrayList<View> selected = new ArrayList<View>();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Activity activity = getActivity();
                if (activity instanceof WeekActivity) {
                    System.out.println("-------------------------------------------------------------------weekFrag cicked pos"+position);
                    ((WeekActivity) activity).onPosSelected(position);
                }
                if(!selected.isEmpty()) {
                    selected.get(0).setBackgroundResource(R.drawable.border);
                    selected.clear();
                }
                view.setBackgroundResource(R.drawable.clicked);
                selected.add(view);
                TextView tv1 = activity.findViewById(R.id.day1);
                TextView tv2 = activity.findViewById(R.id.day2);
                TextView tv3 = activity.findViewById(R.id.day3);
                TextView tv4 = activity.findViewById(R.id.day4);
                TextView tv5 = activity.findViewById(R.id.day5);
                TextView tv6 = activity.findViewById(R.id.day6);
                TextView tv7 = activity.findViewById(R.id.day7);

                switch (position%7) {
                    case 0:
                        tv1.setBackgroundColor(Color.parseColor("#8B9C84"));
                        tv2.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv3.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv4.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv5.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv6.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv7.setBackgroundColor(Color.parseColor("#CADFC1"));
                        break;
                    case 1:
                        tv1.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv2.setBackgroundColor(Color.parseColor("#8B9C84"));
                        tv3.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv4.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv5.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv6.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv7.setBackgroundColor(Color.parseColor("#CADFC1"));                        break;
                    case 2:
                        tv1.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv2.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv3.setBackgroundColor(Color.parseColor("#8B9C84"));
                        tv4.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv5.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv6.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv7.setBackgroundColor(Color.parseColor("#CADFC1"));
                        break;
                    case 3:
                        tv1.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv2.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv3.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv4.setBackgroundColor(Color.parseColor("#8B9C84"));
                        tv5.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv6.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv7.setBackgroundColor(Color.parseColor("#CADFC1"));                        break;
                    case 4:
                        tv1.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv2.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv3.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv4.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv5.setBackgroundColor(Color.parseColor("#8B9C84"));
                        tv6.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv7.setBackgroundColor(Color.parseColor("#CADFC1"));                        break;
                    case 5:
                        tv1.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv2.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv3.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv4.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv5.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv6.setBackgroundColor(Color.parseColor("#8B9C84"));
                        tv7.setBackgroundColor(Color.parseColor("#CADFC1"));                        break;
                    case 6:
                        tv1.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv2.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv3.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv4.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv5.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv6.setBackgroundColor(Color.parseColor("#CADFC1"));
                        tv7.setBackgroundColor(Color.parseColor("#8B9C84"));
                        break;



                }
            }
        });
        return rootView;
    }

    public interface wfragInterface {
        public void getYearMonth(int year, int month, int day);
        public void mainGetDisplay(int w, int h);
        public void setAppbar(int year, int month);

    }
}