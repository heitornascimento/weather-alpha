package lm.com.br.weatherdemo.endpoints;

import java.util.List;

import lm.com.br.weatherdemo.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by heitornascimento on 7/20/16.
 */
public interface WeatherService {

    @GET("premium/v1/weather.ashx")
    Call<WeatherResponse> getWeather(@Query("key") String key, @Query("q") String city, @Query("num_of_days") String futureDays, @Query("format") String format);

}
