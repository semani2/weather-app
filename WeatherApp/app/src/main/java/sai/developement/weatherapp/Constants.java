package sai.developement.weatherapp;

import sai.developement.weatherapp.data.WeatherContract;

/**
 * Created by sai on 10/21/16.
 */

public class Constants {

    public static final String[] FORECAST_COLUMNS = {
            WeatherContract.WeatherEntry.TABLE_NAME + "." + WeatherContract.WeatherEntry._ID,
            WeatherContract.WeatherEntry.COLUMN_DATE,
            WeatherContract.WeatherEntry.COLUMN_SHORT_DESC,
            WeatherContract.WeatherEntry.COLUMN_MAX_TEMP,
            WeatherContract.WeatherEntry.COLUMN_MIN_TEMP,
            WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING,
            WeatherContract.WeatherEntry.COLUMN_ICON
    };

    public static final int COL_WEATHER_ID = 0;
    public static final int COL_WEATHER_DATE = 1;
    public static final int COL_WEATHER_DESC = 2;
    public static final int COL_WEATHER_MAX_TEMP = 3;
    public static final int COL_WEATHER_MIN_TEMP = 4;
    public static final int COL_LOCATION_SETTING = 5;
    public static final int COL_WEATHER_ICON = 6;

    // Weather API Constants
    public static final String WEATHER_ICON_BASE_URI = "http://openweathermap.org/img/w/";
    public static final String FORECAST_BASE_URL =
            "http://api.openweathermap.org/data/2.5/forecast/daily?";
    public static final String QUERY_PARAM = "q";
    public static final String FORMAT_PARAM = "mode";
    public static final String UNITS_PARAM = "units";
    public static final String DAYS_PARAM = "cnt";
    public static final String APPID_PARAM = "APPID";

    //JSON Parsing constants
    public static final String OWM_CITY = "city";
    public static final String OWM_CITY_NAME = "name";

    public static final String OWM_LIST = "list";

    public static final String OWM_TEMPERATURE = "temp";
    public static final String OWM_MAX = "max";
    public static final String OWM_MIN = "min";

    public static final String OWM_WEATHER = "weather";
    public static final String OWM_DESCRIPTION = "main";
    public static final String OWM_WEATHER_ICON = "icon";
}
