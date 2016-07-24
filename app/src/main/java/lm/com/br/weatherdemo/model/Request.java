package lm.com.br.weatherdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class Request implements Parcelable {

    @SerializedName("type")
    String type;
    @SerializedName("query")
    String query;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.query);
    }

    public static final Parcelable.Creator CREATOR = new Creator<Request>() {


        @Override
        public Request createFromParcel(Parcel source) {
            Request request = new Request();
            request.type = source.readString();
            request.query = source.readString();

            return  request;
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };
}
