package com.garciparedes.evaluame;

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
import com.garciparedes.evaluame.interfaces.AddData;

/**
 *
 */
public class FragmentAddTest extends Fragment
        implements NumberPickerDialogFragment.NumberPickerDialogHandler, AddData {

    private Button btnCreate;
    private EditText editTextName;
    private TextView textMark;
    private TextView textValue;
    private NumberPickerBuilder numberPickerMark;
    private NumberPickerBuilder numberPickerValue;

    private int subject;
    private String name;
    private float score;
    private float value;


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
        textMark = (TextView) view.findViewById(R.id.textView_set_mark);
        textValue = (TextView) view.findViewById(R.id.textView_set_value);
        editTextName = (EditText) view.findViewById(R.id.edit_text_dialog);
        btnCreate = (Button) view.findViewById(R.id.button_dialog);

        numberPickerMark = new NumberPickerBuilder();
        numberPickerValue = new NumberPickerBuilder();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        editTextName.setHint(getString(R.string.set_name));

        numberPickerMark
                .setFragmentManager(getFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment)
                .setTargetFragment(FragmentAddTest.this)
                .setMaxNumber(10)
                .setMinNumber(0)
                .setPlusMinusVisibility(View.INVISIBLE)
                .setReference(0);

        numberPickerValue
                .setFragmentManager(getFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment)
                .setTargetFragment(FragmentAddTest.this)
                .setMaxNumber(100)
                .setMinNumber(0)
                .setPlusMinusVisibility(View.INVISIBLE)
                .setLabelText("%")
                .setReference(1);

        textMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickerMark.show();
            }
        });

        textValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickerValue.show();
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

                    ListDB.addTest(getActivity(),subject, name, score, value);

                    hideKeyboard();

                    replaceFragment();
                }
            }
        });
    }

    @Override
    public void onDialogNumberSet(int reference, int number, double decimal, boolean isNegative, double fullNumber) {
        switch (reference){
            case 0:
                textMark.setText(fullNumber+"");
                score = (float) fullNumber;
                break;
            case 1:
                textValue.setText(fullNumber + "%");
                value = (float) (0.01 * fullNumber);
                break;

            default:
                break;
        }

    }

    /**
     *
     */
    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextName.getWindowToken(), 0);

    }

    /**
     *
     */
    @Override
    public void replaceFragment() {

        getFragmentManager().beginTransaction()
            .replace(R.id.container,
                SubjectFragment.newInstance(subject))
            .commit();

    }
}
