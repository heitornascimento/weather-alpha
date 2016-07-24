package lm.com.br.weatherdemo.endpoints;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import lm.com.br.weatherdemo.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class WeatherEndpoint extends IntentService {

    public static final String debug = "weather";

    private ResultReceiver mReceiver;
    private WeatherResponse mWeatherResponse;

    public WeatherEndpoint() {
        super("WeatherEndpoint");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent == null || intent.getExtras() == null) {
            return;
        }

        String city = intent.getStringExtra("city");
        mReceiver = intent.getParcelableExtra("receiver");
        callWeather(city);
    }

    public void callWeather(final String city) {
        final WeatherService service = Injector.provideWeatherService();
        service.getWeather(Constants.MAP_KEY, city, Constants.DAY_TOTAL, Constants.FORMAT).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                sendToReceiverSuccess(response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                sendToReceiverError();
            }
        });
    }

    /**
     * @param response
     */
    public void sendToReceiverSuccess(WeatherResponse response) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("data", response.getData());
        if (mReceiver != null) {
            mReceiver.send(Constants.RESULT_OK, bundle);
        }

    }

    /**
     *
     */
    public void sendToReceiverError() {
        Bundle bundle = new Bundle();
        if (mReceiver != null) {
            mReceiver.send(Constants.RESULT_ERROR, bundle);
        }
    }

}
