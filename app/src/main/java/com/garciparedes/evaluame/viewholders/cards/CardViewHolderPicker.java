package com.garciparedes.evaluame.viewholders.cards;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
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
    private EditText mEditText;
    private TextInputLayout mTextInputLayout;

    public CardViewHolderPicker(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_picker, parent, false));

        //mTextViewTitle = (TextView) v.findViewById(R.id.card_view_atrib_title);
        mImgView = (ImageView) itemView.findViewById(R.id.card_view_image_view);
        mEditText = (EditText) itemView.findViewById(R.id.card_edit_text);
        mTextInputLayout = (TextInputLayout) itemView.findViewById(R.id.card_view_text_input_layout);
    }



    public void setError(int name) {
        setError(getStringResource(name));
    }

    public void setError(String string){
        mTextInputLayout.setError(string);
    }


    public void setHint(int name) {
        setHint(getStringResource(name));
    }

    public void setHint(String string){
        mTextInputLayout.setHint(string);
    }


    public void setImage(int name){
        setImage(getDrawableResource(name));
    }

    public void setImage(Drawable drawable){
        mImgView.setImageDrawable(drawable);
    }


    public void setText(int name) {
        setText(getStringResource(name));
    }

    public void setText(String string){
        mEditText.setText(string);
    }


    public String getText(){
        return mEditText.getText().toString();
    }


    public EditText getEditText() {
        return mEditText;
    }


    public TextInputLayout getTextInputLayout() {
        return mTextInputLayout;
    }

}
