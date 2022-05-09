package com.example.teamproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=3;
    private int POS;
    MonthCalc mva = new MonthCalc();

    public PagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public long getItemId(int position) {
        int[] current = mva.calcCal();

        return (current[0]*10000+current[1]*100+current[2]);
    }

    // 각 페이지를 나타내는 프래그먼트 반환
    @Override

    public Fragment createFragment(int position)
    {
        long current = getItemId(position);
        contentFragment fg = new contentFragment();
        Bundle args = new Bundle();
        args.putLong("yearMonth",current);
        fg.setArguments(args);
        System.out.println("-------------------------------------------------------------------bundle 전달 전 current값: "+current);
        return fg;
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}
