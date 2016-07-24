package lm.com.br.weatherdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class WeatherDescription {

    @SerializedName("value")
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
