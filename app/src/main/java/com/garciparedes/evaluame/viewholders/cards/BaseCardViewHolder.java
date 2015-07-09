package com.garciparedes.evaluame.viewholders.cards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

/**
 * Created by garciparedes on 17/6/15.
 */
public abstract class BaseCardViewHolder extends RecyclerView.ViewHolder {

    private int id;

    public BaseCardViewHolder(View itemView, int id) {
        super(itemView);
        this.id = id;
    }


    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getStringResource(int name){
        return getContext().getResources().getString(name);
    }

    public Drawable getDrawableResource(int name){
        return getContext().getResources().getDrawable(name);
    }

    public Context getContext() {
        return itemView.getContext();
    }
}
