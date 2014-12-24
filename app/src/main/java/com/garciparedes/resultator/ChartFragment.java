package com.garciparedes.resultator;

import android.app.Fragment;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

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

    private ListView list;
    private TestListAdapter listAdapter;
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
        list = (ListView)getView().findViewById(R.id.test_listView);

        listAdapter = new TestListAdapter(this, datos);
        list.setAdapter(listAdapter);

        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = list.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                listAdapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = listAdapter
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Test selecteditem = listAdapter
                                        .getItem(selected.keyAt(i));
                                // Remove selected items following the ids
                                subject.removeTest(selecteditem);

                            }
                        }
                        update();
                        // Close CAB
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.folder, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
                listAdapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    public void update(){

        xVals = new ArrayList<String>();
        yVals = new PieDataSet(null, "Company 1");

        int[] rainbow = getResources().getIntArray(R.array.rainbow);
        yVals.setColors(rainbow);

        data = new PieData(xVals,yVals);

        mChart.setData(data);

        for (int j = 0 ; j< datos.size() ; j++){
            introduce(datos.get(j),j);
        }

        subject = ListDB.getMasterList().get(getArguments().getInt("subject", 0));
        datos = subject.getTestList();


        list.setAdapter(new TestListAdapter(this, datos));


        mChart.setData(data);
        mChart.animateXY(1500, 1500);


        ListDB.saveData(getActivity());

    }

}