package com.garciparedes.evaluame.viewholders.cards;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.garciparedes.evaluame.R;

/**
 * Created by garciparedes on 18/6/15.
 */
public class CardViewHolderPicker extends BaseCardViewHolder {
    // each data item is just a string in this case
    //public TextView mTextViewTitle;
    private ImageView mImgView;
    private TextView mTextViewLabel;
    private TextView mTextViewValue;
    private LinearLayout mLinearLayout;

    public CardViewHolderPicker(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_picker, parent, false));

        //mTextViewTitle = (TextView) v.findViewById(R.id.card_view_atrib_title);
        mImgView = (ImageView) itemView.findViewById(R.id.card_view_image_view);
        mTextViewLabel = (TextView) itemView.findViewById(R.id.card_view_text_view_label);
        mTextViewValue = (TextView) itemView.findViewById(R.id.card_view_text_view_value);

        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.card_view_linear_layout);
    }


    public void setImage(int name){
        setImage(getDrawableResource(name));
    }

    public void setImage(Drawable drawable){
        mImgView.setImageDrawable(drawable);
    }

    public void setTextLabel(int name) {
        setTextLabel(getStringResource(name));
    }

    public void setTextLabel(String string){
        mTextViewLabel.setText(string);
    }

    public void setText(int name) {
        setText(getStringResource(name));
    }

    public void setText(String string){
        mTextViewValue.setText(string);
    }


    public LinearLayout getLinearLayout() {
        return mLinearLayout;
    }
}
