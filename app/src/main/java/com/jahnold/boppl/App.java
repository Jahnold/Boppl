package com.jahnold.boppl;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 *  Application
 */
public class App extends Application {

    // global request queue and image loader for Volley
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

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

    public ImageLoader getImageLoader() {

        if (mImageLoader == null) {

            mImageLoader = new ImageLoader(
                    getRequestQueue(),
                    new ImageLoader.ImageCache() {

                        private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);

                        @Override
                        public Bitmap getBitmap(String url) {
                            return cache.get(url);
                        }

                        @Override
                        public void putBitmap(String url, Bitmap bitmap) {
                            cache.put(url, bitmap);
                        }
                    }
            );
        }

        return mImageLoader;
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
