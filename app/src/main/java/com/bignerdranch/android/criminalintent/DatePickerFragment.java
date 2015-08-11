package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sanjaya on 09/08/15.
 */
public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";

    private static final String ARG_DATE = "date";

    private DatePicker mDatePicker;
    private Button mConfirmButton;
    private Date mDate;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        /*View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        mDate = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);

        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(calendar.get(Calendar.YEAR),
                         calendar.get(Calendar.MONTH),
                         calendar.get(Calendar.DAY_OF_MONTH),
                         null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.data_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Calendar calendar2 = Calendar.getInstance();
                        calendar2.setTime(mDate);
                        calendar2.set(Calendar.YEAR, mDatePicker.getYear());
                        calendar2.set(Calendar.MONTH, mDatePicker.getMonth());
                        calendar2.set(Calendar.DAY_OF_MONTH, mDatePicker.getDayOfMonth());

                        sendResult(Activity.RESULT_OK, calendar2.getTime());
                    }
                })
                .create();*/

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialog_date, container, false);

        mDate = (Date) getArguments().getSerializable(ARG_DATE);
        if(mDate == null){
            mDate = (Date) getActivity().getIntent().getSerializableExtra(EXTRA_DATE);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);

        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        null);

        mConfirmButton = (Button) view.findViewById(R.id.date_picker_button);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(mDate);
                calendar2.set(Calendar.YEAR, mDatePicker.getYear());
                calendar2.set(Calendar.MONTH, mDatePicker.getMonth());
                calendar2.set(Calendar.DAY_OF_MONTH, mDatePicker.getDayOfMonth());

                sendResult(Activity.RESULT_OK, calendar2.getTime());

                if(getTargetFragment() != null)
                    dismiss();
                else
                    getActivity().finish();
            }
        });

        return view;
    }

    private void sendResult(int resultCode, Date date){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        if(getTargetFragment() != null)
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
        else
            getActivity().setResult(Activity.RESULT_OK, intent);
    }

    public static DatePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
