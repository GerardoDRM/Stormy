package doge.thecraftcod.stormy.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import doge.thecraftcod.stormy.R;
import doge.thecraftcod.stormy.weather.CurrentWeather;
import doge.thecraftcod.stormy.weather.Day;
import doge.thecraftcod.stormy.weather.Forecast;
import doge.thecraftcod.stormy.weather.Hour;


public class MainActivity extends Activity {

    private Forecast forecast;
    public static final String DAYLY_FORECAST = "DAYLY_FORECAST";

    @Bind(R.id.timeL) TextView mTimeL;
    @Bind(R.id.temperatureL) TextView mTemperatureL;
    @Bind(R.id.precipVal) TextView mPrecipVal;
    @Bind(R.id.humidityVal) TextView mHumidityVal;
    @Bind(R.id.summaryL) TextView mSummaryL;
    @Bind(R.id.locationL) TextView mLocationL;
    @Bind(R.id.iconImg) ImageView mIcon;
    @Bind(R.id.refreshID) ImageView refresh;
    @Bind(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        progressBar.setVisibility(View.INVISIBLE);

        final double latitude = 19.217154;
        final double longitude = -98.235677;

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForeCast(latitude, longitude);

            }
        });

        getForeCast(latitude, longitude);
    }

    private void getForeCast(double lat, double lon) {
        String apiKey = "b78b5fcd01d5ed09d2e827da3f75f035";
        double latitude = lat;
        double longitude = lon;
        String forecastUrl = "https://api.forecast.io/forecast/" + apiKey + "/"
                + latitude + "," + longitude;
        if (isNetworkAvailable()) {
            toggleRefresh();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastUrl)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            forecast = parseForecastDetails(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    upadateDisplay();
                                }
                            });

                        } else {
                            alertUserAboutError();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        } else {
            Toast.makeText(this, "network unavailable", Toast.LENGTH_LONG).show();
        }
    }

    private void toggleRefresh() {
        if (progressBar.getVisibility() == View.INVISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
            refresh.setVisibility(View.INVISIBLE);
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
            refresh.setVisibility(View.VISIBLE);
        }

    }

    private void upadateDisplay() {
        CurrentWeather mCurretnWeather = forecast.getmCurrentWeather();
        mTemperatureL.setText(mCurretnWeather.getmTemperature() + "");
        mTimeL.setText("At " + mCurretnWeather.getFormattedTime() + " it will be");
        mHumidityVal.setText(mCurretnWeather.getmHumidity() + "");
        mPrecipVal.setText(mCurretnWeather.getmPrecipChance() + "");
        mSummaryL.setText(mCurretnWeather.getmSummary());
        mLocationL.setText(mCurretnWeather.getmTimeZone());
        Drawable drawable = getResources().getDrawable(mCurretnWeather.getIconId());
        mIcon.setImageDrawable(drawable);
    }

    private Forecast parseForecastDetails(String jsonData) throws JSONException {
        Forecast forecast = new Forecast();
        forecast.setmCurrentWeather(getCurrentDetails(jsonData));
        forecast.setmHuorlyForecast(getHourlForecast(jsonData));
        forecast.setmDailyForecast(getDailyForecast(jsonData));
        return forecast;
    }

    private Day[] getDailyForecast(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        JSONObject daily = forecast.getJSONObject("daily");
        JSONArray data = daily.getJSONArray("data");

        Day[] days = new Day[data.length()];
        for(int i = 0; i < data.length(); i++) {
            JSONObject jsonHour = data.getJSONObject(i);
            Day day = new Day();

            day.setmSummary(jsonHour.getString("summary"));
            day.setmTemperatureMax(jsonHour.getDouble("temperatureMax"));
            day.setmIcon(jsonHour.getString("icon"));
            day.setmTime(jsonHour.getLong("time"));
            day.setmTimezone(timezone);

            days[i] = day;

        }
        return days;
    }

    private Hour[] getHourlForecast(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");

        Hour[] hours = new Hour[data.length()];
        for(int i = 0; i < data.length(); i++) {
            JSONObject jsonHour = data.getJSONObject(i);
            Hour hour = new Hour();

            hour.setmSummary(jsonHour.getString("summary"));
            hour.setmTemperature(jsonHour.getDouble("temperature"));
            hour.setmIcon(jsonHour.getString("icon"));
            hour.setmTime(jsonHour.getLong("time"));
            hour.setmTimezone(timezone);

            hours[i] = hour;

        }
        return hours;
    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");

        JSONObject currently = forecast.getJSONObject("currently");

        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setmHumidity(currently.getDouble("humidity"));
        currentWeather.setmPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setmTime(currently.getLong("time"));
        currentWeather.setmIcon(currently.getString("icon"));
        currentWeather.setmSummary(currently.getString("summary"));
        currentWeather.setmTemperature(currently.getDouble("temperature"));
        currentWeather.setmTimeZone(timezone);

        return currentWeather;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    public boolean isNetworkAvailable() {
        boolean networkAvailable = false;

        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            networkAvailable = true;
        }
        return networkAvailable;
    }
    @OnClick (R.id.dailyBtn)
    public void startDailyActivity(View view) {
        Intent intent = new Intent(this, DailyForecastActivity.class);
        intent.putExtra(DAYLY_FORECAST, forecast.getmDailyForecast());
        startActivity(intent);
    }
}
