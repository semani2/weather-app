package sai.developement.weatherapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by sai on 10/21/16.
 */

public class ForecastAdapter extends CursorAdapter {

    public ForecastAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    private String formatHighLows(double high, double low) {
        String highLowStr = formatTemperature(high) + "/" + formatTemperature(low);
        return highLowStr;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_forecast, parent, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv = (TextView)view;
        tv.setText(convertCursorRowToUXFormat(cursor));
    }

    private String convertCursorRowToUXFormat(Cursor cursor) {
        String highAndLow = formatHighLows(
                cursor.getDouble(Constants.COL_WEATHER_MAX_TEMP),
                cursor.getDouble(Constants.COL_WEATHER_MIN_TEMP));

        return formatDate(cursor.getLong(Constants.COL_WEATHER_DATE)) +
                " - " + cursor.getString(Constants.COL_WEATHER_DESC) +
                " - " + highAndLow;
    }

    private static String formatTemperature(double temperature) {
        return String.format("%.0f", temperature);
    }

    private static String formatDate(long dateInMillis) {
        Date date = new Date(dateInMillis);
        return DateFormat.getDateInstance().format(date);
    }

}
