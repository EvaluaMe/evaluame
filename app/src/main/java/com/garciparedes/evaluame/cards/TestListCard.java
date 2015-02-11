package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.items.Test;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;
import it.gmariotti.cardslib.library.prototypes.LinearListView;


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
        setSwipeable(true);
        setOnSwipeListener(new OnSwipeListener() {
            @Override
            public void onSwipe(Card card) {
                Toast.makeText(getContext(), "Swipe on " + card.getCardHeader().getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected List<ListObject> initChildren() {

        //Init the list
        List<ListObject> mObjects = new ArrayList<ListObject>();

        for (int i = 0 ; i< subject.getTestList().size(); i++) {
            TestObject testObject = new TestObject(this, subject.getTestElement(i));
            testObject.setObjectId(subject.getTestElement(i).getName());
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
        final TestObject testObject= (TestObject) listObject;

        nameMarkView.setText(testObject.getTest().getName());
        markTextView.setText(testObject.getTest().getMarkString());
        percentageTextView.setText(testObject.getTest().getPercentageString());


        testObject.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipe(ListObject listObject, boolean b) {
                subject.removeTest(((TestObject)listObject).getTest());
            }
        });

        return  view;
    }


    @Override
    public int getChildLayoutId() {
        return R.layout.card_test_list_inner_main;
    }


    private class TestObject extends DefaultListObject {

        private Test mTest;

        public TestObject(Card parentCard, Test test) {
            super(parentCard);
            this.mTest = test;
            init();
        }

        public Test getTest() {
            return mTest;
        }

        private void init() {
            //OnClick Listener
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(LinearListView parent, View view, int position, ListObject object) {
                    Toast.makeText(getContext(), "Click on " + getObjectId(), Toast.LENGTH_SHORT).show();
                }
            });

            /*
            //OnItemSwipeListener
            setOnItemSwipeListener(new OnItemSwipeListener() {
                @Override
                public void onItemSwipe(ListObject object, boolean dismissRight) {

                    Toast.makeText(getContext(),
                            "Swipe on "
                            + ((TestObject) object).getTest().getName()
                            , Toast.LENGTH_SHORT
                    ).show();
                }
            });
            */
        }
    }
}
