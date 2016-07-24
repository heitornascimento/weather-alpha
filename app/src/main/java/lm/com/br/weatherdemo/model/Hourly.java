package lm.com.br.weatherdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by heitornascimento on 7/22/16.
 */
public class Hourly implements Parcelable {

    @SerializedName("tempC")
    int tempC;
    @SerializedName("tempF")
    int tempF;
    @SerializedName("weatherIconUrl")
    List<IconWeather> mIcon;
    @SerializedName("weatherDesc")
    List<WeatherDescription> mDescription;


    public int getTempC() {
        return tempC;
    }

    public void setTempC(int tempC) {
        this.tempC = tempC;
    }

    public int getTempF() {
        return tempF;
    }

    public void setTempF(int tempF) {
        this.tempF = tempF;
    }

    public List<IconWeather> getIcon() {
        return mIcon;
    }

    public void setIcon(List<IconWeather> mIcon) {
        this.mIcon = mIcon;
    }

    public List<WeatherDescription> getDescription() {
        return mDescription;
    }

    public void setDescription(List<WeatherDescription> mDescription) {
        this.mDescription = mDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(tempC);
        dest.writeInt(tempF);
        dest.writeList(mIcon);
        dest.writeList(mDescription);
    }

    public static final Parcelable.Creator CREATOR = new Creator<Hourly>() {

        @Override
        public Hourly createFromParcel(Parcel source) {

            Hourly futureWeather = new Hourly();
            futureWeather.tempC = source.readInt();
            futureWeather.tempF = source.readInt();
            source.readList(futureWeather.mIcon, IconWeather.class.getClassLoader());
            source.readList(futureWeather.mDescription, WeatherDescription.class.getClassLoader());
            return futureWeather;
        }

        @Override
        public Hourly[] newArray(int size) {
            return new Hourly[size];
        }
    };
}
