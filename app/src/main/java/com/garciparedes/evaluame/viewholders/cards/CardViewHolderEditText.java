package com.garciparedes.evaluame.viewholders.cards;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.garciparedes.evaluame.R;

/**
 * Created by garciparedes on 17/6/15.
 */
// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public class CardViewHolderEditText extends BaseCardViewHolder {
    // each data item is just a string in this case
    //public TextView mTextViewTitle;
    private ImageView mImgView;
    private EditText mEditText;
    private TextInputLayout mTextInputLayout;
    private OnEditTextCallbacks onEditTextCallbacks;

    public CardViewHolderEditText(ViewGroup parent, int id) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_edit_text, parent, false), id);

        //mTextViewTitle = (TextView) v.findViewById(R.id.card_view_atrib_title);
        mImgView = (ImageView) itemView.findViewById(R.id.card_view_image_view);
        mEditText = (EditText) itemView.findViewById(R.id.card_edit_text_subject);
        mTextInputLayout = (TextInputLayout) itemView.findViewById(R.id.card_view_text_input_layout);
    }

    public CardViewHolderEditText(ViewGroup parent, int id, OnEditTextCallbacks onEditTextCallbacks) {
        this(parent, id);
        this.onEditTextCallbacks = onEditTextCallbacks;
    }

    public void setup( String text, int hint, int error, int image) {
        setText(text);
        setHint(hint);
        setError(error);
        setImage(image);

        setCallBack();
    }

    public void setCallBack(){
        getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                onEditTextCallbacks.onTextChanged(getId(), getText());
            }
        });
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

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public interface OnEditTextCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onTextChanged(int id, String text);
    }

}
