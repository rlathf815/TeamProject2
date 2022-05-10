package com.example.teamproject2;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


public class WeekFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeekFragment() {
        // Required empty public constructor
    }

    int[] info = new int[4];
    MonthCalc mva = new MonthCalc();
    public static int[] current = new int[3];
    static MyGridViewAdapter adapter;
    GridView gridView;

    // TODO: Rename and change types and number of parameters
    public static WeekFragment newInstance(String param1, String param2) {
        WeekFragment fragment = new WeekFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        int bd = (int) getArguments().getLong("yearMonth");
        System.out.println("-------------------------------------------------------------------bundle 전달받은 값은"+bd);
        current[0] = bd/10000;
        current[1] = (bd%10000)/100;
        current[2] = (bd%10000)%100;

        if (getActivity() instanceof contentFragment.fragInterface) {
            ((contentFragment.fragInterface) getActivity()).getYearMonth(current[0], current[1], current[2]);
            System.out.println("-------------------------------------------------------------------yearmonthInterface 성공여부" + (getActivity() instanceof contentFragment.fragInterface));
        }

        info = mva.calcInfo(current);
        ArrayList<item> data = new ArrayList<item>();
        for (int i = 0; i < info[0] - 1; i++) {
            data.add(new item(" ", "", "", "", current[1]));
        }
        for (int i = 0; i < info[1]; i++) {
            data.add(new item("" + (i + 1), "", "", "", current[1]));
        }
        for (int i = 0; i < (43 - (info[0] + info[1])); i++) {
            data.add(new item(" ", "", "", "", current[1]));
        }

        adapter = new MyGridViewAdapter(getActivity(), R.layout.item_layout, data);

        gridView.setAdapter(adapter);
        // 리스트뷰 항목이 선택되었을 때, 항목 클릭 이벤트 처리
        ArrayList<View> selected = new ArrayList<View>();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Activity activity = getActivity();

                // 선택된 항목 위치 (position)을 이 프래그먼트와 연결된 MainActivity로 전달
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
        return inflater.inflate(R.layout.fragment_week, container, false);
    }
    public interface fragInterface {
        public void getYearMonth(int year, int month, int day);
        public void mainGetDisplay(int w, int h);
    }
}