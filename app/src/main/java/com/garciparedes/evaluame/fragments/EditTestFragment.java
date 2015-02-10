package com.garciparedes.evaluame.fragments;

import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Test;

/**
 * Created by garciparedes on 10/2/15.
 */
public class EditTestFragment extends BaseManageTestFragment {

    private static Test test;

    public static EditTestFragment newInstance(int i, Test test1) {
        EditTestFragment f = new EditTestFragment();
        Bundle args = new Bundle();
        args.putInt("subject", i);
        f.setArguments(args);
        test = test1;
        return f;
    }

    @Override
    public void setOnClickButton() {
        test.setName(name);
        test.setMark(mark);
        test.setPercentage(value);
    }

    @Override
    public String setTextButton() {
        return getResources().getString(R.string.edit_test);
    }

    @Override
    public String setTextName() {
        return test.getName();
    }

    @Override
    public String setTextMark() {
        return test.getMarkString();
    }

    @Override
    public String setTextPercentage() {
        return test.getPercentageString();
    }
}
