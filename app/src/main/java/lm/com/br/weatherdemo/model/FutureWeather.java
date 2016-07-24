package lm.com.br.weatherdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class FutureWeather implements Parcelable {

    @SerializedName("date")
    String mDate;
    @SerializedName("maxtempC")
    String mMaxTempC;
    @SerializedName("mintempC")
    String mMinTempC;
    @SerializedName("maxtempF")
    String mMaxTempF;
    @SerializedName("mintempF")
    String mMinTempF;
    @SerializedName("hourly")
    List<Hourly> mHourly;

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmMaxTempC() {
        return mMaxTempC;
    }

    public void setMaxTempC(String mMaxTempC) {
        this.mMaxTempC = mMaxTempC;
    }

    public String getMinTempC() {
        return mMinTempC;
    }

    public void setMinTempC(String mMinTempC) {
        this.mMinTempC = mMinTempC;
    }

    public String getMaxTempF() {
        return mMaxTempF;
    }

    public void setMaxTempF(String mMaxTempF) {
        this.mMaxTempF = mMaxTempF;
    }

    public String getMinTempF() {
        return mMinTempF;
    }

    public void setMinTempF(String mMinTempF) {
        this.mMinTempF = mMinTempF;
    }

    public List<Hourly> getHourly() {
        return mHourly;
    }

    public void setWeather(List<Hourly> hourly) {
        this.mHourly = hourly;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mDate);
        dest.writeString(mMaxTempC);
        dest.writeString(mMinTempC);
        dest.writeString(mMaxTempF);
        dest.writeString(mMinTempF);
        dest.writeList(mHourly);
    }

    public static final Parcelable.Creator CREATOR = new Creator<FutureWeather>() {

        @Override
        public FutureWeather createFromParcel(Parcel source) {

            FutureWeather futureWeather = new FutureWeather();
            futureWeather.mDate = source.readString();
            futureWeather.mMaxTempC = source.readString();
            futureWeather.mMinTempC = source.readString();
            futureWeather.mMaxTempF = source.readString();
            futureWeather.mMinTempF = source.readString();
            source.readList(futureWeather.mHourly, Hourly.class.getClassLoader());

            return futureWeather;
        }

        @Override
        public FutureWeather[] newArray(int size) {
            return new FutureWeather[size];
        }
    };
}
