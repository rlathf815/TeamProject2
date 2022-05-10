package com.example.teamproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class weekPagerAdapter extends FragmentStateAdapter {
    //private static int NUM_ITEMS=3;
    private int START_POS =10;

    MonthCalc mva = new MonthCalc();
    public weekPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public long getItemId(int position)
    {

        return 0;
    }

    // 각 페이지를 나타내는 프래그먼트 반환

    //ArrayList<Fragment> pages = new ArrayList<Fragment>();
    @Override
    public Fragment createFragment(int position)
    {
        //long itemID = getItemId(position);
        //WeekFragment fg = new WeekFragment();
        WeekFragment fg = WeekFragment.newInstance(2022, 5);

        return fg;
    }

    @Override
    public int getItemCount() {
        return 20;
    }


}