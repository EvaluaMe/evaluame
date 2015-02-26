package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.fragments.SubjectFragment;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

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


    private FragmentManager fragmentManager;

    public StarredSubjectListCard(Context context, FragmentManager fragmentManager) {
        super(context);
        this.fragmentManager = fragmentManager;
        init();
    }


    @Override
    protected CardHeader initCardHeader() {

        CardHeader cardHeader = new CardHeader(getContext());
        cardHeader.setTitle(getContext().getString(R.string.starred_subjects));

        return cardHeader;
    }


    @Override
    protected void initCard() {
        setEmptyViewViewStubLayoutId(R.layout.card_extras_empty_starred_subjects);


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
}
