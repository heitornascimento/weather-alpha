package lm.com.br.weatherdemo.endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class Injector {

    public static Retrofit provideRetrofit(String baseurl){
        return  new Retrofit.Builder().baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static WeatherService provideWeatherService(){
        return provideRetrofit(Constants.baseUrl).create(WeatherService.class);
    }
}
