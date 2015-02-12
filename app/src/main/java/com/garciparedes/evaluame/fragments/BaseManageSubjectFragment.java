package com.garciparedes.evaluame.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.activities.MainActivity;
import com.garciparedes.evaluame.interfaces.AddData;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;
/**
 * Created by garciparedes on 10/2/15.
 */
public abstract class BaseManageSubjectFragment  extends Fragment implements AddData {

    private EditText editTextName;
    private EditText editTextDescription;
    private Button btnCreate;

    protected Subject newSubject;

    /**
     *
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState savedInstanceState
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_subject, container, false);

        newSubject = initSubject();

        editTextName = (EditText) view.findViewById(R.id.edit_text_dialog_name_subject);
        editTextDescription = (EditText) view.findViewById(R.id.edit_text_dialog_description_subject);
        btnCreate = (Button) view.findViewById(R.id.button_dialog_subject);

        return view;

    }


    /**
     *
     * @param state state
     */
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        editTextName.setHint(getString(R.string.set_name));
        editTextName.setText(setTextName());

        editTextDescription.setHint(getString(R.string.set_description));
        editTextDescription.setText(setTextDescription());

        btnCreate.setText(setTextButton());
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newSubject.setName(editTextName.getText().toString());
                newSubject.setDescription(editTextDescription.getText().toString());

                setOnClickButton();

                replaceFragment();

                hideKeyboard();

                ((MainActivity) getActivity()).update();

            }
        });
    }

    public abstract Subject initSubject();

    public abstract void setOnClickButton();

    public abstract String setTextButton();

    public abstract String setTextName();

    public abstract String setTextDescription();



    /**
     *
     */
    @Override
    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextDescription.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editTextName.getWindowToken(), 0);
    }


    /**
     *
     */
    @Override
    public void replaceFragment(){

        getFragmentManager().beginTransaction()
                .replace(R.id.container,
                        SubjectFragment.newInstance(ListDB.get(ListDB.size() - 1)))
                .commit();

    }
}