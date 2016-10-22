package sai.developement.weatherapp.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by sai on 10/21/16.
 */

/** Based of the sample code from android developer docs
 * https://developer.android.com/training/volley/requestqueue.html
 */

public class WeatherRequestQueue {

    private static WeatherRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private WeatherRequestQueue(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized WeatherRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new WeatherRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
