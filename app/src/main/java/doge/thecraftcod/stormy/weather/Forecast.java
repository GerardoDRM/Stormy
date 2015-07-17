package doge.thecraftcod.stormy.weather;

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
}
