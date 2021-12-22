package com.example.mym.session;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mym.R;
import com.example.mym.server.model.user.User;
import com.example.mym.session.friends_tab.FriendsFragment;
import com.example.mym.session.home_tab.HomeFragment;
import com.example.mym.session.profile_tab.ProfileFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.Home, R.string.Friends,R.string.Profile};
    private final Context mContext;
    public static User user;
    public SectionsPagerAdapter(Context context, FragmentManager fm, User user) {
        super(fm);
        mContext = context;
        SectionsPagerAdapter.user = user;
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    @Override
    public Fragment getItem(int position ) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                return new HomeFragment(user);
            case 1:
                return new FriendsFragment(user);
            default:
                return new ProfileFragment(user);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}