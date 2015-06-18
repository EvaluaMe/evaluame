package com.garciparedes.evaluame.viewholders.cards;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

/**
 * Created by garciparedes on 17/6/15.
 */
public abstract class BaseCardViewHolder extends RecyclerView.ViewHolder {

    public BaseCardViewHolder(View itemView) {
        super(itemView);
    }

    public String getStringResource(int name){
        return itemView.getContext().getResources().getString(name);
    }

    public Drawable getDrawableResource(int name){
        return itemView.getContext().getResources().getDrawable(name);
    }
}
