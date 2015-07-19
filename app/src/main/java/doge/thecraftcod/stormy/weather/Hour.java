package doge.thecraftcod.stormy.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gerardo on 15/07/15.
 */
public class Hour implements Parcelable {
    private long mTime;
    private String mSummary;
    private double mTemperature;
    private String mIcon;
    private String mTimezone;

    public Hour() { }


    public String getmIcon() {
        return mIcon;
    }

    public int getIconId() {
        return Forecast.getIconId(mIcon);
    }

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public int getmTemperature() {
        return (int) Math.round(mTemperature);
    }

    public void setmTemperature(double mTemperature) {
        this.mTemperature = mTemperature;
    }

    public String getmSummary() {
        return mSummary;
    }

    public void setmSummary(String mSummary) {
        this.mSummary = mSummary;
    }

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }

    public String getmTimezone() {
        return mTimezone;
    }

    public void setmTimezone(String mTimezone) {
        this.mTimezone = mTimezone;
    }


    public String getHour() {
        SimpleDateFormat format = new SimpleDateFormat("h a");
        Date date = new Date(mTime * 1000);
        return format.format(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSummary);
        dest.writeLong(mTime);
        dest.writeDouble(mTemperature);
        dest.writeString(mTimezone);
        dest.writeString(mIcon);

    }

    private Hour(Parcel in) {
        mSummary = in.readString();
        mTime = in.readLong();
        mTemperature = in.readDouble();
        mTimezone = in.readString();
        mIcon = in.readString();
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel source) {
            return new Hour(source);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

}
