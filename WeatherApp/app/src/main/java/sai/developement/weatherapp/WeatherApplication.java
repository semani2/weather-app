package sai.developement.weatherapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by sai on 10/21/16.
 */

public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
