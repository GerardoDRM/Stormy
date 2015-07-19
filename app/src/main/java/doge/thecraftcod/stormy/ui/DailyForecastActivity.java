package doge.thecraftcod.stormy.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.Arrays;

import doge.thecraftcod.stormy.R;
import doge.thecraftcod.stormy.adapters.DayAdapter;
import doge.thecraftcod.stormy.weather.Day;

public class DailyForecastActivity extends ListActivity {

    private Day[] mDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        Intent i = getIntent();
        Parcelable[] parcelables = i.getParcelableArrayExtra(MainActivity.DAYLY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);
        DayAdapter adapter = new DayAdapter(this, mDays);
        setListAdapter(adapter);
    }

}
