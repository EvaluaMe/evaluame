package com.garciparedes.resultator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by garciparedes on 10/12/14.
 */
public class FragmentAddTest extends Fragment {


    private int subject;
    private Button btnCreate;

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
        TextView textMark = (TextView) getActivity().findViewById(R.id.text_view_dialog_mark);
        TextView textValue = (TextView) getActivity().findViewById(R.id.text_view_dialog_value);
        final EditText editText = (EditText) getActivity().findViewById(R.id.edit_text_dialog);

        editText.setHint(getString(R.string.set_name));
        textMark.setText(getString(R.string.set_score));
        textValue.setText(getString(R.string.set_value));

        final NumberPicker npMarkInteger = (NumberPicker) getActivity().findViewById(R.id.number_picker_mark_dialog_integer);
        npMarkInteger.setMaxValue(10);
        npMarkInteger.setMinValue(0);

        final NumberPicker npMarkFloat = (NumberPicker) getActivity().findViewById(R.id.number_picker_mark_dialog_float);
        npMarkFloat.setMaxValue(99);
        npMarkFloat.setMinValue(0);
        npMarkFloat.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return "."+value;
            }
        });

        final NumberPicker npValue = (NumberPicker) getActivity().findViewById(R.id.number_picker_value_dialog);
        npValue.setMaxValue(100);
        npValue.setMinValue(0);

        btnCreate = (Button) getActivity().findViewById(R.id.button_dialog);
        btnCreate.setText(getString(R.string.create_test));
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDB.addTest(subject
                        ,editText.getText().toString()
                        ,npMarkInteger.getValue() + (npMarkFloat.getValue()/100)
                        ,npValue.getValue());

                ListDB.saveData(getActivity());

                getFragmentManager().beginTransaction()
                        .replace(R.id.container, ChartFragment.newInstance(subject)).commit();

            }
        });




    }
}
