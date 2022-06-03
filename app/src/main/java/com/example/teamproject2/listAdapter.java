package com.example.teamproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listAdapter extends BaseAdapter {
    private Context mContext;
    private int mResource;
    private ArrayList<timeline> mItems = new ArrayList<timeline>();

    public listAdapter(Context context, int resource, ArrayList<timeline> items) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) { // 해당 항목 뷰가 이전에 생성된 적이 없는 경우
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent,false);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.time);


        tv.setText(mItems.get(position).time);
        tv.setHeight(152);
        return convertView;
    }
}
