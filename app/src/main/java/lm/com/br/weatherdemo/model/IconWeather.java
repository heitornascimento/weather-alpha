package lm.com.br.weatherdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class IconWeather implements Parcelable {

    @SerializedName("value")
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }


    public static final Parcelable.Creator CREATOR = new Creator<IconWeather>() {

        @Override
        public IconWeather createFromParcel(Parcel source) {

            IconWeather icon = new IconWeather();
            icon.url = source.readString();
            return icon;
        }

        @Override
        public IconWeather[] newArray(int size) {
            return new IconWeather[size];
        }
    };
}

