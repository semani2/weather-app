package sai.developement.weatherapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import sai.developement.weatherapp.Constants;
import sai.developement.weatherapp.R;

/**
 * Created by sai on 10/21/16.
 */

public class ForecastAdapter extends CursorAdapter {

    private static final int VIEW_COUNT = 2;
    private static final int VIEW_TODAY = 0;
    private static final int VIEW_FUTURE = 1;

    public ForecastAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int viewType = getItemViewType(cursor.getPosition());
        int layoutId = -1;
        switch (viewType) {
            case VIEW_TODAY: {
                layoutId = R.layout.today_list_view_item;
                break;
            }
            case VIEW_FUTURE: {
                layoutId = R.layout.future_list_view_item;
                break;
            }
        }

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String iconId = cursor.getString(Constants.COL_WEATHER_ICON).concat(".png");
        Uri iconUri = Uri.parse(Constants.WEATHER_ICON_BASE_URI.concat(iconId));
        Picasso.with(mContext).load(iconUri).into(viewHolder.iconImageView);

        long dateInMillis = cursor.getLong(Constants.COL_WEATHER_DATE);
        viewHolder.dateTextView.setText(getFriendlyDateString(dateInMillis));

        String description = cursor.getString(Constants.COL_WEATHER_DESC);
        viewHolder.descTextView.setText(description);

        double high = cursor.getDouble(Constants.COL_WEATHER_MAX_TEMP);
        viewHolder.highTempTextView.setText(formatTemperature(high));

        double low = cursor.getDouble(Constants.COL_WEATHER_MIN_TEMP);
        viewHolder.lowTempTextView.setText(formatTemperature(low));
    }

    private static String formatTemperature(double temperature) {
        return String.format("%.0f", temperature);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TODAY : VIEW_FUTURE;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_COUNT;
    }

    private String getFriendlyDateString(long dateInMillis) {
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
        return shortenedDateFormat.format(dateInMillis);
    }

    public static class ViewHolder {
        public final ImageView iconImageView;
        public final TextView dateTextView;
        public final TextView descTextView;
        public final TextView highTempTextView;
        public final TextView lowTempTextView;

        public ViewHolder(View view) {
            iconImageView = (ImageView) view.findViewById(R.id.list_item_icon);
            dateTextView = (TextView) view.findViewById(R.id.list_item_date_textview);
            descTextView = (TextView) view.findViewById(R.id.list_item_forecast_textview);
            highTempTextView = (TextView) view.findViewById(R.id.list_item_high_textview);
            lowTempTextView = (TextView) view.findViewById(R.id.list_item_low_textview);
        }
    }
}
