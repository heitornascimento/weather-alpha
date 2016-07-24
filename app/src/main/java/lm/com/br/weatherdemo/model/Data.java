package lm.com.br.weatherdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class Data implements Parcelable {

    @SerializedName("current_condition")
    List<Weather> mCurrentCondition;

    @SerializedName("request")
    List<Request> mRequest;

    @SerializedName("weather")
    List<FutureWeather> mFutureWeather;


    public List<Weather> getCurrentCondition() {
        return mCurrentCondition;
    }

    public void setCurrentCondition(List<Weather> weathers) {
        this.mCurrentCondition = weathers;
    }

    public List<Request> getRequest() {
        return mRequest;
    }

    public void setRequest(List<Request> mRequest) {
        this.mRequest = mRequest;
    }

    public List<FutureWeather> getFutureWeather() {
        return mFutureWeather;
    }

    public void setFutureWeather(List<FutureWeather> mFutureWeather) {
        this.mFutureWeather = mFutureWeather;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mCurrentCondition);
        dest.writeList(mRequest);
        dest.writeList(mFutureWeather);
    }

    public static final Parcelable.Creator<Data> CREATOR = new Creator<Data>() {

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }

        @Override
        public Data createFromParcel(Parcel source) {

            Data data = new Data();
            source.readList(data.mCurrentCondition, Weather.class.getClassLoader());
            source.readList(data.mRequest, Request.class.getClassLoader());
            source.readList(data.mFutureWeather, FutureWeather.class.getClassLoader());

            return data;
        }
    };
}
