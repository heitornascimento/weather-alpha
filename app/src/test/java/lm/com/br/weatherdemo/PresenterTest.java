package lm.com.br.weatherdemo;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import lm.com.br.weatherdemo.dao.DataRepository;
import lm.com.br.weatherdemo.model.City;
import lm.com.br.weatherdemo.presenter.CityPresenter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by heitornascimento on 7/23/16.
 */
public class PresenterTest {


    DataRepository db;
    CityPresenter presenter;
    City ciy;
    List<City> listOfCities;
    CityPresenter.Persistence view;


    @Before
    public void setup() {

        //Mock Database
        db = Mockito.mock(DataRepository.class);
        listOfCities = new ArrayList<>();
        listOfCities.add(new City("São Paulo"));
        listOfCities.add(new City("Recife"));
        listOfCities.add(new City("Dublin"));
        listOfCities.add(new City("Lima"));
        Mockito.when(db.loadAllCities()).thenReturn(listOfCities);

        //Mock View
        view = Mockito.mock(CityPresenter.Persistence.class);

        //Mock Presenter
        presenter = Mockito.mock(CityPresenter.class);
        Mockito.when(presenter.loadAllCities()).thenReturn(listOfCities);
        presenter.setView(view);

    }

    @Test
    public void shouldLoadFourCities(){
        List<City> result = presenter.loadAllCities();
        Assert.assertTrue(result.size() == 4);
    }

    @Test
    public void shouldSaveNewCity(){
        presenter.setView(view);
        presenter.saveCity("Praga");
        Mockito.verify(presenter, times(1)).refreshData();
    }
}
