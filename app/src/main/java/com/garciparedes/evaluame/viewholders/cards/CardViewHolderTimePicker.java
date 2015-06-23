package com.garciparedes.evaluame.viewholders.cards;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
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
    public CardViewHolderTimePicker(ViewGroup parent) {
        super(parent);
        this.myCalendar = Calendar.getInstance();

    }

    private Calendar getCalendar(){
        return myCalendar;
    }
    @Override
    public void setup(String text, int hint, int error, int image) {
        super.setup(text, hint, error, image);

        time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                // TODO Auto-generated method stub
                getCalendar().set(Calendar.HOUR, i);
                getCalendar().set(Calendar.MINUTE, i1);
                updateLabel();
            }
        };

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

    private void updateLabel() {

        String myFormat = "HH:MM"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        getEditText().setText(sdf.format(myCalendar.getTime()));
    }

}