package com.jahnold.boppl.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by matthewarnold on 20/05/15.
 */
public class CategoryFragment extends Fragment {

    private int mCategoryId;

    public static CategoryFragment getInstance(int categoryId) {

        CategoryFragment categoryFragment = new CategoryFragment();
        categoryFragment.setCategoryId(categoryId);

        return categoryFragment;

    }

    // empty constructor
    public CategoryFragment() {}

    // gets and sets
    public int getCategoryId() { return mCategoryId;}
    public void setCategoryId(int categoryId) { mCategoryId = categoryId; }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
