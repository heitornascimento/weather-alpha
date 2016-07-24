package lm.com.br.weatherdemo.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import lm.com.br.weatherdemo.R;
import lm.com.br.weatherdemo.core.FutureWeatherAdapter;
import lm.com.br.weatherdemo.model.Data;
import lm.com.br.weatherdemo.presenter.WeatherPresenter;

/**
 * Created by heitornascimento on 7/21/16.
 */
public class WeatherDetailsActivity extends BasicActivity implements
        SwipeRefreshLayout.OnRefreshListener, WeatherPresenter.Presentable {

    private final static char DEGREE = '°';
    private final static String MPH = "MPH";

    private SwipeRefreshLayout mSwipeRefresh;
    private Toolbar mToolbar;
    private WeatherPresenter mPresenter;
    private String mCity;
    private TextView mToolbarTile;
    private RecyclerView mRecyclerViewWeather;
    private FutureWeatherAdapter mAdapterWeather;

    private Data mData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        setupToolbar();
        loadView();
        restoreData(savedInstanceState);
    }


    private void restoreData(Bundle bundle){
        if(bundle != null){
            mData = bundle.getParcelable("data");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(getIntent());
        if(mPresenter != null){
            mPresenter.setForeground(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mPresenter != null){
            mPresenter.setForeground(false);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("data", mData);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            mData = savedInstanceState.getParcelable("data");
            if(mData != null){
                populateData(mData);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void onRefresh() {
        if (mCity != null && !mCity.equals("")) {
            loadWeather(mCity, true);
        } else{
            loadWeather(mData.getRequest().get(0).getQuery(), true);
        }
    }

    @Override
    public void onSuccess(Object resultObj) {

        mData = (Data) resultObj;
        if (mData == null) {
            showMessageSnackBar(getResources().getString(R.string.error_city));
            return;
        }
        populateData(mData);
        if (mSwipeRefresh != null && mSwipeRefresh.isRefreshing()) {
            mSwipeRefresh.setRefreshing(false);
        }
    }


    @Override
    public void onFailure(String msg) {
        showMessageSnackBar(msg);
        if (mSwipeRefresh != null && mSwipeRefresh.isRefreshing()) {
            mSwipeRefresh.setRefreshing(false);
        }
    }


    /*#####################*/
    /*       PRIVATE       */
    /*#####################*/

    /**
     * Load All View.
     */
    private void loadView() {
        mToolbarTile = (TextView) findViewById(R.id.toolbar_title);
        mRecyclerViewWeather = (RecyclerView) findViewById(R.id.future);
        mRecyclerViewWeather.setHasFixedSize(true);
        mRecyclerViewWeather.setLayoutManager(new LinearLayoutManager(this));
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefresh.setOnRefreshListener(this);
    }

    /**
     * Configure basic properties of toolbar
     */
    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow = ResourcesCompat.
                getDrawable(getResources(), R.drawable.arrow_back, null);
        upArrow.setColorFilter(ContextCompat.
                getColor(getApplicationContext(), R.color.textColor), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });

    }

    /**
     * Load City's Weather
     *
     * @param intent
     */
    private void loadData(Intent intent) {

        if (intent != null && mData == null) {
            mCity = intent.getStringExtra("city");
            if (mCity != null && !mCity.equals("")) {
                loadWeather(mCity, false);
            }
        }
    }

    /**
     * Call Service to pull data from the webservice.
     *
     * @param city
     */
    private void loadWeather(String city, boolean isToRefresh) {
        if(mData == null || isToRefresh){
            mPresenter = new WeatherPresenter(this, getApplicationContext());
            mPresenter.execute(city);
        }
    }

    /**
     * Populate the forecast data
     *
     * @param data
     */
    private void populateData(Data data) {
        populateTodayWeather(data);
        populateFutureWeather(data);
    }

    /**
     * Populate the fragment of today's weather
     *
     * @param data
     */
    private void populateTodayWeather(Data data) {

        mToolbarTile.setText(mCity);
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", data);

        Intent intent = new Intent(TodayFragment.ACTION);
        intent.putExtras(bundle);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /**
     * Populate the list of upcoming days.
     *
     * @param data
     */
    private void populateFutureWeather(Data data) {
        //data.getFutureWeather().remove(0);
        mAdapterWeather = new FutureWeatherAdapter(this, data.getFutureWeather());
        mRecyclerViewWeather.setAdapter(mAdapterWeather);
        mAdapterWeather.notifyDataSetChanged();
    }
}
