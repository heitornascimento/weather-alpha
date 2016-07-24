package lm.com.br.weatherdemo.dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import lm.com.br.weatherdemo.model.City;

/**
 * Created by heitornascimento on 7/22/16.
 */
public class Dao implements DataRepository {

    private static Dao instance = new Dao();
    private Realm realm;
    public boolean isFirstTime = true;

    private Dao() {
        realm = Realm.getDefaultInstance();
    }

    public static Dao getInstance() {
        return instance;
    }

    /**
     * Save a city
     *
     * @param name
     */
    @Override
    public void saveCity(final String name) {
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                City result = realm.where(City.class).equalTo("name", name).findFirst();
                if(result == null || !result.getName().equalsIgnoreCase(name)){
                    City city = realm.createObject(City.class);
                    city.setId(UUID.randomUUID().toString());
                    city.setName(name);
                }
            }
        });
    }

    /**
     * Delete city record.
     *
     * @param city
     */
    @Override
    public void deleteCity(final City city) {

        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                city.deleteFromRealm();
            }
        });
    }

    /**
     * Load all cities
     *
     * @return
     */
    @Override
    public List<City> loadAllCities() {
        RealmResults<City> results = getRealm().where(City.class).findAll();
        return results;
    }


    /**
     * Close the connection with DB, if don't leak memory may occurs.
     */
    @Override
    public void close() {
        if (!realm.isClosed()) {
            realm.close();
        }
    }

    public Realm getRealm() {
        if (realm.isClosed()) {
            realm = Realm.getDefaultInstance();
        }
        return realm;
    }

    /**
     * Cleanup the database
     */
    @Override
    public void deleteAll() {
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }

}
