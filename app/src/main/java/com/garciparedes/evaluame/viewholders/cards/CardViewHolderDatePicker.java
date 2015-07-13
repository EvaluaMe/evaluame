package com.garciparedes.evaluame.viewholders.cards;

import android.app.DatePickerDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by garciparedes on 23/6/15.
 */
public class CardViewHolderDatePicker extends CardViewHolderEditText {

    private Calendar myCalendar;

    private ViewHolderDateCallbacks viewHolderDateCallbacks;

    private DatePickerDialog.OnDateSetListener date;
    private DatePickerDialog pickerDialog;

    public CardViewHolderDatePicker(ViewGroup parent, int id, ViewHolderDateCallbacks viewHolderDateCallbacks) {
        super(parent, id);
        this.myCalendar = Calendar.getInstance();
        this.viewHolderDateCallbacks = viewHolderDateCallbacks;
    }

    private Calendar getCalendar(){
        return myCalendar;
    }

    @Override
    public void setup(String text, int hint, int image) {
        super.setup(text, hint, image);


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                // TODO Auto-generated method stub
                getCalendar().set(Calendar.YEAR, year);
                getCalendar().set(Calendar.MONTH, monthOfYear);
                getCalendar().set(Calendar.DAY_OF_MONTH, dayOfMonth);
                update();
            }

        };

        pickerDialog = new DatePickerDialog(getContext(), date, getCalendar()
                .get(Calendar.YEAR), getCalendar().get(Calendar.MONTH),
                getCalendar().get(Calendar.DAY_OF_MONTH));

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
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                // TODO Auto-generated method stub
                getCalendar().set(Calendar.YEAR, year);
                getCalendar().set(Calendar.MONTH, monthOfYear);
                getCalendar().set(Calendar.DAY_OF_MONTH, dayOfMonth);
                update();
            }

        };    }

    private void update() {

        viewHolderDateCallbacks.onDateChanged(getId()
                ,myCalendar.get(Calendar.YEAR)
                ,myCalendar.get(Calendar.MONTH)
                ,myCalendar.get(Calendar.DAY_OF_MONTH)
        );

        String myFormat = "EEE, MMMMM d, yyyy"	; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        getEditText().setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface ViewHolderDateCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onDateChanged(int id, int year, int month, int day);
    }

}
