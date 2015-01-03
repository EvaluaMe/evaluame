package com.garciparedes.resultator;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by garciparedes on 10/12/14.
 */
public class SubjectDialog extends Dialog {

    private EditText editTextName;
    private EditText editTextDescription;
    private Button btnCreate;

    private NavigationDrawerFragment navigationDrawerFragment;

    public SubjectDialog(Context context, NavigationDrawerFragment navigationDrawerFragment) {
        // Set your theme here
        super(context);
        this.navigationDrawerFragment = navigationDrawerFragment;
        // This is the layout XML file that describes your Dialog layout
        //this.setContentView(R.layout.dialog_add_subject);

        /*

        */
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_add_subject);

        // Set the title
        setTitle(getContext().getString(R.string.new_subject));

        setCanceledOnTouchOutside(true);

        editTextName = (EditText) findViewById(R.id.edit_text_dialog_name_subject);
        editTextDescription = (EditText) findViewById(R.id.edit_text_dialog_description_subject);

        editTextName.setHint(getContext().getString(R.string.set_name));
        editTextDescription.setHint(getContext().getString(R.string.set_description));

        btnCreate = (Button) findViewById(R.id.button_dialog_subject);

        btnCreate.setText(getContext().getString(R.string.create_subject));
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDB.addSubject(editTextName.getText().toString(), editTextDescription.getText().toString());

                dismiss();

                ListDB.saveData(getContext());

                navigationDrawerFragment.updateListView();


            }
        });
    }
}
