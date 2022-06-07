package com.example.teamproject2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;

public class MonthFragment extends Fragment {

    private int mParam1;
    private int mParam2;


    int[] info = new int[4];
    MonthCalc mva = new MonthCalc();
    public static int[] current = new int[3];
    static MonthGridViewAdapter adapter;
    GridView gv;
    private DBHelper mDBHelper;
    ArrayList<View> selected = new ArrayList<View>();
    Cursor cursor;
    Dialog dialog1;
    public MonthFragment() {
        // Required empty public constructor
    }

    public static MonthFragment newInstance(int param1,int param2)
    {
        MonthFragment fragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putInt("Year", param1);
        args.putInt("Month", param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt("Year");
            mParam2 = getArguments().getInt("Month");

        }
        current[0]= mParam1;
        current[1]= mParam2;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDBHelper = new DBHelper(this.getContext());
        cursor = mDBHelper.getAllSchBySQL();
        dialog1  = new Dialog(this.getActivity());
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.select_dialog);

        //mDBHelper.deleteAllBySQL();
        while (cursor.moveToNext())
        {
            System.out.println(cursor.getInt(0) +" "+ cursor.getString(1)+" "+ cursor.getString(2)+" "+ cursor.getString(3)
                    +" "+ cursor.getString(4)+" "+ cursor.getString(5)+" "+ cursor.getString(6));
        }
        if (getArguments() != null) {

            mParam1 = getArguments().getInt("Year");
            mParam2 = getArguments().getInt("Month");

            current[0] = mParam1;
            current[1] = mParam2;
        }
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        setHasOptionsMenu(true);
        int bd = (int) getArguments().getLong("yearMonth");
        System.out.println("-------------------------------------------------------------------bundle 전달받은 값은" + bd);
        if (bd != 0) {
            current[0] = bd / 10000;
            current[1] = (bd % 10000) / 100;
            current[2] = (bd % 10000) % 100;
        }
        if (getActivity() instanceof fragInterface) {
            ((fragInterface) getActivity()).getYearMonth(current[0], current[1], current[2]);
            ((fragInterface) getActivity()).setAppbar(current[0], current[1]);
        }
        // id가 listview인 리스트뷰 객체를 얻어옴

        //current = mva.calcCal();
        info = mva.calcInfo(current);
        int displayWidth = getGridviewSize().x;
        int displayHeight = getGridviewSize().y;
        if (getActivity() instanceof fragInterface) {
            ((fragInterface) getActivity()).mainGetDisplay(displayWidth, displayHeight);
        }
        //(setYearMonth(current[0],current[1]);
        ArrayList<item> data = new ArrayList<item>();
        for (int i = 0; i < info[0] - 1; i++) {
            data.add(new item(" ", null, null, current[1], current[0]));
        }
        for (int i = 0; i < info[1]; i++) {
            data.add(new item(String.valueOf(i+1), null, null, current[1], current[0]));
        }
        for (int i = 0; i < (43 - (info[0] + info[1])); i++) {
            data.add(new item(" ", null, null, current[1], current[0]));
        }
        if(mDBHelper!=null)
        {
            System.out.println("9999999999999999999999999 Searching DB ");
            cursor = mDBHelper.getAllSchBySQL();

            while(cursor.moveToNext()) {
                for(int i=0;i<data.size();i++) {
                    int  y= data.get(i).year ;
                    int m = data.get(i).month ;
                    int d ;
                    if(data.get(i).day!=" ")
                        d=Integer.valueOf(data.get(i).day);
                    else
                        d=0;
                    int date = (y*10000) +( m*100) + d ;
                    //System.out.println("********************** date = "+date);
                    //System.out.println("********************** DB date = "+cursor.getString(1));
                    if (String.valueOf(date).equals(cursor.getString(1))) {
                        if (data.get(i).schedule1 == null) {
                            data.set(i, new item(String.valueOf(d), cursor.getString(2), null, m, y));
                            System.out.println("000000000000000000000!! date =" + String.valueOf(date)+ " DBdate=  " + cursor.getString(1) + "equal? "+String.valueOf(date).equals(cursor.getString(1)));
                            System.out.println("**********************successfully saved in ArrayList");
                        }
                        else {
                            String schedule1 = data.get(i).schedule1;
                            data.set(i, new item(String.valueOf(d), schedule1, cursor.getString(2), m, y));
                        }
                    }
                }
            }
        }

        GridView gridView = rootView.findViewById(R.id.gridview);
        gv = gridView;
        // Activity activity = getActivity();
        // MainActivity.setYearMonth(current[0],current[1]);

        adapter = new MonthGridViewAdapter(getActivity(), R.layout.item_layout, data);

