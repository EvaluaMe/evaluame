package com.garciparedes.evaluame.viewholders.cards;

import android.view.View;
import android.view.ViewGroup;

import com.doomonafireball.betterpickers.numberpicker.NumberPickerBuilder;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerDialogFragment;
import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.activities.MainActivity;

/**
 * Created by garciparedes on 23/6/15.
 */
public class CardViewHolderNumberPicker extends CardViewHolderEditText implements NumberPickerDialogFragment.NumberPickerDialogHandler{

    private NumberPickerBuilder numberPicker;

    public CardViewHolderNumberPicker(ViewGroup parent) {
        super(parent);
    }

    @Override
    public void setup(String text, int hint, int error, int image) {
        super.setup(text, hint, error, image);

        numberPicker = new NumberPickerBuilder();
        numberPicker
                .setFragmentManager(
                        ((MainActivity)getContext()).getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment_Light)
                        .setTargetFragment(((MainActivity)getContext()).getCurrentFragment())
                        .setMaxNumber(10)
                        .setMinNumber(0)
                        .setPlusMinusVisibility(View.INVISIBLE)
                        .setLabelText(getStringResource(R.string.over_ten))
                        .setReference(0);

        getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    numberPicker.show();
                }
            }
        });

        getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPicker.show();
            }
        });
    }


    @Override
    public void onDialogNumberSet(int reference, int number, double decimal, boolean isNegative, double fullNumber) {

    }
}

