package com.garciparedes.evaluame.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.garciparedes.evaluame.utils.ListAtrib;
import com.garciparedes.evaluame.viewholders.cards.BaseCardViewHolder;
import com.garciparedes.evaluame.viewholders.cards.CardViewHolderTextView;

import java.util.ArrayList;

/**
 * Created by garciparedes on 14/6/15.
 */
public class RecyclerAtribImageTextAdapter extends RecyclerView.Adapter<BaseCardViewHolder>{

    private static final int TYPE_TEXT_VIEW = 0;


    private ArrayList<ListAtrib> mData;

    public RecyclerAtribImageTextAdapter (ArrayList<ListAtrib> data){
        mData = data;
    }


    @Override
    public int getItemViewType(int position) {
        return TYPE_TEXT_VIEW;
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
            case TYPE_TEXT_VIEW:
                return new CardViewHolderTextView(parent, TYPE_TEXT_VIEW);
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
            case TYPE_TEXT_VIEW:
                ((CardViewHolderTextView) holder).setupView(mData.get(position));
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
        return mData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
