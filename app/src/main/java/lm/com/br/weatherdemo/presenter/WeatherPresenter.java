package lm.com.br.weatherdemo.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import lm.com.br.weatherdemo.R;
import lm.com.br.weatherdemo.endpoints.Constants;
import lm.com.br.weatherdemo.endpoints.EndpointResult;
import lm.com.br.weatherdemo.endpoints.WeatherEndpoint;
import lm.com.br.weatherdemo.model.Data;
import lm.com.br.weatherdemo.model.Weather;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class WeatherPresenter extends BasePresenter {

    private Presentable mView;
    private WeakReference<Context> mContextReference;
    private String mCity;

    public WeatherPresenter(@NonNull Presentable view, @NonNull Context ctx) {
        this.mView = view;
        mContextReference = new WeakReference<Context>(ctx);
    }

    public void execute(@NonNull String city) {

        if (isForeground()) {
            Intent endpoint = new Intent(mContextReference.get(), WeatherEndpoint.class);
            EndpointResult receiver = new EndpointResult(new Handler());
            receiver.setReceiver(this);
            mCity = city;
            endpoint.putExtra("city", city);
            endpoint.putExtra("receiver", receiver);
            mContextReference.get().startService(endpoint);
        }

    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        if (isForeground()) {
            if (resultCode == Constants.RESULT_OK && resultData != null) {
                Data data = resultData.getParcelable("data");

                if (data == null || data.getFutureWeather() == null
                        || data.getFutureWeather() == null || data.getFutureWeather().size() == 0) {
                    if(mView != null && mContextReference.get() != null){
                        mView.onFailure(mContextReference.
                                get().getResources().getString(R.string.error_city) + " - " + mCity);
                    }
                    return;
                }
                mView.onSuccess(data);
            }
        }
    }

}
