package com.garciparedes.evaluame.adapters;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.viewholders.cards.CardViewHolderEditText;

/**
 * Created by garciparedes on 17/6/15.
 */
public class RecyclerManageSubjectAdapter extends RecyclerView.Adapter<CardViewHolderEditText>{

    private static final int TYPE_IMPUT_NAME = 0;
    private static final int TYPE_IMPUT_DESCRIPTION = 1;


    private Subject mSubject;

    public RecyclerManageSubjectAdapter (Subject subject){
        this.mSubject = subject;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        //public TextView mTextViewTitle;
        public ImageView mImgView;
        public EditText mEditText;
        public TextInputLayout textInputLayout;

        public ViewHolder(View v) {
            super(v);
            view = v;

            //mTextViewTitle = (TextView) v.findViewById(R.id.card_view_atrib_title);
            //mImgView = (ImageView) v.findViewById(R.id.card_view_atrib_image_view);
            mEditText = (EditText) v.findViewById(R.id.card_edit_text_subject);
            textInputLayout = (TextInputLayout) v.findViewById(R.id.card_view_text_input_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(CardViewHolderEditText, int)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(CardViewHolderEditText, int)
     */
    @Override
    public CardViewHolderEditText onCreateViewHolder(ViewGroup parent, int viewType) {
        CardViewHolderEditText pvh = new CardViewHolderEditText(parent);
        return pvh;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link ViewHolder#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will have
     * the updated adapter position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final CardViewHolderEditText holder, int position) {

        switch (holder.getItemViewType()){
            case TYPE_IMPUT_NAME:

                holder.setHint(R.string.name);
                holder.setError(R.string.fail_name);

                holder.setText(mSubject.getName());
                holder.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        mSubject.setName(holder.getText());
                    }
                });
                break;
            case TYPE_IMPUT_DESCRIPTION:
                holder.setHint(R.string.description);
                holder.setText(mSubject.getDescription());
                holder.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        mSubject.setDescription(holder.getText());
                    }
                });
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
        return 2;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
