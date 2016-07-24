package lm.com.br.weatherdemo.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.List;

import lm.com.br.weatherdemo.R;
import lm.com.br.weatherdemo.dao.Dao;
import lm.com.br.weatherdemo.dao.DataRepository;
import lm.com.br.weatherdemo.model.City;

/**
 * Created by heitornascimento on 7/23/16.
 */
public class CityPresenter extends BasePresenter {

    private Persistence mView;
    private WeakReference<Context> mWeakReference;
    private DataRepository mDB;

    public CityPresenter(Persistence view, Context ctx) {
        this.mView = view;
        mWeakReference = new WeakReference<Context>(ctx);
        mDB = Dao.getInstance();
    }

    /**
     * Save a record City into DB
     */
    public void saveCity(String city) {
        if (city == null || city.equals("")) {
            mView.showInvalidInput();
        } else {
            mDB.saveCity(city);
            if (mView != null) {
                refreshData();
            }
        }
    }

    /**
     * Only for unit test
     * @param view
     */
    public void setView(CityPresenter.Persistence view){
        mView = view;
    }

    /**
     * Refresh adapter's data.
     */
    public void refreshData() {
        List<City> cityList = loadAllCities();
        mView.refreshData(cityList);
    }


    /**
     * Listt all cities.
     *
     * @return
     */
    public List<City> loadAllCities() {
        return mDB.loadAllCities();
    }

    /**
     * Should close connection to avoid leak.
     */
    public void closeDB() {
        mDB.close();
    }

    /**
     * On swipe, delete city.
     *
     * @param city
     */
    public void deleteCity(City city) {
        mDB.deleteCity(city);
        refreshData();
    }


    public static interface Persistence<T> {
        public void refreshData(List<City> cityList);
        public void showInvalidInput();
    }

}
