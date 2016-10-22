package sai.developement.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String FORECAST_FRAGMENT_TAG = "Forecast_Fragment";

    private String mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment(), FORECAST_FRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String location = Utils.getPreferredLocation(this);

        if(location != null && !location.equals(mLocation)) {
            ForecastFragment forecastFragment = (ForecastFragment) getSupportFragmentManager().findFragmentByTag(FORECAST_FRAGMENT_TAG);
            if(null != forecastFragment) {
                forecastFragment.onLocationChanged();
            }
            mLocation = location;
        }
    }
}
