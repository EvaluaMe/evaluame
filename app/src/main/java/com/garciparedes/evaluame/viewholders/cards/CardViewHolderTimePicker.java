package com.garciparedes.evaluame.viewholders.cards;

import android.app.TimePickerDialog;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.garciparedes.evaluame.utils.Date;

import java.util.Calendar;

/**
 * CardViewHolderDatePicker class.
 * It is used to create TimePickers.
 *
 * Created by garciparedes on 23/6/15.
 */
public class CardViewHolderTimePicker extends CardViewHolderEditText {

    private Calendar calendar;
    private TimePickerDialog.OnTimeSetListener time;
    private TimePickerDialog pickerDialog;
    private OnTimeHolderCallbacks onTimeHolderCallbacks;


    /**
     * Constructor of the class.
     *
     * @param parent Parent View.
     * @param id Holder id.
     * @param onTimeHolderCallbacks Class which implements OnTimeCallbacks.
     */
    public CardViewHolderTimePicker(ViewGroup parent, int id, OnTimeHolderCallbacks onTimeHolderCallbacks) {
        super(parent, id);
        this.calendar = Calendar.getInstance();
        this.onTimeHolderCallbacks = onTimeHolderCallbacks;

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
     * Getter of calendar.
     *
     * @return calendar.
     */
    private Calendar getCalendar(){
        return calendar;
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

        pickerDialog = new TimePickerDialog(getContext(), time, getCalendar().get(Calendar.HOUR_OF_DAY)
                , getCalendar().get(Calendar.MINUTE),
                DateFormat.is24HourFormat(getContext()));

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
        time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                getCalendar().set(Calendar.HOUR_OF_DAY, i);
                getCalendar().set(Calendar.MINUTE, i1);
                update();
            }
        };
    }


    /**
     * Update method.
     * It call {@link OnTimeHolderCallbacks}
     * to return user input after select it
     * and update text label on the card.
     */
    private void update() {
        onTimeHolderCallbacks.onTimeChanged(getId()
                , getCalendar().get(Calendar.HOUR_OF_DAY)
                , getCalendar().get(Calendar.MINUTE)
        );
        updateText();
    }


    /**
     * updateText method.
     * It setText() with calendar data
     * and internal system format.
     */
    private void updateText(){
        setText(Date.timeToString(getContext(), getCalendar()));
    }


    /**
     * Callbacks interface that all Adapters using this holder must implement.
     */
    public interface OnTimeHolderCallbacks {


        /**
         * Called when time change.
         */
        void onTimeChanged(int id, int hour, int minute);
    }

}