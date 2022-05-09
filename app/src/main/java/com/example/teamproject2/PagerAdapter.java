package com.example.teamproject2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=3;
    MonthCalc mva = new MonthCalc();

    public PagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    // 각 페이지를 나타내는 프래그먼트 반환
    @Override

    public Fragment createFragment(int position)
    {
        switch(position){
            case 0:
                contentFragment first = new contentFragment();
                contentFragment.current = mva.calcCal();
                System.out.println("------------------------------------------------pager current[1] ="+contentFragment.current[1]);
                return first;
            case 1:
                contentFragment.current[1]--;
                contentFragment prev = new contentFragment();
                return prev;
            case 2:
                contentFragment next = new contentFragment();
                return next;
            default:
                return null;

        }
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}
