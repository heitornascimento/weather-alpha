package lm.com.br.weatherdemo;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import lm.com.br.weatherdemo.model.City;

/**
 * Created by heitornascimento on 7/22/16.
 */
public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).
                name("weather.realm").build();
        //TODO for debug, comment for production
        //Realm.deleteRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
        saveDefaultCities();
    }

    /**
     * startup, create the four default cities.
     */
    private void saveDefaultCities() {

        List<String> defaultCities = new ArrayList<>();
        defaultCities.add("São Paulo");
        defaultCities.add("Recife");
        defaultCities.add("Dublin");
        defaultCities.add("Lima");

        Realm realm = Realm.getDefaultInstance();
        RealmResults<City> cities = realm.where(City.class).equalTo("name", defaultCities.get(0))
                .or().equalTo("name", defaultCities.get(1))
                .or().equalTo("name", defaultCities.get(2))
                .or().equalTo("name", defaultCities.get(3)).findAll();

        List<String> helperList = new ArrayList<>();
        for (City city : cities) {
            helperList.add(city.getName());
        }
        defaultCities.removeAll(helperList);

        City city = new City();

        for (final String singleCity : defaultCities) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    City city = realm.createObject(City.class);
                    city.setId(UUID.randomUUID().toString());
                    city.setName(singleCity);
                }
            });
        }

    }
}
