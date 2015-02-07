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
import com.garciparedes.evaluame.provider.ListDB;

/**
 * FramentAddSubject Class
 */
public class AddSubjectFragment extends Fragment implements AddData {

    private EditText editTextName;
    private EditText editTextDescription;
    private Button btnCreate;


    /**
     * NewInstance method
     *
     * @return AddSubjectFragment
     */
    public static AddSubjectFragment newInstance() {
        AddSubjectFragment f = new AddSubjectFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }


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
        View view = inflater.inflate(R.layout.fragment_add_subject, container, false);

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
        editTextDescription.setHint(getString(R.string.set_description));
        btnCreate.setText(getString(R.string.create_subject));

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDB.addSubject(getActivity(), editTextName.getText().toString(), editTextDescription.getText().toString());

                replaceFragment();

                hideKeyboard();

                ((MainActivity) getActivity()).update();

            }
        });
    }


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
                        SubjectFragment.newInstance(ListDB.getMasterList().size() - 1))
                .commit();
    }
}
