package com.garciparedes.evaluame.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garciparedes.evaluame.R;

import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 24/2/15.
 */
public class ExamFragment extends BaseSubjectFragment{

    private static final String EXAM = "exam";
    private static final String EXAM_SAVED = "exam_saved";

    private TextView mValueTextView;
    private TextView mTypeTextView;
    private TextView mDateTextView;
    private TextView mMarkTextView;

    private Exam mExam;

    public static ExamFragment newInstance(Subject subject, Exam exam) {
        ExamFragment examFragment = new ExamFragment();
        Bundle args = new Bundle();
        args.putParcelable(SUBJECT, subject);
        args.putParcelable(EXAM, exam);
        examFragment.setArguments(args);
        return examFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exam, container, false);
        mValueTextView = (TextView) view.findViewById(R.id.fragment_exam_value_textView);
        mTypeTextView = (TextView) view.findViewById(R.id.fragment_exam_type_textView);
        mDateTextView = (TextView) view.findViewById(R.id.fragment_exam_date_textView);
        mMarkTextView = (TextView) view.findViewById(R.id.fragment_exam_mark_textView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if (savedInstanceState != null) {
            mExam = savedInstanceState.getParcelable(EXAM_SAVED);
        } else {
            mExam = getArguments().getParcelable(EXAM);
        }

        customizeActionBar(true, mSubject.getColor(), mExam.getName(), mSubject.getName());


        mValueTextView.setText(mExam.getPercentageString());
        mTypeTextView.setText(mExam.getTypeString(getActivity()));
        mDateTextView.setText(mExam.getDateString(getActivity()));
        mMarkTextView.setText(mExam.getMarkString());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXAM_SAVED, mExam);

    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, SubjectFragment.newInstance(mSubject))
                .commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete) {

            deleteMark();

            return true;
        }


        if (item.getItemId() == R.id.action_edit) {

            editMark();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void deleteMark(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title
        alertDialogBuilder.setTitle(getString(R.string.delete_mark));

        // set dialog message
        alertDialogBuilder
                .setMessage(getString(R.string.delete_mark_confirmation))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        ListDB.removeTest(getActivity(), mSubject, mExam);

                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, SubjectFragment.newInstance(mSubject))
                                .commit();

                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    /**
     *
     */
    public void editMark() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, EditTestFragment.newInstance(mSubject, mExam))
                .commit();

    }
}
