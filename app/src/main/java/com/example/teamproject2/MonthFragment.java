package com.example.teamproject2;

import android.app.Activity;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MonthFragment extends Fragment {

    private int mParam1;
    private int mParam2;
    int[] info = new int[4];
    MonthCalc mva = new MonthCalc();
    public static int[] current = new int[3];
    static MonthGridViewAdapter adapter;
    GridView gv;
    private DBHelper mDBHelper;
    public MonthFragment() {
        // Required empty public constructor
    }

    public static MonthFragment newInstance(int param1, int param2) {
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
        if (getArguments() != null) {

            mParam1 = getArguments().getInt("Year");
            mParam2 = getArguments().getInt("Month");
            System.out.println("-------------------------------------------------------------------year"+mParam1);
            current[0]= mParam1;
            current[1]= mParam2;
        }
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        setHasOptionsMenu(true);
        int bd = (int) getArguments().getLong("yearMonth");
        System.out.println("-------------------------------------------------------------------bundle 전달받은 값은"+bd);
        if(bd!=0) {
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
        System.out.println("-------------------------------------------------------------------Frag gridviewidth="+displayWidth+" height="+displayHeight);
        System.out.println("------------------------------------------------------------------adapterInterface 성공여부" + (getActivity() instanceof fragInterface));
        System.out.println("-------------------------------------------------------------------current[0]="+current[0]+" [1]="+current[1]+" [2]="+current[2]);
        if (getActivity() instanceof fragInterface) {
            ((fragInterface) getActivity()).mainGetDisplay(displayWidth, displayHeight);
            System.out.println("------------------------------------------------------------------호출됨?------------------");
        }
        //(setYearMonth(current[0],current[1]);
        ArrayList<item> data = new ArrayList<item>();
        for (int i = 0; i < info[0] - 1; i++) {
            data.add(new item(" ", "", "", current[1]));
        }
        for (int i = 0; i < info[1]; i++) {
            data.add(new item("" + (i + 1), "", "", current[1]));
        }
        for (int i = 0; i < (43 - (info[0] + info[1])); i++) {
            data.add(new item(" ", "", "", current[1]));
        }

        GridView gridView = rootView.findViewById(R.id.gridview);
        gv = gridView;
        // Activity activity = getActivity();
        // MainActivity.setYearMonth(current[0],current[1]);

        adapter = new MonthGridViewAdapter(getActivity(), R.layout.item_layout, data);

        gridView.setAdapter(adapter);
        // 그리드뷰 항목이 선택되었을 때, 항목 클릭 이벤트 처리
        ArrayList<View> selected = new ArrayList<View>();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Activity activity = getActivity();

                if (activity instanceof MainActivity) {
                    String day = ((item) adapter.getItem(position)).day;
                    String year = String.valueOf(current[0]);
                    String month = String.valueOf(current[1]);
                    ((MainActivity) activity).onDateSelected(year, month, day);
                }
                if(!selected.isEmpty()) {
                    selected.get(0).setBackgroundResource(R.drawable.border);
                    selected.clear();
                }
                view.setBackgroundResource(R.drawable.clicked);
                selected.add(view);
            }
        });
        return rootView;
    }
    public void showSchedule(){
        Cursor cursor = mDBHelper.getAllSchBySQL();
        while(cursor.moveToNext()){
            String date = cursor.getString(1);

        }

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