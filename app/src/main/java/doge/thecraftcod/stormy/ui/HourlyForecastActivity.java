package doge.thecraftcod.stormy.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import doge.thecraftcod.stormy.R;
import doge.thecraftcod.stormy.adapters.HourAdapter;
import doge.thecraftcod.stormy.weather.Hour;

public class HourlyForecastActivity extends Activity {

    private Hour[] mHours;
    @Bind(R.id.recyclerView) RecyclerView mReciclyeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        ButterKnife.bind(this);

        Intent i = getIntent();
        Parcelable[] parcelables = i.getParcelableArrayExtra(MainActivity.HOURLY_FORECAST);
        mHours = Arrays.copyOf(parcelables, parcelables.length, Hour[].class);

        HourAdapter adapter = new HourAdapter(mHours);
        mReciclyeView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mReciclyeView.setLayoutManager(layoutManager);

        mReciclyeView.setHasFixedSize(true);
    }
}
