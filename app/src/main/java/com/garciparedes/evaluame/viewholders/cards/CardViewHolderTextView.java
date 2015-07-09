package com.garciparedes.evaluame.viewholders.cards;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.utils.ListAtrib;

/**
 * Created by garciparedes on 17/6/15.
 */

// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public class CardViewHolderTextView extends BaseCardViewHolder {
    // each data item is just a string in this case
    private ImageView mImgView;
    private TextView mTextView;


    public CardViewHolderTextView(ViewGroup parent, int id) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_atrib_image_text, parent, false), id);

        mImgView = (ImageView) itemView.findViewById(R.id.card_view_atrib_image_view);;
        mTextView = (TextView) itemView.findViewById(R.id.card_view_atrib_text_view);
    }


    public void setText(String string){
        mTextView.setText(string);
    }

    public void setImage(Drawable drawable){
        mImgView.setImageDrawable(drawable);
    }

    public void setupView(ListAtrib listAtrib) {
        setImage(listAtrib.getImage());
        setText(listAtrib.getString());
    }
}