package com.garciparedes.evaluame.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
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


import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerBuilder;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerDialogFragment;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;
import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.enums.ExamType;
import com.garciparedes.evaluame.interfaces.AddData;
import com.garciparedes.evaluame.items.Exam;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 */
public abstract class BaseManageTestFragment extends BaseSubjectFragment
        implements NumberPickerDialogFragment.NumberPickerDialogHandler, AddData,
        CalendarDatePickerDialog.OnDateSetListener,
        RadialTimePickerDialog.OnTimeSetListener{

    private Button btnCreate;
    private EditText editTextName;
    private TextView textMark;
    private TextView textValue;
    private TextView textDate;
    private TextView textTime;


    private NumberPickerBuilder numberPickerMark;
    private NumberPickerBuilder numberPickerValue;

    private Spinner mSpinnerType;

    private CalendarDatePickerDialog datePicker;
    private RadialTimePickerDialog timePicker;

    protected Exam newExam;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            newExam = savedInstanceState.getParcelable("save");
        } else {
            newExam = initTest();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("save", newExam);
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
        textTime = (TextView) view.findViewById(R.id.textView_set_time);
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
        editTextName.setText(newExam.getName());

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

        GregorianCalendar now = newExam.getDate();

        datePicker  = CalendarDatePickerDialog
                .newInstance(this, now.get(Calendar.YEAR), now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH));

        timePicker = RadialTimePickerDialog
                .newInstance(this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE),
                        DateFormat.is24HourFormat(getActivity()));

        textMark.setText(newExam.getMarkString());
        textMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickerMark.show();
            }
        });

        textValue.setText(newExam.getPercentageString());
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
                datePicker.show(getFragmentManager(), "hola");
            }
        });

        textTime.setText(newExam.getTimeString(getActivity()));
        textTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show(getFragmentManager(), "adios");
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

    @Override
    public void onDialogNumberSet(int reference, int number, double decimal, boolean isNegative, double fullNumber) {
        switch (reference) {
            case 0:
                newExam.setMark((float) fullNumber);
                textMark.setText(newExam.getMarkString());
                break;
            case 1:
                newExam.setPercentage((float) (fullNumber));
                textValue.setText(newExam.getPercentageString());
                break;

            default:
                break;
        }
    }

    @Override
    public void onDateSet( CalendarDatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        newExam.setDate(new GregorianCalendar(year, monthOfYear, dayOfMonth));
        textDate.setText(newExam.getDateString(getActivity()));
    }

    @Override
    public void onTimeSet(RadialTimePickerDialog dialog, int hourOfDay, int minute) {
        newExam.getDate().set(Calendar.HOUR_OF_DAY, hourOfDay);
        newExam.getDate().set(Calendar.MINUTE, minute);

        textTime.setText( newExam.getTimeString(getActivity()));
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
}
