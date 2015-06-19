package com.garciparedes.evaluame.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.viewholders.cards.BaseCardViewHolder;
import com.garciparedes.evaluame.viewholders.cards.CardViewHolderEditText;
import com.garciparedes.evaluame.viewholders.cards.CardViewHolderPicker;


/**
 * Created by garciparedes on 18/6/15.
 */
public class RecyclerManageTestAdapter extends RecyclerView.Adapter<BaseCardViewHolder>{

    private static final int TYPE_IMPUT_NAME = 0;
    private static final int TYPE_IMPUT_VALUE = 1;
    private static final int TYPE_IMPUT_TYPE = 2;
    private static final int TYPE_IMPUT_DATE = 3;
    private static final int TYPE_IMPUT_TIME = 4;
    private static final int TYPE_IMPUT_SCORE = 5;


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
            case TYPE_IMPUT_NAME:
                return new CardViewHolderEditText(parent);

            case TYPE_IMPUT_VALUE:
                return new CardViewHolderPicker(parent);

            case TYPE_IMPUT_TYPE:
                return new CardViewHolderEditText(parent);

            case TYPE_IMPUT_DATE:
                return new CardViewHolderPicker(parent);

            case TYPE_IMPUT_TIME:
                return new CardViewHolderPicker(parent);

            case TYPE_IMPUT_SCORE:
                return new CardViewHolderPicker(parent);

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
            case TYPE_IMPUT_NAME:
                setupName((CardViewHolderEditText) holder);
                break;

            case TYPE_IMPUT_VALUE:
                setupValue((CardViewHolderPicker) holder);
                break;

            case TYPE_IMPUT_TYPE:
                //setupType((CardViewHolderSpinner) holder);
                break;

            case TYPE_IMPUT_DATE:
                setupDate((CardViewHolderPicker) holder);
                break;

            case TYPE_IMPUT_TIME:
                setupTime((CardViewHolderPicker) holder);
                break;

            case TYPE_IMPUT_SCORE:
                setupScore((CardViewHolderPicker) holder);
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
        holder.setImage(R.drawable.ic_action_about);
        holder.setHint(R.string.name);
        holder.setError(R.string.fail_name);
        holder.setText(mExam.getName());
        holder.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mSubject.setName(holder.getText());
            }
        });
    }

    private void setupValue(CardViewHolderPicker holder) {
        holder.setImage(R.drawable.ic_action_weight);
        holder.setHint(R.string.value);
        holder.setText(mExam.getPercentageString());

    }

    private void setupDate(CardViewHolderPicker holder) {
        holder.setImage(R.drawable.ic_action_event);
        holder.setHint(R.string.date);
        holder.setText(mExam.getDateString(holder.getContext()));
    }

    private void setupTime(CardViewHolderPicker holder) {
        holder.setImage(R.drawable.ic_action_time);
        holder.setHint(R.string.time);
        holder.setText(mExam.getTimeString(holder.getContext()));
    }

    private void setupScore(CardViewHolderPicker holder) {
        holder.setImage(R.drawable.ic_action_accept_dark);
        holder.setHint(R.string.score);
        holder.setText(mExam.getMarkString());
    }
}
