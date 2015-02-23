package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.Util.Date;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;
import it.gmariotti.cardslib.library.prototypes.LinearListView;


/**
 * Created by garciparedes on 9/2/15.
 */
public class UpcomingExamListCard extends CardWithList {


    private ArrayList<Exam> upcomingExams;


    public UpcomingExamListCard(Context context) {
        super(context);
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


    }


    @Override
    protected List<ListObject> initChildren() {

        Calendar twoWeeks = Calendar.getInstance();
        twoWeeks.add(Calendar.DAY_OF_MONTH, 21);

        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -1);
        //Init the list
        List<ListObject> mObjects = new ArrayList<ListObject>();

        for(int i = 0; i < ListDB.getMasterList().size(); i++){
            Subject subject = ListDB.get(i);

            for (int j = 0; j < subject.getExamList().size(); j++) {

                Exam exam = subject.getTestElement(j);
                try {


                    if (exam.getDate().before(twoWeeks) && exam.getDate().after(now)) {
                        System.out.print(exam.getName());
                        mObjects.add(new TestObject(this, exam));
                    }
                }catch (NullPointerException ignored){};

                //TestObject testObject = new TestObject(this, subject.getTestElement(i));
                //testObject.setObjectId(subject.getTestElement(i).getName());
                //testObject.setSwipeable(true);
                //mObjects.add(testObject);
            }
        }



        return mObjects;
    }


    @Override
    public View setupChildView(int i, ListObject listObject, View view, ViewGroup viewGroup) {
        //Setup the ui elements inside the item
        TextView nameMarkView = (TextView) view.findViewById(R.id.card_test_list_upcoming_inner_name);
        TextView daysTextView = (TextView) view.findViewById(R.id.card_test_list_upcoming_inner_days_left);

        //Retrieve the values from the object
        final TestObject testObject = (TestObject) listObject;

        nameMarkView.setText(testObject.mName);
        daysTextView.setText(testObject.mDays);


        return view;
    }


    @Override
    public int getChildLayoutId() {
        return R.layout.card_test_list_upcoming_inner_main;
    }


    private class TestObject extends DefaultListObject {

        String mName;
        String mDays;

        public TestObject(Card parentCard, Exam exam) {
            super(parentCard);
            this.mName = exam.getName();
            this.mDays = Date.upcomingDays(getContext(), exam.getDate());
            init();
        }

        private void init() {
            /*
            //OnClick Listener
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(LinearListView parent, View view, int position, ListObject object) {
                    Toast.makeText(getContext(), "Click on " + getObjectId(), Toast.LENGTH_SHORT).show();
                }
            });
            */
        }
    }
}