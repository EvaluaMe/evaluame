package com.garciparedes.evaluame.viewholders.cards;

import android.app.DatePickerDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.garciparedes.evaluame.utils.Date;

import java.util.Calendar;

/**
 * CardViewHolderDatePicker class.
 * It is used to create DatePickers.
 *
 * Created by garciparedes on 23/6/15.
 */
public class CardViewHolderDatePicker extends CardViewHolderEditText {

    private Calendar calendar;

    private OnDateHolderCallbacks onDateHolderCallbacks;

    private DatePickerDialog.OnDateSetListener date;
    private DatePickerDialog pickerDialog;


    /**
     * Constructor of the class.
     *
     * @param parent Parent View.
     * @param id Holder id.
     * @param onDateHolderCallbacks Class which implements OnDateHolderCallbacks.
     */
    public CardViewHolderDatePicker(ViewGroup parent, int id, OnDateHolderCallbacks onDateHolderCallbacks) {
        super(parent, id);
        this.onDateHolderCallbacks = onDateHolderCallbacks;
    }


    /**
     * Getter of calendar.
     *
     * @return calendar.
     */
    private Calendar getCalendar(){
        return calendar;
    }


    /**
     * Setter of calendar.
     *
     * @param calendar calendar.
     */
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        updateText();
    }


    /**
     * Setup method. It's initialize the Holder
     * and customized.
     * @param calendar Date to show and modify.
     * @param hint Hint of EditText.
     * @param image Image to complement.
     */
    public void setup(Calendar calendar, int hint, int image) {
        super.setup(null, hint, image);
        setCalendar(calendar);

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                getCalendar().set(Calendar.YEAR, year);
                getCalendar().set(Calendar.MONTH, monthOfYear);
                getCalendar().set(Calendar.DAY_OF_MONTH, dayOfMonth);
                update();
            }
        };

        pickerDialog = new DatePickerDialog(getContext(), date, getCalendar()
                .get(Calendar.YEAR), getCalendar().get(Calendar.MONTH),
                getCalendar().get(Calendar.DAY_OF_MONTH));

        getEditText().setFocusable(false);
        getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickerDialog.show();
            }
        });
    }


    /**
     * Setter of callBack.
     */
    @Override
    public void setCallBack() {
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                getCalendar().set(Calendar.YEAR, year);
                getCalendar().set(Calendar.MONTH, monthOfYear);
                getCalendar().set(Calendar.DAY_OF_MONTH, dayOfMonth);
                update();
            }

        };
    }


    /**
     * Update method.
     * It call {@link OnDateHolderCallbacks}
     * to return user input after select it
     * and update text label on the card.
     */
    private void update() {
        onDateHolderCallbacks.onDateChanged(getId()
                , getCalendar().get(Calendar.YEAR)
                , getCalendar().get(Calendar.MONTH)
                , getCalendar().get(Calendar.DAY_OF_MONTH)
        );
        updateText();
    }


    /**
     * updateText method.
     * It setText() with calendar data
     * and internal system format.
     */
    private void updateText() {
        setText(Date.dateToString(getContext(),getCalendar()));
    }



    /**
     * Callbacks interface that all Adapters using this holder must implement.
     */
    public interface OnDateHolderCallbacks {


        /**
         * Called when date change.
         */
        void onDateChanged(int id, int year, int month, int day);
    }

}
