package com.garciparedes.resultator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doomonafireball.betterpickers.numberpicker.NumberPickerBuilder;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerDialogFragment;

/**
 * Created by garciparedes on 10/12/14.
 */
public class FragmentAddTest extends Fragment
        implements NumberPickerDialogFragment.NumberPickerDialogHandler{


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
        View view = inflater.inflate(R.layout.fragment_add_test, container, false);

        subject = getArguments().getInt("subject", 0);

        // Set the dialog text -- this is better done in the XML
        textMark = (TextView) view.findViewById(R.id.text_view_dialog_mark);
        textValue = (TextView) view.findViewById(R.id.text_view_dialog_value);
        editTextName = (EditText) view.findViewById(R.id.edit_text_dialog);
        btnCreate = (Button) view.findViewById(R.id.button_dialog);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


        editTextName.setHint(getString(R.string.set_name));
        textMark.setText(getString(R.string.set_score));
        textValue.setText(getString(R.string.set_value));

        textMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getString(R.string.error_max_score), Toast.LENGTH_SHORT).show();

                //DecimalNumberDialog subjectDialog = new DecimalNumberDialog(getActivity());
                //subjectDialog.show();
                NumberPickerBuilder numberPickerBuilder = new NumberPickerBuilder()
                        .setFragmentManager(getFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setTargetFragment(FragmentAddTest.this)
                        .setReference(0);

                numberPickerBuilder.show();

            }
        });

        textValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getString(R.string.error_max_score), Toast.LENGTH_SHORT).show();

                //DecimalNumberDialog subjectDialog = new DecimalNumberDialog(getActivity());
                //subjectDialog.show();
                NumberPickerBuilder numberPickerBuilder = new NumberPickerBuilder()
                        .setFragmentManager(getFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setTargetFragment(FragmentAddTest.this)
                        .setReference(1);

                numberPickerBuilder.show();

            }
        });


        btnCreate.setText(getString(R.string.create_test));
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = editTextName.getText().toString();

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

    @Override
    public void onDialogNumberSet(int reference, int number, double decimal, boolean isNegative, double fullNumber) {
        switch (reference){
            case 0:
                textMark.setText("Puntuacion " + fullNumber);
                score = (float) fullNumber;
                break;
            case 1:
                textValue.setText("Valor " +number);
                value = number;
                break;

            default:
                break;
        }

    }
}
