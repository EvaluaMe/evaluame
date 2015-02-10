package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.items.Test;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;


/**
 * Created by garciparedes on 9/2/15.
 */
public class TestListCard extends CardWithList {


    private Subject subject;


    public TestListCard(Context context, Subject subject) {
        super(context);
        this.subject = subject;
        init();
    }


    @Override
    protected CardHeader initCardHeader() {
        CardHeader cardHeader = new CardHeader(getContext());
        cardHeader.setTitle(getContext().getString(R.string.title_exams));
        return cardHeader;
    }


    @Override
    protected void initCard() {
        //setSwipeable(true);

    }


    @Override
    protected List<ListObject> initChildren() {

        //Init the list
        List<ListObject> mObjects = new ArrayList<ListObject>();

        for (int i = 0 ; i< subject.getTestList().size(); i++) {
            TestObject testObject = new TestObject(this, subject.getTestElement(i));
            testObject.setSwipeable(true);
            mObjects.add(testObject);
        }


        return mObjects;
    }


    @Override
    public View setupChildView(int i, ListObject listObject, View view, ViewGroup viewGroup) {
        //Setup the ui elements inside the item
        TextView nameMarkView = (TextView) view.findViewById(R.id.card_test_list_inner_name);
        TextView markTextView = (TextView) view.findViewById(R.id.card_test_list_inner_mark);
        TextView percentageTextView = (TextView) view.findViewById(R.id.card_test_list_inner_percentage);

        //Retrieve the values from the object
        TestObject testObject= (TestObject) listObject;
        nameMarkView.setText(testObject.test.getName());

        markTextView.setText(testObject.test.getMarkString());
        percentageTextView.setText(testObject.test.getPercentageString());

        return  view;
    }


    @Override
    public int getChildLayoutId() {
        return R.layout.card_test_list_inner_main;
    }


    private class TestObject extends DefaultListObject{

        Test test;

        public TestObject(Card parentCard, Test test) {
            super(parentCard);
            this.test = test;
        }
    }
}
