package com.jahnold.boppl.models;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 *  Model Factory
 */
public class ModelFactory {

    private static JSONArray loadEndpoint(String endPoint) {

        JSONArray result = new JSONArray();

        return result;

    }

    public static ArrayList<Category> getCategories() {

        ArrayList<Category> categories = new ArrayList<>();

        return categories;
    }

    public static ArrayList<Product> getProducts(int categoryId) {

        ArrayList<Product> products = new ArrayList<>();

        return products;
    }
}
