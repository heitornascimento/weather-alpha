package lm.com.br.weatherdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class WeatherDescription implements Parcelable {

    @SerializedName("value")
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
    }

    public static final Parcelable.Creator CREATOR = new Creator<WeatherDescription>() {

        @Override
        public WeatherDescription createFromParcel(Parcel source) {

            WeatherDescription weatherDescription = new WeatherDescription();
            weatherDescription.content = source.readString();
            return weatherDescription;
        }

        @Override
        public WeatherDescription[] newArray(int size) {
            return new WeatherDescription[size];
        }
    };
}
