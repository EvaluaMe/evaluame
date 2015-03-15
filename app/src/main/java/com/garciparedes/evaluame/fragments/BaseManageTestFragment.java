package com.garciparedes.evaluame.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.datepicker.DatePickerDialogFragment;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerBuilder;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerDialogFragment;
import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.Util.Date;
import com.garciparedes.evaluame.Util.Number;
import com.garciparedes.evaluame.enums.ExamType;
import com.garciparedes.evaluame.interfaces.AddData;
import com.garciparedes.evaluame.items.Exam;

import java.util.GregorianCalendar;

/**
 *
 */
public abstract class BaseManageTestFragment extends BaseSubjectFragment
        implements NumberPickerDialogFragment.NumberPickerDialogHandler, AddData,
        DatePickerDialogFragment.DatePickerDialogHandler {

    private Button btnCreate;
    private EditText editTextName;
    private TextView textMark;
    private TextView textValue;
    private TextView textDate;

    private NumberPickerBuilder numberPickerMark;
    private NumberPickerBuilder numberPickerValue;

    private Spinner mSpinnerType;

    private DatePickerBuilder datePicker;

    protected Exam newExam;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newExam = initTest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_test, container, false);

        // Set the dialog text -- this is better done in the XML
        textMark = (TextView) view.findViewById(R.id.textView_set_mark);
        textValue = (TextView) view.findViewById(R.id.textView_set_value);
        textDate = (TextView) view.findViewById(R.id.textView_set_date);
        editTextName = (EditText) view.findViewById(R.id.edit_text_dialog);
        mSpinnerType = (Spinner) view.findViewById(R.id.spinner_set_type);
        btnCreate = (Button) view.findViewById(R.id.button_dialog);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        customizeActionBar(false, mSubject.getColor(), mSubject.getName(), null);

        editTextName.setHint(getString(R.string.set_name));
        editTextName.setText(setTextName());

        numberPickerMark = new NumberPickerBuilder();
        numberPickerMark
                .setFragmentManager(getFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment_Light)
                .setTargetFragment(BaseManageTestFragment.this)
                .setMaxNumber(10)
                .setMinNumber(0)
                .setPlusMinusVisibility(View.INVISIBLE)
                .setLabelText(getString(R.string.over_ten))
                .setReference(0);

        numberPickerValue = new NumberPickerBuilder();
        numberPickerValue
                .setFragmentManager(getFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment_Light)
                .setTargetFragment(BaseManageTestFragment.this)
                .setMaxNumber(100)
                .setMinNumber(0)
                .setPlusMinusVisibility(View.INVISIBLE)
                .setLabelText("%")
                .setReference(1);

        datePicker = new DatePickerBuilder();
        datePicker
                .setFragmentManager(getFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment)
                .setTargetFragment(BaseManageTestFragment.this)
                .setReference(0);

        textMark.setText(setTextMark());
        textMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickerMark.show();
            }
        });

        textValue.setText(setTextPercentage());
        textValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickerValue.show();
            }
        });

        textDate.setText(newExam.getDateString(getActivity()));

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
            }
        });

        mSpinnerType.setAdapter(new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item,
                        ExamType.values(getActivity()))
        );
        mSpinnerType.setSelection(newExam.getType().ordinal());

        btnCreate.setText(setTextButton());
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newExam.setName(editTextName.getText().toString());
                newExam.setType(ExamType.values()[mSpinnerType.getSelectedItemPosition()]);
                try {

                    setOnClickButton();
                    hideKeyboard();
                    replaceFragment();

                } catch (IllegalArgumentException e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    public abstract Exam initTest();

    public abstract void setOnClickButton();

    public abstract String setTextButton();

    public abstract String setTextName();

    public abstract String setTextMark();

    public abstract String setTextPercentage();

    @Override
    public void onDialogNumberSet(int reference, int number, double decimal, boolean isNegative, double fullNumber) {
        switch (reference) {
            case 0:
                textMark.setText(Number.toString((float) fullNumber));
                newExam.setMark((float) fullNumber);
                break;
            case 1:
                textValue.setText(Number.toString((float) fullNumber, "%"));
                newExam.setPercentage((float) (fullNumber));
                break;

            default:
                break;
        }
    }

    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        textDate.setText(
                dayOfMonth
                        + "/"
                        + Date.intToStringMonth(getActivity(), monthOfYear)
                        + "/"
                        + year
        );

        newExam.setDate(new GregorianCalendar(year, monthOfYear, dayOfMonth));
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
                .replace(R.id.container, ExamFragment.newInstance(mSubject, newExam))
                .commit();
    }

    @Override
    public void onBackPressed() {
        replaceFragment();
    }
}
