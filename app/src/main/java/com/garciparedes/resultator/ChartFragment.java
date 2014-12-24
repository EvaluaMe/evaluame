package com.garciparedes.resultator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

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


        int subjectNum = getArguments().getInt("subject", 0);
        try {
            subject = ListDB.getMasterList().get(subjectNum);

        } catch (IndexOutOfBoundsException e){
            ListDB.getMasterList().add(new Subject("",""));
            subject = ListDB.getMasterList().get(subjectNum);

        }
        datos = subject.getTestList();
        createChart();

        createList(datos);

        for (int j = 0 ; j< datos.size() ; j++){
            introduce(datos.get(j),j);
        }
        final TestDialog testDialog = new TestDialog(getActivity(), getArguments().getInt("subject", 0),this);


        FloatingActionButton button = (FloatingActionButton) getActivity().findViewById(R.id.floating_button);
        button.setSize(FloatingActionButton.SIZE_NORMAL);
        button.setColorNormalResId(R.color.green);
        button.setColorPressedResId(R.color.spring_green);
        button.bringToFront();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                testDialog.show();

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


        eventList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        eventList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        //deleteSelectedItems();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.folder, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });
    }

    public void update(){

        if (datos.size() > 0) {
            subject = ListDB.getMasterList().get(getArguments().getInt("subject", 0));
            datos = subject.getTestList();


            createList(datos);

            introduce(datos.get(datos.size() - 1), datos.size() - 1);

            mChart.setData(data);
            mChart.animateXY(1500, 1500);

        }
    }
}