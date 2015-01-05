package com.garciparedes.resultator;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by garciparedes on 5/1/15.
 */
public class DecimalNumberDialog extends Dialog {


    private Button btnCreate;

    public DecimalNumberDialog(Context context) {
        super(context);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_decimal_number);

        // Set the title
        setTitle(getContext().getString(R.string.new_subject));

        setCanceledOnTouchOutside(true);

        btnCreate = (Button) findViewById(R.id.button_set_number_dialog);

        btnCreate.setText(getContext().getString(R.string.create_subject));
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dismiss();
            }
        });
    }
}