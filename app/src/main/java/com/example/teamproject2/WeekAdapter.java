package com.example.teamproject2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class WeekAdapter extends FragmentStateAdapter {
    private int START_POS =10;

    MonthCalc mva = new MonthCalc();

    public WeekAdapter(FragmentActivity fb) {
        super(fb);
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
        System.out.println("---------------------------------------------------------------move="+move);
        //if(move==0) return (current[0]*10000+current[1]*100+current[2]);
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

    public Fragment createFragment(int position)
    {
        long itemID = getItemId(position);
        WeekFragment fg = new WeekFragment();
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
