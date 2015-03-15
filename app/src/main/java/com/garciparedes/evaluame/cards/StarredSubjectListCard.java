package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.fragments.SubjectFragment;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;
import it.gmariotti.cardslib.library.prototypes.LinearListView;

/**
 * Created by garciparedes on 25/2/15.
 */
public class StarredSubjectListCard extends CardWithList {

    private BarChart mBarChart;
    private FragmentManager fragmentManager;

    public StarredSubjectListCard(Context context, FragmentManager fragmentManager) {
        super(context);
        this.fragmentManager = fragmentManager;
        init();
    }


    @Override
    protected CardHeader initCardHeader() {

        CustomCardHeader cardHeader = new CustomCardHeader(getContext());
        cardHeader.setTitle(getContext().getString(R.string.starred_subjects));

        return cardHeader;
    }


    @Override
    protected void initCard() {
        setEmptyViewViewStubLayoutId(R.layout.card_extras_empty_starred_subjects);
        setInnerLayout(R.layout.card_starred_subjects);

    }


    @Override
    protected List<ListObject> initChildren() {

        List<ListObject> mObjects = new ArrayList<ListObject>();

        for(int i = 0; i < ListDB.getMasterList().size(); i++){
            if(ListDB.get(i).isStarred()){
                mObjects.add(new TestObject(this, ListDB.get(i)));
            }
        }

        return mObjects;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

        //Retrieve elements
        mBarChart = (BarChart) parent.findViewById(R.id.card_chart_bar);

        if (mBarChart != null) {
            mBarChart.setTouchEnabled(false);
            mBarChart.setDescription("");
            mBarChart.setDrawLegend(false);
            mBarChart.setDrawXLabels(false);
            setValues();
        }
    }

    @Override
    public View setupChildView(int i, ListObject listObject, View view, ViewGroup viewGroup) {
        //Setup the ui elements inside the item
        TextView nameMarkView = (TextView) view.findViewById(R.id.card_subject_list_starred_inner_name);
        ImageView imgView = (ImageView) view.findViewById(R.id.card_subject_list_starred_inner_image);

        //Retrieve the values from the object
        final TestObject testObject = (TestObject) listObject;

        nameMarkView.setText(testObject.mSubject.getName());
        TextDrawable drawable = TextDrawable.builder().buildRound(
                testObject.mSubject.getDropCap()
                , testObject.mSubject.getColor()
        );
        imgView.setImageDrawable(drawable);


        return view;
    }


    @Override
    public int getChildLayoutId() {
        return R.layout.card_subject_list_starred_inner_main;
    }


    private class TestObject extends DefaultListObject {

        Subject mSubject;


        public TestObject(Card parentCard, Subject subject) {
            super(parentCard);
            this.mSubject = subject;
            init();


        }

        private void init() {

            //OnClick Listener
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(LinearListView parent, View view, int position, ListObject object) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, SubjectFragment.newInstance(mSubject))
                            .commit();
                }
            });

        }
    }

    /**
     *
     */
    public void setValues() {

        ArrayList<BarEntry> valsComp1 = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<Subject> starredSubjects = ListDB.getStarredSubjects();

        for (int i = 0; i < starredSubjects.size(); i++) {
            colors.add(starredSubjects.get(i).getColor());
            BarEntry subject = new BarEntry(starredSubjects.get(i).getWeightedAverage(), i);
            valsComp1.add(subject);
            xVals.add(starredSubjects.get(i).getName());
        }

        BarDataSet setComp1 = new BarDataSet(valsComp1, "Company 1");
        setComp1.setColors(colors);
        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(setComp1);


        BarData data = new BarData(xVals, dataSets);
        mBarChart.setData(data);

        mBarChart.animateY(1000);

    }
}
