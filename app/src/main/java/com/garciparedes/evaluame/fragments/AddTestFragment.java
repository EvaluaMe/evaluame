package com.garciparedes.evaluame.fragments;

import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 10/2/15.
 */
public class AddTestFragment extends NativeManageTestFragment {

    private final String defaultText = "- - . - -";

    public static AddTestFragment newInstance(int i) {
        AddTestFragment f = new AddTestFragment();
        Bundle args = new Bundle();
        args.putInt("subject", i);
        f.setArguments(args);
        return f;
    }


    @Override
    public void setOnClickButton() {
        ListDB.addTest(getActivity(), subject, name, mark, value);
        hideKeyboard();
        replaceFragment();
    }

    @Override
    public String setTextButton() {
        return getString(R.string.create_test);
    }

    @Override
    public String setTextName() {
        return null;
    }

    @Override
    public String setTextMark() {
        return defaultText;
    }

    @Override
    public String setTextValue() {
        return defaultText;
    }
}
