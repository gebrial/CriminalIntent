package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.Date;

/**
 * Created by sanjaya on 10/08/15.
 */
public class DatePickerActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context, Date date){
        Intent intent = new Intent(context, DatePickerActivity.class);
        intent.putExtra(DatePickerFragment.EXTRA_DATE, date);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return DatePickerFragment.newInstance(null);
    }
}
