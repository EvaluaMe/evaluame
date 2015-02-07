package com.garciparedes.resultator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by garciparedes on 6/2/15.
 */
public class TestListFragment extends Fragment {

    private TestListAdapter listAdapter;
    private ArrayList<Test> datos;

    private Subject subject;

    private ListView list;

    public static TestListFragment newInstance(int subjectNum) {
        TestListFragment f = new TestListFragment();
        Bundle args = new Bundle();
        args.putInt("subject", subjectNum);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        list = (ListView) inflater.inflate(R.layout.fragment_test_list, container, false);

        subject = ListDB.getMasterList().get(
                getArguments().getInt("subject", 0)
        );

        return list;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
                        System.out.println(selected.toString());

                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - list.getHeaderViewsCount()); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Test selecteditem = listAdapter.getItem(selected.keyAt(i)-1);
                                // Remove selected items following the ids
                                subject.removeTest(selecteditem);
                                ListDB.saveData(getActivity());

                            }
                        }
                        //update();
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

}

