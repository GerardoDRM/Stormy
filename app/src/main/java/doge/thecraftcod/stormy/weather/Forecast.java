package doge.thecraftcod.stormy.weather;

import doge.thecraftcod.stormy.R;

/**
 * Created by gerardo on 15/07/15.
 */
public class Forecast {
    private CurrentWeather mCurrentWeather;
    private Hour[] mHuorlyForecast;

    public Hour[] getmHuorlyForecast() {
        return mHuorlyForecast;
    }

    public void setmHuorlyForecast(Hour[] mHuorlyForecast) {
        this.mHuorlyForecast = mHuorlyForecast;
    }

    public CurrentWeather getmCurrentWeather() {
        return mCurrentWeather;
    }

    public void setmCurrentWeather(CurrentWeather mCurrentWeather) {
        this.mCurrentWeather = mCurrentWeather;
    }

    public Day[] getmDailyForecast() {
        return mDailyForecast;
    }

    public void setmDailyForecast(Day[] mDailyForecast) {
        this.mDailyForecast = mDailyForecast;
    }

    private Day[] mDailyForecast;

    public static int getIconId(String mIcon) {
        int iconId = R.drawable.clear_day;

        if (mIcon.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        }
        else if (mIcon.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if (mIcon.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (mIcon.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (mIcon.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (mIcon.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (mIcon.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (mIcon.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (mIcon.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (mIcon.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }
        return iconId;
    }
}
