package com.jahnold.boppl.models;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jahnold.boppl.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *  Model Factory
 */
public class ModelFactory {

    private static final String sService = "https://services.boppl.me/api/v0.0.1";

    // listener interface for categories
    public interface CategoryLoadListener {
        public void onLoadComplete(ArrayList<Category> categories);
    }

    // listener interface for products
    public interface ProductLoadListener {
        public void onLoadComplete(ArrayList<Product> products);
    }

    /**
     *  Load all the categories from the RESt API
     */
    public static void getCategories(final CategoryLoadListener listener) {

        final String endpoint = "/api/venues/4/products/categories";

        // make the request to the RESt API
        JsonArrayRequest req = new JsonArrayRequest(
                sService + endpoint,
                new Response.Listener<JSONArray> () {

                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<Category> categories = new ArrayList<>();

                        try {

                            for (int i=0; i < response.length(); i++) {

                                // get the current category
                                JSONObject category = response.getJSONObject(i);

                                // create a category object and add the details
                                Category catObj = new Category();
                                catObj.setId(category.getInt("id"));
                                catObj.setDescription(category.getString("category_desc"));

                                // add to array list
                                categories.add(catObj);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        listener.onLoadComplete(categories);
                    }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Log.d("loading: ", "categories");
            }
        });

        // add the request to the queue
        App.getInstance().addToRequestQueue(req);

    }


    public static void getProducts(int categoryId, final ProductLoadListener listener) {

        final String endpoint = "/api/venues/4/products/categories/" + String.valueOf(categoryId) + "/products";

        // make the request to the RESt API
        JsonArrayRequest req = new JsonArrayRequest(
            sService + endpoint,
            new Response.Listener<JSONArray> () {

                @Override
                public void onResponse(JSONArray response) {

                    ArrayList<Product> products = new ArrayList<>();

                    try {

                        for (int i=0; i < response.length(); i++) {

                            // get the current category
                            JSONObject product = response.getJSONObject(i);

                            // create a category object and add the details
                            Product productObj = new Product();
                            productObj.setId(product.getInt("id"));
                            productObj.setImageURL(product.getString("image_thumb_url"));
                            productObj.setName(product.getString("product_name"));
                            productObj.setPrice(product.getString("price"));

                            // add to array list
                            products.add(productObj);
                        }


                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                    listener.onLoadComplete(products);
                }

            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                    Log.d("loading: ", "products");
                }
            }
        );

        // add the request to the queue
        App.getInstance().addToRequestQueue(req);
    }
}
