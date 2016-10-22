package sai.developement.weatherapp.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import sai.developement.weatherapp.asynctasks.FetchWeatherTask;
import sai.developement.weatherapp.adapters.ForecastAdapter;
import sai.developement.weatherapp.R;
import sai.developement.weatherapp.Utils;
import sai.developement.weatherapp.activities.SettingsActivity;
import sai.developement.weatherapp.data.WeatherContract;

import static sai.developement.weatherapp.Constants.FORECAST_COLUMNS;

/**
 * Created by sai on 10/21/16.
 */

public class ForecastFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String LOG_TAG = ForecastFragment.class.getSimpleName();

    private ForecastAdapter mForecastAdapter;

    private TextView mEmptyListTextView;

    private static final int FORECAST_LOADER = 0;

    public ForecastFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);

        mForecastAdapter = new ForecastAdapter(getActivity(), null, 0);

        mEmptyListTextView = (TextView) rootView.findViewById(R.id.empty_list_text_view);

        ListView forecastListView = (ListView) rootView.findViewById(R.id.listview_forecast);
        forecastListView.setEmptyView(mEmptyListTextView);
        forecastListView.setAdapter(mForecastAdapter);

        return rootView;
    }


    public void onLocationChanged() {
        updateWeather();
        getLoaderManager().restartLoader(FORECAST_LOADER, null, this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(FORECAST_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String locationSetting = Utils.getPreferredLocation(getActivity());

        String sortOrder = WeatherContract.WeatherEntry.COLUMN_DATE + " ASC";
        Uri weatherForLocationUri = WeatherContract.WeatherEntry.buildWeatherLocationWithStartDate(
                locationSetting, System.currentTimeMillis());

        return new CursorLoader(getActivity(),
                weatherForLocationUri,
                FORECAST_COLUMNS,
                null,
                null,
                sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null && data.getCount() < 1) {
            if(Utils.isNetworkAvailable(getContext())) {
                mEmptyListTextView.setText(getString(R.string.no_data_check_location));
                mEmptyListTextView.setVisibility(View.VISIBLE);
            }
            else {
                mEmptyListTextView.setText(getString(R.string.no_data_check_connection));
                mEmptyListTextView.setVisibility(View.VISIBLE);
            }
            mForecastAdapter.swapCursor(data);

        }
        else {
            mForecastAdapter.swapCursor(data);
            mEmptyListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mForecastAdapter.swapCursor(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
            return true;
        }

        if (id == R.id.action_map) {
            showLocationOnMap();
            return true;
        }

        if(id == R.id.action_refresh) {
            updateWeather();
            getLoaderManager().restartLoader(FORECAST_LOADER, null, this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLocationOnMap() {
        String location = Utils.getPreferredLocation(getActivity());

        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "No apps installed, capable of displaying location on map");
        }
    }

    private void updateWeather() {
        FetchWeatherTask weatherTask = new FetchWeatherTask(getActivity());
        weatherTask.execute();
    }
}