        gridView.setAdapter(adapter);
        // 그리드뷰 항목이 선택되었을 때, 항목 클릭 이벤트 처리

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Activity activity = getActivity();
                String sch1=null,sch2 = null,date=null;
                if (activity instanceof MonthActivity) {
                    String day = ((item) adapter.getItem(position)).day;
                    String year = String.valueOf(current[0]);
                    String month = String.valueOf(current[1]);
                    sch1 = ((item) adapter.getItem(position)).schedule1;
                    sch2 = ((item) adapter.getItem(position)).schedule2;
                    date = String.valueOf((current[0]*10000)+(current[1]*100)+current[2]);
                    ((MonthActivity) activity).onDateSelected(year, month, day);
                }
                if (!selected.isEmpty()) {
                    selected.get(0).setBackgroundResource(R.drawable.border);
                    selected.clear();
                }
                view.setBackgroundResource(R.drawable.clicked);

                selected.add(view);
                if(sch1 !=null && sch2 !=null)
                    showDialog1(date, sch1, sch2);
                else if(sch1!=null&&sch2==null)
                    showSchedule(sch1);
                else if(sch1==null&&sch2!=null)
                    showSchedule(sch2);


            }
        });

        return rootView;
    }
    public void showSchedule(String sch)
    {
        if(mDBHelper.findSchBySQL(sch)!=null) {
            cursor = mDBHelper.findSchBySQL(sch);
            System.out.println("-------------------------------------------keyword "+ sch);

            System.out.println("--------------------------------------------cursor found "+ cursor);
            if(cursor!=null) cursor.moveToFirst();
            Intent intent1 = new Intent(getActivity(), ScheduleActivity.class);
            intent1.putExtra("id", cursor.getInt(0));
            intent1.putExtra("date", cursor.getString(1));
            intent1.putExtra("title", sch);
            intent1.putExtra("STtime", cursor.getString(3));
            intent1.putExtra("FINtime", cursor.getString(4));
            intent1.putExtra("loc", cursor.getString(5));
            intent1.putExtra("memo", cursor.getString(6));
            startActivity(intent1);
        }
    }
    public void showDialog1(String date, String sch1, String sch2){
        dialog1.show();
        mDBHelper = new DBHelper(this.getContext());

        TextView tv = dialog1.findViewById(R.id.dialog_date);
        Button btn1 = dialog1.findViewById(R.id.dialog_btn1);
        Button btn2 = dialog1.findViewById(R.id.dialog_btn2);
        tv.setText(date);
        btn1.setText(sch1);
        btn2.setText(sch2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDBHelper.findSchBySQL(sch1)!=null) {
                    cursor = mDBHelper.findSchBySQL(sch1);
                    System.out.println("-------------------------------------------keyword "+ sch1);

                    System.out.println("--------------------------------------------cursor found "+ cursor);
                    if(cursor!=null) cursor.moveToFirst();
                    Intent intent1 = new Intent(getActivity(), ScheduleActivity.class);
                    intent1.putExtra("id", cursor.getInt(0));
                    intent1.putExtra("date", date);
                    intent1.putExtra("title", sch1);
                    intent1.putExtra("STtime", cursor.getString(3));
                    intent1.putExtra("FINtime", cursor.getString(4));
                    intent1.putExtra("loc", cursor.getString(5));
                    intent1.putExtra("memo", cursor.getString(6));
                    startActivity(intent1);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDBHelper.findSchBySQL(sch2)!=null) {
                    cursor = mDBHelper.findSchBySQL(sch2);
                    System.out.println("-------------------------------------------keyword "+ sch2);

                    System.out.println("--------------------------------------------cursor found "+ cursor);
                    if(cursor!=null) cursor.moveToFirst();
                    Intent intent1 = new Intent(getActivity(), ScheduleActivity.class);
                    intent1.putExtra("id", cursor.getInt(0));
                    intent1.putExtra("date", date);
                    intent1.putExtra("title", sch2);
                    intent1.putExtra("STtime", cursor.getString(3));
                    intent1.putExtra("FINtime", cursor.getString(4));
                    intent1.putExtra("loc", cursor.getString(5));
                    intent1.putExtra("memo", cursor.getString(6));
                    startActivity(intent1);
                }
            }
        });

    }

    public Point getGridviewSize() {
        //Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        size.x = displayMetrics.widthPixels;
        size.y = displayMetrics.heightPixels;
        System.out.println("------------------------------------------------------------------statbarH = "+getStatusBarHeight());
        size.y = size.y - getStatusBarHeight();

        return size;
    }
    private int getStatusBarHeight() {
        int statusHeight = 0;
        int screenSizeType = (getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK);
        if(screenSizeType != Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusHeight = getResources().getDimensionPixelSize(resourceId);
            }
        }

        return statusHeight;
    }


    public interface fragInterface {
        public void getYearMonth(int year, int month, int day);
        public void mainGetDisplay(int w, int h);
        public void setAppbar(int year, int month);
    }

}