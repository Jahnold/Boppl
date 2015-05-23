package com.jahnold.boppl.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.jahnold.boppl.R;
import com.jahnold.boppl.adapters.ProductAdapter;
import com.jahnold.boppl.models.ModelFactory;
import com.jahnold.boppl.models.Product;

import java.util.ArrayList;

/**
 *  Category Fragment
 */
public class CategoryFragment extends Fragment {

    private int mCategoryId;
    private ProductAdapter mAdapter;
    private ArrayList<Product> mProducts = new ArrayList<>();

    public static CategoryFragment getInstance(int categoryId) {

        CategoryFragment categoryFragment = new CategoryFragment();
        categoryFragment.setCategoryId(categoryId);

        return categoryFragment;

    }

    // empty constructor
    public CategoryFragment() {}

    // gets and sets
    public void setCategoryId(int categoryId) { mCategoryId = categoryId; }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate the layout
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // get a ref to the gridview
        GridView gridView = (GridView) view.findViewById(R.id.grid_view);

        // set up the adapter
        mAdapter = new ProductAdapter(
                getActivity(),
                0,
                mProducts
        );

        // introduce the grid view to the adapter
        gridView.setAdapter(mAdapter);

        // load the products for this category
        ModelFactory.getProducts(
                mCategoryId,
                new ModelFactory.ProductLoadListener() {
                    @Override
                    public void onLoadComplete(ArrayList<Product> products) {

                        mProducts.addAll(products);
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );

        return view;
    }
}
