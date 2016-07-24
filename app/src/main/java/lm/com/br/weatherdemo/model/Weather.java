package lm.com.br.weatherdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class Weather implements Parcelable {

    @SerializedName("temp_C")
    int temp_C;
    @SerializedName("temp_F")
    int temp_F;
    @SerializedName("weatherIconUrl")
    List<IconWeather> mIcon;
    @SerializedName("weatherDesc")
    List<WeatherDescription> mDescription;
    @SerializedName("windspeedMiles")
    private int mWindMPH;
    @SerializedName("FeelsLikeC")
    private int mFeelLikeC;

    public int getTemp_F() {
        return temp_F;
    }

    public void setTemp_F(int temp_F) {
        this.temp_F = temp_F;
    }

    public int getTemp_C() {
        return temp_C;
    }

    public void setTemp_C(int temp_C) {
        this.temp_C = temp_C;
    }

    @Override
    public String toString() {
        return super.toString();
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

    public int getWindMPH() {
        return mWindMPH;
    }

    public void setWindMPH(int mWindMPH) {
        this.mWindMPH = mWindMPH;
    }

    public int getFeelLikeC() {
        return mFeelLikeC;
    }

    public void setFeelLikeC(int mFeelLikeC) {
        this.mFeelLikeC = mFeelLikeC;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.temp_C);
        dest.writeInt(this.temp_F);
        dest.writeList(this.mIcon);
        dest.writeList(this.mDescription);
        dest.writeInt(this.mWindMPH);
        dest.writeInt(this.mFeelLikeC);
    }

    public static final Parcelable.Creator<Weather> CREATOR = new Creator<Weather>() {

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }

        @Override
        public Weather createFromParcel(Parcel source) {

            Weather weather = new Weather();
            weather.temp_C = source.readInt();
            weather.temp_F = source.readInt();
            source.readList(weather.mIcon, IconWeather.class.getClassLoader());
            source.readList(weather.mDescription, WeatherDescription.class.getClassLoader());
            source.readList(weather.mDescription, WeatherDescription.class.getClassLoader());
            weather.mWindMPH = source.readInt();
            weather.mFeelLikeC = source.readInt();
            return weather;
        }
    };
}
