package com.garciparedes.resultator;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by garciparedes on 10/12/14.
 */
public class FragmentAddTest extends Fragment {


    private int subject;
    private Button btnCreate;

    private EditText editTextName;

    private TextView textMark;
    private TextView textValue;

    private String name;
    private float score;
    private int value;


    public static FragmentAddTest newInstance(int i) {
        FragmentAddTest f = new FragmentAddTest();
        Bundle args = new Bundle();
        args.putInt("subject", i);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_test, container, false);

    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        subject = getArguments().getInt("subject", 0);

        // Set the dialog text -- this is better done in the XML
        textMark = (TextView) getActivity().findViewById(R.id.text_view_dialog_mark);
        textValue = (TextView) getActivity().findViewById(R.id.text_view_dialog_value);
        editTextName = (EditText) getActivity().findViewById(R.id.edit_text_dialog);

        editTextName.setHint(getString(R.string.set_name));
        textMark.setText(getString(R.string.set_score));
        textValue.setText(getString(R.string.set_value));

        textMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getString(R.string.error_max_score), Toast.LENGTH_SHORT).show();


            }
        });



        btnCreate = (Button) getActivity().findViewById(R.id.button_dialog);
        btnCreate.setText(getString(R.string.create_test));
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = editTextName.getText().toString();
                //score = npMarkInteger.getValue()
                //        + (float) (npMarkTenths.getValue()*0.1)
                //        + (float) (npMarkHundredths.getValue()*0.01);
                //value = npPercentageInteger.getValue();

                if (score > 10) {
                    Toast.makeText(getActivity(), getString(R.string.error_max_score), Toast.LENGTH_SHORT).show();
                } else {

                    ListDB.addTest(subject, name, score, value);
                    ListDB.saveData(getActivity());

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextName.getWindowToken(), 0);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, ChartFragment.newInstance(subject)).commit();
                }
            }
        });




    }
}
