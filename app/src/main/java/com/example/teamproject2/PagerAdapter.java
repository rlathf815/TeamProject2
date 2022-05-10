package com.example.teamproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStateAdapter {
    //private static int NUM_ITEMS=3;
    private int START_POS =10;

    MonthCalc mva = new MonthCalc();
    public PagerAdapter(FragmentActivity fa) {
        super(fa);
    }
    @Override
    public long getItemId(int position)
    {
        int[] current = mva.calcCal();
        if(position ==START_POS)
            return (current[0]*10000+current[1]*100+current[2]);
        System.out.println("---------------------------------------------------------------current[0]"+current[0]);
        System.out.println("---------------------------------------------------------------position"+position);

        int move = position - START_POS;
        if(current[1]+move<1)
        {
            current[0]--;
            current[1]=current[1]+move+12;
        }
        else if(current[1]+move>12)
        {
            current[0]++;
            current[1]=current[1]+move-12;
        }
        else
        {
            current[1]+=move;
        }

        return (current[0]*10000+current[1]*100+current[2]);
    }

    // 각 페이지를 나타내는 프래그먼트 반환

    //ArrayList<Fragment> pages = new ArrayList<Fragment>();
    @Override
    public Fragment createFragment(int position)
    {
        long itemID = getItemId(position);
        contentFragment fg = new contentFragment();
        Bundle args = new Bundle();
        args.putLong("yearMonth",itemID);
        fg.setArguments(args);
        return fg;
    }

    @Override
    public int getItemCount() {
        return 20;
    }


}