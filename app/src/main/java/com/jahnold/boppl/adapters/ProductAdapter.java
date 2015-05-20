package com.jahnold.boppl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.jahnold.boppl.R;
import com.jahnold.boppl.models.Product;
import com.jahnold.boppl.views.ProductLayout;

import java.util.ArrayList;

/**
 *  Product Adapter
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    // working copy of the products
    private ArrayList<Product> mProducts;

    // constructor
    public ProductAdapter(Context context, int textViewResourceId, ArrayList<Product> products) {

        super(context,textViewResourceId,products);
        this.mProducts = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // check whether the view needs inflating (it may be recycled)
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_product, null);
        }

        // get the product at the current position
        Product product = mProducts.get(position);

        if (product != null) {

            ((ProductLayout) convertView).setModel(product);
        }

        return convertView;
    }
}
