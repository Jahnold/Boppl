package com.jahnold.boppl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.jahnold.boppl.App;
import com.jahnold.boppl.R;
import com.jahnold.boppl.models.Product;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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

            // get refs
            final NetworkImageView productImage = (NetworkImageView) convertView.findViewById(R.id.img_product);
            TextView txtPrice = (TextView) convertView.findViewById(R.id.txt_price);
            TextView txtName = (TextView) convertView.findViewById(R.id.txt_name);

            // set the name text
            txtName.setText(product.getName());

            // format and set the price text
            Locale uk = new Locale("en", "GB");
            NumberFormat nf = NumberFormat.getCurrencyInstance(uk);
            txtPrice.setText(nf.format(new BigDecimal(product.getPrice())));

            // set the image
            productImage.setImageUrl(product.getImageURL(), App.getInstance().getImageLoader());

        }

        return convertView;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }
}
