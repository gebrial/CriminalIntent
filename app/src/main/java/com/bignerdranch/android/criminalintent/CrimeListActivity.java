package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by sanjaya on 08/08/15.
 */
public class CrimeListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}