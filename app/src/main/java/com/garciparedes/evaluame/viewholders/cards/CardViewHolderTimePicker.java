package com.garciparedes.evaluame.viewholders.cards;

import android.app.TimePickerDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by garciparedes on 23/6/15.
 */
public class CardViewHolderTimePicker extends CardViewHolderEditText {

    private Calendar myCalendar;
    private TimePickerDialog.OnTimeSetListener time;
    private TimePickerDialog pickerDialog;
    private OnTimeCallbacks onTimeCallbacks;

    public CardViewHolderTimePicker(ViewGroup parent, int id, OnTimeCallbacks onTimeCallbacks) {
        super(parent, id);
        this.myCalendar = Calendar.getInstance();
        this.onTimeCallbacks = onTimeCallbacks;

    }

    private Calendar getCalendar(){
        return myCalendar;
    }
    @Override
    public void setup(String text, int hint, int error, int image) {
        super.setup(text, hint, error, image);

        pickerDialog = new TimePickerDialog(getContext(), time, getCalendar().get(Calendar.HOUR)
                , getCalendar().get(Calendar.MINUTE),
                DateFormat.is24HourFormat(getContext()));

        getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    pickerDialog.show();
                }
            }
        });

        getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickerDialog.show();
            }
        });
    }

    @Override
    public void setCallBack() {
        time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                // TODO Auto-generated method stub
                getCalendar().set(Calendar.HOUR, i);
                getCalendar().set(Calendar.MINUTE, i1);
                update();
            }
        };
    }
    private void update() {

        String myFormat = "HH:MM"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        onTimeCallbacks.onTimeChanged(getId(), myCalendar.get(Calendar.HOUR), myCalendar.get(Calendar.MINUTE));
        getEditText().setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface OnTimeCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onTimeChanged(int id, int hour, int minute);
    }

}