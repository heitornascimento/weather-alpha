package lm.com.br.weatherdemo.presenter;

import android.os.Bundle;

import java.util.List;

import lm.com.br.weatherdemo.endpoints.EndpointResult;
import lm.com.br.weatherdemo.model.City;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class BasePresenter implements EndpointResult.Receiver {

    private boolean isForeground = true;

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

    }

    /**
     * prevent to start any background task if the activity is not foreground.
     * @return
     */
    public boolean isForeground(){
        return  isForeground;
    }

    public void setForeground(boolean isForeground){
        this.isForeground = isForeground;
    }

    public static interface Presentable<T> {

        public void onSuccess(T resultObj);

        public void onFailure(String msg);
    }


}

