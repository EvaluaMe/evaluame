package com.garciparedes.evaluame.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;

import java.util.ArrayList;

/**
 * Created by garciparedes on 21/2/15.
 */
public class NavDrawerAdapter extends ArrayAdapter<Subject> {

    private final Context context;
    private final ArrayList<Subject> modelsArrayList;

    public NavDrawerAdapter(Context context, ArrayList<Subject> modelsArrayList) {

        super(context, R.layout.target_item_nav_drawer, modelsArrayList);

        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater

        View rowView = inflater.inflate(R.layout.target_item_nav_drawer, parent, false);

        // 3. Get icon,title & counter views from the rowView
        ImageView imgView = (ImageView) rowView.findViewById(R.id.item_image_view);
        TextView titleView = (TextView) rowView.findViewById(R.id.item_title);
        //TextView counterView = (TextView) rowView.findViewById(R.id.item_counter);

        TextDrawable drawable = TextDrawable.builder().buildRound(
                modelsArrayList.get(position).getName().substring(0,1)
                , generator.getRandomColor()
        );
        imgView.setImageDrawable(drawable);

        // 4. Set the text for textView
        //imgView.setImageResource(modelsArrayList.get(position).getIcon());
        titleView.setText(modelsArrayList.get(position).getName());
//      counterView.setText(modelsArrayList.get(position).getExamList().size());

        return rowView;
    }


}
