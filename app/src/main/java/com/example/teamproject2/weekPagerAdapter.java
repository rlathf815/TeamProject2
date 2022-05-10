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
        int[] current = mva.calcCal();
        int[] info =mva.calcInfo(current);
        if(position ==START_POS)
            return (current[0]*10000+current[1]*100+current[2]);
        int move = position - START_POS;
        if(current[2]+move<1)
        {
            current[1]--;
            info = mva.calcInfo(current);
            current[2]=(current[2]+(move*7))+info[1];
        }
        else if(current[2]+move>info[1])
        {
            current[1]++;
            current[2]=current[2]+(move*7)-info[1];
        }
        else
        {
            current[2]+=move*7;
        }
        return (current[0]*10000+current[1]*100+current[2]);
    }

    // 각 페이지를 나타내는 프래그먼트 반환

    //ArrayList<Fragment> pages = new ArrayList<Fragment>();
    @Override
    public Fragment createFragment(int position)
    {
        long itemID = getItemId(position);
        int[] current = new int[3];
        current[0] = (int)itemID / 10000;
        current[1] = ((int)itemID % 10000) / 100;
        current[2] = ((int)itemID % 10000) % 100;
        System.out.println("---------------------------연 월 일"+itemID);
        //WeekFragment fg = new WeekFragment();
        WeekFragment fg = WeekFragment.newInstance(current[0], current[1],current[2]);

        return fg;
    }

    @Override
    public int getItemCount() {
        return 20;
    }


}