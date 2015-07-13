package com.garciparedes.evaluame.viewholders.cards;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ViewGroup;

/**
 * Created by garciparedes on 23/6/15.
 */
public class CardViewHolderNumberPicker extends CardViewHolderEditText {

    private OnNumberCallbacks onNumberCallbacks;

    public CardViewHolderNumberPicker(ViewGroup parent, int id
            , OnNumberCallbacks onNumberCallbacks) {

        super(parent, id);
        this.onNumberCallbacks = onNumberCallbacks;
    }

    @Override
    public void setup( String text, int hint, int image) {
        super.setup(text, hint, image);

        getEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

    }

    @Override
    public void setCallBack() {
        getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double value = 0.0;

                try{
                    value = Double.valueOf(getText());
                }catch (NumberFormatException e){
                }

                onNumberCallbacks.onNumberChanged(getId(), value);
            }
        });
    }


    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface OnNumberCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNumberChanged(int id, double number);
    }
}

