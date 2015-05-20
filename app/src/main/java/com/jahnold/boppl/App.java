package com.jahnold.boppl;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 *  Application
 */
public class App extends Application {

    // global request queue for Volley
    private RequestQueue mRequestQueue;

    // tag for volley requests
    public static final String TAG = "VolleyRequest";

    // singleton App Context
    private static App sInstance;

    // getters
    public static synchronized App getInstance() { return sInstance; }
    public RequestQueue getRequestQueue() {

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // init singleton
        sInstance = this;
    }

    /**
     *  Add the request to the global queue
     */
    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    /**
     *  Cancel all pending requests
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
