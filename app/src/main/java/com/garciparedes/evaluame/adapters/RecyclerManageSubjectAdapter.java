package com.garciparedes.evaluame.adapters;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;

/**
 * Created by garciparedes on 17/6/15.
 */
public class RecyclerManageSubjectAdapter extends RecyclerView.Adapter<RecyclerManageSubjectAdapter.ViewHolder>{


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



    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public RecyclerManageSubjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_edit_text, parent, false);
        ViewHolder pvh = new ViewHolder(v);
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
    public void onBindViewHolder(final RecyclerManageSubjectAdapter.ViewHolder holder, int position) {


        switch (position){
            case 0:
                holder.textInputLayout.setHint(holder.view.getContext().getResources().getString(R.string.name));
                holder.textInputLayout.setError(holder.view.getContext().getResources().getString(R.string.fail_name));

                holder.mEditText.setText(mSubject.getName());
                holder.mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        mSubject.setName(holder.mEditText.getText().toString());
                    }
                });
                break;
            case 1:
                holder.textInputLayout.setHint(holder.view.getContext().getResources().getString(R.string.description));
                holder.mEditText.setText(mSubject.getDescription());
                holder.mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        mSubject.setDescription(holder.mEditText.getText().toString());
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
