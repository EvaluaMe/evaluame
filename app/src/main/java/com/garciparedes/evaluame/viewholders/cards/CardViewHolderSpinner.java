package com.garciparedes.evaluame.viewholders.cards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.garciparedes.evaluame.R;

/**
 * Created by garciparedes on 13/7/15.
 */
public class CardViewHolderSpinner extends BaseCardViewHolder {

    private OnSpinnerCallbacks onSpinnerCallbacks;
    private Spinner spinner;

    public CardViewHolderSpinner(View parent, int id) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_spinner, (ViewGroup) parent, false), id);
        spinner = (Spinner) itemView.findViewById(R.id.card_view_spinner);
    }

    public CardViewHolderSpinner(ViewGroup parent, int id, OnSpinnerCallbacks onSpinnerCallbacks) {
        this(parent, id);
        this.onSpinnerCallbacks = onSpinnerCallbacks;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void setAdapter(String[] list){
        getSpinner().setAdapter(new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                list));
    }


    public void setup( String[] list, int index) {
        setAdapter(list);
        System.out.println(index);

        getSpinner().setSelection(index);
        getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                onSpinnerCallbacks.onPosChanged(getId(), position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }


    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public interface OnSpinnerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onPosChanged(int id, int pos);
    }
}
