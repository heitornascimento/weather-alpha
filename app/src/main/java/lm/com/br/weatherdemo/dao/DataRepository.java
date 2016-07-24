package lm.com.br.weatherdemo.dao;

import java.util.List;

import lm.com.br.weatherdemo.model.City;

/**
 * Created by heitornascimento on 7/23/16.
 */
public interface DataRepository {

    public void saveCity(final String name);
    public void deleteCity(final City city);
    public List<City> loadAllCities();
    public void deleteAll();
    public void close();

}
