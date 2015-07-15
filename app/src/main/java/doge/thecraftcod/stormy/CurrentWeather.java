package doge.thecraftcod.stormy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by gerardo on 14/07/15.
 */
public class CurrentWeather {
    private String mIcon;
    private long mTime;
    private double mTemperature;
    private double mHumidity;
    private double mPrecipChance;
    private String mTimeZone;

    public String getmTimeZone() {
        return mTimeZone;
    }

    public void setmTimeZone(String mTimeZone) {
        this.mTimeZone = mTimeZone;
    }

    public String getmSummary() {
        return mSummary;

    }

    public void setmSummary(String mSummary) {
        this.mSummary = mSummary;
    }

    public int getmPrecipChance() {
        double precipPercentage = mPrecipChance * 100;
        return (int) Math.round(precipPercentage);
    }

    public void setmPrecipChance(double mPrecipChance) {
        this.mPrecipChance = mPrecipChance;
    }

    public double getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(double mHumidity) {
        this.mHumidity = mHumidity;
    }

    public int getmTemperature() {
        return (int)Math.round(mTemperature);
    }

    public void setmTemperature(double mTemperature) {
        this.mTemperature = mTemperature;
    }

    public int getIconId() {
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

    public long getmTime() {
        return mTime;
    }

    public String getFormattedTime() {
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        format.setTimeZone(TimeZone.getTimeZone(getmTimeZone()));
        Date dateTime = new Date(getmTime() * 1000);

        String timeString = format.format(dateTime);

        return timeString;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }

    public String getmIcon() {
        return mIcon;
    }

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    private String mSummary;

}
