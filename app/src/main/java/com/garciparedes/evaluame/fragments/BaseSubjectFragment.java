package com.garciparedes.evaluame.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.activities.MainActivity;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

import java.util.Collections;

/**
 * Created by garciparedes on 13/2/15.
 */
public abstract class BaseSubjectFragment extends Fragment {

    protected Subject subject;

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        subject = initSubject();
    }

    /**
     * @return
     */
    public Subject initSubject() {
        Subject subject;
        try {
            subject = getArguments().getParcelable("subject");
        } catch (NullPointerException e) {
            subject = null;
        }
        return subject;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete_subject) {

            deleteSubject();

            return true;
        }


        if (item.getItemId() == R.id.action_edit_subject) {

            editSubject();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     */
    public void deleteSubject() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title
        alertDialogBuilder.setTitle(getString(R.string.delete_subject));

        // set dialog message
        alertDialogBuilder
                .setMessage(getString(R.string.delete_subject_confirmation))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity

                        ListDB.removeSubject(getActivity(), subject);
                        //updateListView();
                        ((MainActivity) getActivity()).update();

                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, DefaultFragment.newInstance())
                                .addToBackStack(null)
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
    public void editSubject() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, EditSubjectFragment.newInstance(subject))
                .addToBackStack(null)
                .commit();

    }
}
