package com.garciparedes.evaluame.fragments.subject;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.utils.ColorUtil;
import com.garciparedes.evaluame.activities.MainActivity;
import com.garciparedes.evaluame.interfaces.AddData;
import com.garciparedes.evaluame.items.Subject;

/**
 * Created by garciparedes on 10/2/15.
 */
public abstract class BaseManageSubjectFragment extends BaseSubjectFragment implements AddData {

    private EditText editTextName;
    private EditText editTextDescription;
    private FloatingActionButton mFAButtonBar;


    protected Subject newSubject;

    /**
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState savedInstanceState
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_subject, container, false);


        editTextName = (EditText) view.findViewById(R.id.edit_text_dialog_name_subject);
        editTextDescription = (EditText) view.findViewById(R.id.edit_text_dialog_description_subject);
        mFAButtonBar = (FloatingActionButton) view.findViewById(R.id.fab_bar);


        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);


        return view;

    }

    /**
     * @param state state
     */
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        newSubject = initNewSubject();

        customizeActionBar( newSubject.getColor(), newSubject.getName(), newSubject.getName());

        editTextName.setText(newSubject.getName());

        editTextDescription.setText(newSubject.getDescription());


        if (mFAButtonBar != null) {
            mFAButtonBar.setRippleColor(ColorUtil.getComplimentColor(newSubject.getColor()));
            mFAButtonBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newSubject.setName(editTextName.getText().toString());
                    newSubject.setDescription(editTextDescription.getText().toString());

                    try {
                        setOnClickButton();


                        hideKeyboard();

                        ((MainActivity) getActivity()).update();
                        replaceFragment();
                    } catch (IllegalArgumentException e){
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT)
                                .show();
                    }

                }
            });
        }

    }

    /**
     * @return
     */
    public abstract Subject initNewSubject();

    /**
     *
     */
    public abstract void setOnClickButton();

    /**
     *
     */
    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextDescription.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editTextName.getWindowToken(), 0);
    }

    /**
     *
     */
    @Override
    public void replaceFragment() {

        getFragmentManager().beginTransaction()
                .replace(R.id.container, SubjectFragment.newInstance(newSubject))
                .commit();

    }
}