package com.jahnold.boppl.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jahnold.boppl.fragments.CategoryFragment;
import com.jahnold.boppl.models.Category;

import java.util.ArrayList;

/**
 *  Pager Adapter
 */
public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Category> mCategories = new ArrayList<>();

    public CategoryPagerAdapter(FragmentManager fragmentManager) {

        super(fragmentManager);
    }


    public void setCategories(ArrayList<Category> categories) {
        mCategories = categories;
    }

    @Override
    public Fragment getItem(int i) {

        // create a category fragment for the category id at position i
        return CategoryFragment.getInstance(mCategories.get(i).getId());

    }


    @Override
    public int getCount() {

        return mCategories.size();

    }


}
