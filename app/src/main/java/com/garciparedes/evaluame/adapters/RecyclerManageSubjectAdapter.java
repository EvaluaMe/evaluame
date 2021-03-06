package com.garciparedes.evaluame.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.viewholders.cards.CardViewHolderEditText;

/**
 * Created by garciparedes on 17/6/15.
 */
public class RecyclerManageSubjectAdapter extends RecyclerView.Adapter<CardViewHolderEditText>
        implements CardViewHolderEditText.OnEditTextCallbacks{

    private static final int TYPE_INPUT_NAME = 0;
    private static final int TYPE_INPUT_DESCRIPTION = 1;


    private Subject mSubject;

    public RecyclerManageSubjectAdapter (Subject subject){
        this.mSubject = subject;
    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }


    /**
     * Called when RecyclerView needs a new {@link CardViewHolderEditText} of the given type to represent
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
        switch (viewType) {

            case TYPE_INPUT_NAME:
                return new CardViewHolderEditText(parent, TYPE_INPUT_NAME, this);

            case TYPE_INPUT_DESCRIPTION:
                return new CardViewHolderEditText(parent, TYPE_INPUT_DESCRIPTION, this);

            default:
                return null;
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link CardViewHolderEditText#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use {@link CardViewHolderEditText#getAdapterPosition()} which will have
     * the updated adapter position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final CardViewHolderEditText holder, int position) {

        switch (holder.getItemViewType()){
            case TYPE_INPUT_NAME:

                holder.setup(mSubject.getName(), R.string.name,  R.drawable.ic_action_about);
                break;
            case TYPE_INPUT_DESCRIPTION:

                holder.setup(mSubject.getDescription(), R.string.description,  R.drawable.ic_action_event);
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

    /**
     * Called when an item in the navigation drawer is selected.
     *
     * @param id
     * @param text
     */
    @Override
    public void onTextChanged(int id, String text) {
        if (id == TYPE_INPUT_NAME) {
            mSubject.setName(text);
        } else if(id == TYPE_INPUT_DESCRIPTION){
            mSubject.setDescription(text);

        }
    }
}
