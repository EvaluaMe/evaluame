package com.garciparedes.evaluame.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.activities.MainActivity;
import com.garciparedes.evaluame.fragments.subject.SubjectFragment;
import com.garciparedes.evaluame.items.Subject;

import java.util.ArrayList;

/**
 * Created by garciparedes on 12/6/15.
*/
public class RecyclerSubjectListAdapter extends RecyclerView.Adapter<RecyclerSubjectListAdapter.ViewHolder> {

    private ArrayList<Subject> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public ImageView imgView;
        public TextView mTextView;


        public ViewHolder(View v) {
            super(v);
            view = v;
            imgView = (ImageView) itemView.findViewById(R.id.item_image_view);;
            mTextView = (TextView) itemView.findViewById(R.id.item_title);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerSubjectListAdapter(ArrayList<Subject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_subject, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {

        final Subject subject = mDataset.get(i);

        TextDrawable drawable = TextDrawable.builder().buildRound(
                subject.getDropCap()
                , subject.getColor()
        );

        holder.imgView.setImageDrawable(drawable);
        holder.mTextView.setText(subject.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) view.getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, SubjectFragment.newInstance(subject))
                        .commit();
            }
        });
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}