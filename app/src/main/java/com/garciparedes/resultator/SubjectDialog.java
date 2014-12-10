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

    private TextView textName;
    private TextView textDescription;
    private EditText editTextName;
    private EditText editTextDescription;
    private Button btnCreate;

    public SubjectDialog(Context context) {
        // Set your theme here
        super(context);

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
        setTitle("Nueva nota");

        setCanceledOnTouchOutside(true);


        // Set the dialog text -- this is better done in the XML
        textName = (TextView) findViewById(R.id.text_view_dialog_name_subject);
        textDescription = (TextView) findViewById(R.id.text_view_dialog_description_subject);

        editTextName = (EditText) findViewById(R.id.edit_text_dialog_name_subject);
        editTextDescription = (EditText) findViewById(R.id.edit_text_dialog_description_subject);


        textName.setText("Introduzca un nombre:");
        textDescription.setText("Introduzca la descripcion de la asignatura:");

        btnCreate = (Button) findViewById(R.id.button_dialog_subject);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDB.addSubject(editTextName.getText().toString(), editTextDescription.getText().toString());

                dismiss();

                SharedPreferences appSharedPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getContext());

                SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(ListDB.getMasterList());
                prefsEditor.putString("MasterList", json);
                prefsEditor.commit();


            }
        });
    }
}
