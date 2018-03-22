package com.decisionmind.pedro.hellomobil.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.decisionmind.pedro.hellomobil.fragment.ChatFragment;
import com.decisionmind.pedro.hellomobil.fragment.ContactsFragment;

/**
 * Created by pedro on 22/03/18.
 */

public class TabAdapter extends FragmentStatePagerAdapter{

    private String[] titleTab = {"CONVERSAS", "CONTATOS"};


    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new ChatFragment();
                break;
            case 1:
                fragment = new ContactsFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return titleTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleTab[position];
    }
}
