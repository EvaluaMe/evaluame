package com.garciparedes.resultator;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by garciparedes on 10/12/14.
 */
public class FragmentAddSubject extends Fragment {

    private EditText editTextName;
    private EditText editTextDescription;
    private Button btnCreate;


    public static FragmentAddSubject newInstance(NavigationDrawerFragment i) {
        FragmentAddSubject f = new FragmentAddSubject();
        Bundle args = new Bundle();
        //args.putAll("subject", i);
        f.setArguments(args);
        i.updateListView();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_subject, container, false);

    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        editTextName = (EditText) getActivity().findViewById(R.id.edit_text_dialog_name_subject);
        editTextDescription = (EditText) getActivity().findViewById(R.id.edit_text_dialog_description_subject);

        editTextName.setHint(getString(R.string.set_name));
        editTextDescription.setHint(getString(R.string.set_description));

        btnCreate = (Button) getActivity().findViewById(R.id.button_dialog_subject);

        btnCreate.setText(getString(R.string.create_subject));
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDB.addSubject(editTextName.getText().toString(), editTextDescription.getText().toString());

                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new ChartFragment()
                                .newInstance(ListDB.getMasterList().size() - 1))
                        .commit();

                ListDB.saveData(getActivity());

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextDescription.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(editTextName.getWindowToken(), 0);

                MainActivity main = (MainActivity) getActivity();
                main.update();

            }
        });
    }
}
