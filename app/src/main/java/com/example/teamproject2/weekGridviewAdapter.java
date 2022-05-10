package com.example.teamproject2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class weekGridviewAdapter extends BaseAdapter{
    private ArrayList<weekItem> mItems = new ArrayList<weekItem>();
    private Context mContext;
    private int mResource;
    public static int gridviewWidth, gridviewHeight;
    WeekActivity act = new WeekActivity();


    public weekGridviewAdapter(Context context, int resource, ArrayList<weekItem> items)
    {
        mContext = context;
        mItems = items;
        mResource = resource;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }

        convertView.setBackgroundResource(R.drawable.border);
        TextView tv_schedule1 = convertView.findViewById(R.id.week1);
        tv_schedule1.setText(mItems.get(i).schedule1);

        TextView tv_schedule2 = convertView.findViewById(R.id.week2);
        tv_schedule2.setText(mItems.get(i).schedule2);

        TextView tv_schedule3 = convertView.findViewById(R.id.week3);
        tv_schedule3.setText(mItems.get(i).schedule3);

        return convertView;
    }

}
