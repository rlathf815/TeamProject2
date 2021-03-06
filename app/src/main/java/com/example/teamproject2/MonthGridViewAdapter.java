package com.example.teamproject2;

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

public class MonthGridViewAdapter extends BaseAdapter{
    private ArrayList<item> mItems = new ArrayList<item>();
    private Context mContext;
    private int mResource;
    public static int gridviewWidth, gridviewHeight;
    MonthActivity act = new MonthActivity();
    private DBHelper mDBHelper;

    public MonthGridViewAdapter(Context context, int resource, ArrayList<item> items)
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
        TextView tv_day = convertView.findViewById(R.id.textView_day);
        tv_day.setText(mItems.get(i).day);

        TextView tv_schedule1 = convertView.findViewById(R.id.textView_schedule1);
        tv_schedule1.setText(mItems.get(i).schedule1);
        //System.out.println("+++++++++++++++++++++++ is schedule1 valid? "+ mItems.get(i).schedule1!=" ");
        TextView tv_schedule2 = convertView.findViewById(R.id.textView_schedule2);
        tv_schedule2.setText(mItems.get(i).schedule2);

        Calendar mCal = Calendar.getInstance();

        int td = mCal.get(Calendar.DAY_OF_WEEK);
        int today = mCal.get(Calendar.DAY_OF_MONTH);
        int curMonth = mCal.get(Calendar.MONTH)+1;
        int year = mCal.get(Calendar.YEAR);
        //blank ????????? 8-blank?????? ?????? ????????? ????????? ?????? ??????
        //String sMonth = String.valueOf(curMonth);
        String sToday = String.valueOf(today);

        if (sToday.equals(mItems.get(i).day) && curMonth==mItems.get(i).month) { //?????? day ????????? ?????? ??????

            tv_day.setBackgroundColor(Color.rgb(222,182,174));
            tv_day.setTextColor(Color.WHITE);
        }


        int j=0;

        while(true)
        {
            if(mItems.get(j).day!=" ")
                break;
            j++;
        }
        if(mItems.get(i).schedule1 != null)
            tv_schedule1.setBackgroundColor(Color.parseColor("#C2AEAE"));
        if(mItems.get(i).schedule2 != null)
            tv_schedule2.setBackgroundColor(Color.parseColor("#616161"));
        for(int k=8-j;k<= mItems.size();k+=7)
        {
            if(String.valueOf(k).equals(mItems.get(i).day))
                tv_day.setTextColor(Color.rgb(198,77,107));
        }
        for(int k=7-j;k<= mItems.size();k+=7)
        {
            if(String.valueOf(k).equals(mItems.get(i).day))
                tv_day.setTextColor(Color.rgb(32,109,171));
        }

        gridviewWidth = ((MonthActivity) MonthActivity.mContext).gridviewWidth;
        gridviewHeight = ((MonthActivity) MonthActivity.mContext).gridviewHeight;
        int width = gridviewWidth/7;
        int height= (gridviewHeight-68)/6;

        LinearLayout layout = convertView.findViewById(R.id.eachItem);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height-2);

        layout.setLayoutParams(params);




        return convertView;
    }




}
