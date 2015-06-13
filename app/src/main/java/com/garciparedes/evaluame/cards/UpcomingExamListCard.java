package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.Util.Date;
import com.garciparedes.evaluame.fragments.TestFragment;
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


    private FragmentManager fragmentManager;


    public UpcomingExamListCard(Context context,  FragmentManager fragmentManager) {
        super(context);
        this.fragmentManager = fragmentManager;

        init();
    }


    @Override
    protected CardHeader initCardHeader() {

        CustomCardHeader cardHeader = new CustomCardHeader(getContext());
        cardHeader.setTitle(getContext().getString(R.string.upcoming_exams));

        return cardHeader;
    }


    @Override
    protected void initCard() {


    }


    @Override
    protected List<ListObject> initChildren() {

        Calendar twoWeeks = Calendar.getInstance();
        twoWeeks.add(Calendar.DAY_OF_MONTH, 21);

        GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
        //Init the list
        List<ListObject> mObjects = new ArrayList<ListObject>();

        for(int i = 0; i < ListDB.getMasterList().size(); i++){
            final Subject subject = ListDB.get(i);

            for (int j = 0; j < subject.getExamList().size(); j++) {

                final Exam exam = subject.getTestElement(j);

                try {

                    if (exam.getDate().before(twoWeeks) && exam.getDate().after(now)) {
                        mObjects.add(new TestObject(this, exam, subject));
                    }
                }catch (NullPointerException ignored){};

                //TestObject testObject = new TestObject(this, mSubject.getTestElement(i));
                //testObject.setObjectId(mSubject.getTestElement(i).getName());
                //testObject.setSwipeable(true);
                //mObjects.add(testObject);
            }
        }



        return mObjects;
    }

    public boolean isEmpty(){
        return initChildren().isEmpty();
    }


    @Override
    public View setupChildView(int i, ListObject listObject, View view, ViewGroup viewGroup) {
        //Setup the ui elements inside the item
        TextView nameMarkView = (TextView) view.findViewById(R.id.card_test_list_upcoming_inner_name);
        TextView daysTextView = (TextView) view.findViewById(R.id.card_test_list_upcoming_inner_days_left);
        TextView nameSubjectView = (TextView) view.findViewById(R.id.card_test_list_upcoming_inner_name_subject);

        //Retrieve the values from the object
        final TestObject testObject = (TestObject) listObject;

        nameMarkView.setText(testObject.mName);
        daysTextView.setText(testObject.mTime);

        nameSubjectView.setTextColor(testObject.color);
        nameSubjectView.setText(testObject.mSubjectName);



        return view;
    }


    @Override
    public int getChildLayoutId() {
        return R.layout.card_test_list_upcoming_inner_main;
    }


    private class TestObject extends DefaultListObject {

        String mName;
        String mTime;
        String mSubjectName;
        int color;

        public TestObject(Card parentCard, Exam exam, Subject subject) {
            super(parentCard);
            this.mName = exam.getName();
            this.mTime = Date.upcomingDays(getContext(), exam.getDate());
            this.mSubjectName = subject.getName();
            this.color = subject.getColor();
            init(exam, subject);
        }

        private void init(final Exam exam, final Subject subject) {

            //OnClick Listener
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(LinearListView parent, View view, int position, ListObject object) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, TestFragment.newInstance(subject, exam))
                            .commit();
                }
            });

        }
    }
}
