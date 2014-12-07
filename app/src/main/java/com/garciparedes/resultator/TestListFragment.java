package com.garciparedes.resultator;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by garciparedes on 6/12/14.
 */
public class TestListFragment extends Fragment {

    private ListView eventList;

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        ArrayList<Test> datos = new ArrayList<Test>();
        datos.add(new Test("Tema1", 3,7.6f));

        eventList = (ListView)getView().findViewById(R.id.test_listView);

        eventList.setAdapter(new CustomArrayAdapter(this, datos));





    }
}
