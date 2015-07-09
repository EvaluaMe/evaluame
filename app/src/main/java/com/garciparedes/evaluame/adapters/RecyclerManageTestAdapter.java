package com.garciparedes.evaluame.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.viewholders.cards.BaseCardViewHolder;
import com.garciparedes.evaluame.viewholders.cards.CardViewHolderDatePicker;
import com.garciparedes.evaluame.viewholders.cards.CardViewHolderEditText;
import com.garciparedes.evaluame.viewholders.cards.CardViewHolderNumberPicker;
import com.garciparedes.evaluame.viewholders.cards.CardViewHolderTimePicker;

import java.util.Calendar;


/**
 * Created by garciparedes on 18/6/15.
 */
public class RecyclerManageTestAdapter extends RecyclerView.Adapter<BaseCardViewHolder>
        implements CardViewHolderDatePicker.ViewHolderDateCallbacks
        , CardViewHolderNumberPicker.OnNumberCallbacks
        , CardViewHolderEditText.OnEditTextCallbacks
        , CardViewHolderTimePicker.OnTimeCallbacks{

    private static final int TYPE_INPUT_NAME = 0;
    private static final int TYPE_INPUT_VALUE = 1;
    private static final int TYPE_INPUT_TYPE = 2;
    private static final int TYPE_INPUT_DATE = 3;
    private static final int TYPE_INPUT_TIME = 4;
    private static final int TYPE_INPUT_SCORE = 5;


    private Exam mExam;
    private Subject mSubject;

    public RecyclerManageTestAdapter (Subject subject, Exam exam){
        this.mSubject = subject;
        this.mExam = exam;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    /**
     * Called when RecyclerView needs a new {@link BaseCardViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(BaseCardViewHolder, int)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(BaseCardViewHolder, int)
     */
    @Override
    public BaseCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case TYPE_INPUT_NAME:
                return new CardViewHolderEditText(parent, TYPE_INPUT_NAME, this);

            case TYPE_INPUT_VALUE:
                return new CardViewHolderNumberPicker(parent, TYPE_INPUT_VALUE, this);

            case TYPE_INPUT_TYPE:
                return new CardViewHolderEditText(parent, TYPE_INPUT_TYPE, this);

            case TYPE_INPUT_DATE:
                return new CardViewHolderDatePicker(parent, TYPE_INPUT_DATE, this);

            case TYPE_INPUT_TIME:
                return new CardViewHolderTimePicker(parent, TYPE_INPUT_TIME, this);

            case TYPE_INPUT_SCORE:
                return new CardViewHolderNumberPicker(parent, TYPE_INPUT_SCORE, this);

            default:
                return null;
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link BaseCardViewHolder#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use {@link BaseCardViewHolder#getAdapterPosition()} which will have
     * the updated adapter position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(BaseCardViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case TYPE_INPUT_NAME:
                setupName((CardViewHolderEditText) holder);
                break;

            case TYPE_INPUT_VALUE:
                setupValue((CardViewHolderNumberPicker) holder);
                break;

            case TYPE_INPUT_TYPE:
                //setupType((CardViewHolderSpinner) holder);
                break;

            case TYPE_INPUT_DATE:
                setupDate((CardViewHolderDatePicker) holder);
                break;

            case TYPE_INPUT_TIME:
                setupTime((CardViewHolderTimePicker) holder);
                break;

            case TYPE_INPUT_SCORE:
                setupScore((CardViewHolderNumberPicker) holder);
                break;

            default:
                break;
        }

    }





    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setupName(final CardViewHolderEditText holder){
        holder.setup(mExam.getName(), R.string.name, R.string.fail_name, R.drawable.ic_action_about);
    }

    private void setupValue(CardViewHolderNumberPicker holder) {

        holder.setup(mExam.getPercentageString()
                , R.string.value, R.string.value_error
                , R.drawable.ic_action_weight
        );
    }

    private void setupDate(CardViewHolderDatePicker holder) {
        holder.setup(mExam.getDateString(holder.getContext())
                ,R.string.date,R.string.date_error
                ,R.drawable.ic_action_event
        );
    }

    private void setupTime(CardViewHolderTimePicker holder) {
        holder.setup(mExam.getTimeString(holder.getContext())
                ,R.string.time,R.string.time_error
                ,R.drawable.ic_action_time
        );
    }

    private void setupScore(CardViewHolderNumberPicker holder) {
        holder.setup(mExam.getMarkString()
                , R.string.score, R.string.score_error
                , R.drawable.ic_action_accept_dark
        );
    }

    /**
     * Called when an item in the navigation drawer is selected.
     *
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void onDateChanged(int id, int year, int month, int day) {
        if (id == TYPE_INPUT_DATE) {
            mExam.getDate().set(Calendar.YEAR, year);
            mExam.getDate().set(Calendar.MONTH, month);
            mExam.getDate().set(Calendar.DAY_OF_MONTH, day);
        }
    }

    /**
     * Called when an item in the navigation drawer is selected.
     *
     * @param number
     */
    @Override
    public void onNumberChanged(int id, double number) {
        if(id == TYPE_INPUT_VALUE){
            mExam.setPercentage((float) number);
        } else if(id == TYPE_INPUT_SCORE){
            mExam.setMark((float) number);
        }
    }

    /**
     * Called when an item in the navigation drawer is selected.
     *
     * @param id
     * @param text
     */
    @Override
    public void onTextChanged(int id, String text) {
        if(id == TYPE_INPUT_NAME){
            mExam.setName(text);
        }
    }

    /**
     * Called when an item in the navigation drawer is selected.
     *
     * @param id
     * @param hour
     * @param minute
     */
    @Override
    public void onTimeChanged(int id, int hour, int minute) {
        if (id == TYPE_INPUT_TIME){
            mExam.getDate().set(Calendar.HOUR, hour);
            mExam.getDate().set(Calendar.MINUTE, minute);
        }
    }
}
