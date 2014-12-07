package com.garciparedes.resultator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.Highlight;

import java.util.ArrayList;


/**
 * Created by garciparedes on 5/12/14.
 */
public class ChartFragment  extends Fragment {

    private PieChart mChart;
    private PieDataSet yVals;
    private PieData data;
    private ArrayList<String> xVals;
    ArrayList<Test> datos;
    Subject subject;


    public static ChartFragment newInstance(int i) {
        ChartFragment f = new ChartFragment();
        Bundle args = new Bundle();
        args.putInt("subject", i);
        f.setArguments(args);

        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart, container, false);

    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


        subject = ListDB.getMasterList().get(getArguments().getInt("subject", 0));
        datos = subject.getTestList();
        createChart();

        createList(datos);

        for (int j = 0 ; j< datos.size() ; j++){
            introduce(datos.get(j),j);
        }

        FloatingActionButton button = (FloatingActionButton) getActivity().findViewById(R.id.floating_button);
        button.setSize(FloatingActionButton.SIZE_MINI);
        button.setColorNormalResId(R.color.green);
        button.setColorPressedResId(R.color.spring_green);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                creaTest();
            }
        });

    }


    private void createChart(){

        mChart = (PieChart) getView().findViewById(R.id.chart);

        mChart.setDescription("");


        //mChart.setUsePercentValues(true);
        //mChart.setValueTextColor(getResources().getColor(R.color.grey));

        mChart.setCenterText(subject.getName());
        mChart.setCenterTextSize(22f);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);


        mChart.animateXY(1500, 1500);


        xVals = new ArrayList<String>();
        yVals = new PieDataSet(null, "Company 1");

        int[] rainbow = getResources().getIntArray(R.array.rainbow);
        yVals.setColors(rainbow);



        data = new PieData(xVals,yVals);

        mChart.setData(data);

    }



    private void introduce(Test test, int i){

        xVals.add(test.getName());
        data.addEntry(new Entry(test.getMark(), i), 0);
        data.addEntry(new Entry(10-test.getMark(),i),1);

    }

    private void createList(ArrayList<Test> datos){
        ListView eventList;
        eventList = (ListView)getView().findViewById(R.id.test_listView);

        eventList.setAdapter(new CustomArrayAdapter(this, datos));
    }


    private void update(){

        subject = ListDB.getMasterList().get(getArguments().getInt("subject", 0));
        datos = subject.getTestList();


        createList(datos);

        introduce(datos.get(datos.size()-1),datos.size()-1);

        mChart.setData(data);
        mChart.animateXY(1500, 1500);



    }

    private void creaTest(){
        // Created a new Dialog
        final Dialog dialog = new Dialog(getActivity());

        // Set the title
        dialog.setTitle("Nueva nota");

        // inflate the layout
        dialog.setContentView(R.layout.dialog_add_test);

        dialog.setCanceledOnTouchOutside(true);

        // Set the dialog text -- this is better done in the XML
        TextView textName = (TextView)dialog.findViewById(R.id.text_view_dialog_name);
        TextView textMark = (TextView)dialog.findViewById(R.id.text_view_dialog_mark);
        TextView textValue = (TextView)dialog.findViewById(R.id.text_view_dialog_value);

        final EditText editText = (EditText) dialog.findViewById(R.id.edit_text_dialog);

        textName.setText("Introduzca un nombre:");
        textMark.setText("Introduzca la nota:");
        textValue.setText("Introduzca la proporcion:");


        final NumberPicker npMark = (NumberPicker) dialog.findViewById(R.id.number_picker_mark_dialog);
        npMark.setMaxValue(10);
        npMark.setMinValue(0);

        final NumberPicker npValue = (NumberPicker) dialog.findViewById(R.id.number_picker_value_dialog);
        npValue.setMaxValue(10);
        npValue.setMinValue(0);



        Button btnCreate = (Button) dialog.findViewById(R.id.button_dialog);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDB.addTest(getArguments().getInt("subject", 0), editText.getText().toString(), npMark.getValue(), npValue.getValue());
                dialog.dismiss();

                update();
            }
        });

        // Display the dialog
        dialog.show();
    }
}