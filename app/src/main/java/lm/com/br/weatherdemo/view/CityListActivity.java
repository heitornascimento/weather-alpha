package lm.com.br.weatherdemo.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import lm.com.br.weatherdemo.R;
import lm.com.br.weatherdemo.core.CityAdapter;
import lm.com.br.weatherdemo.core.RecyclerClickListener;
import lm.com.br.weatherdemo.model.City;
import lm.com.br.weatherdemo.presenter.CityPresenter;

public class CityListActivity extends BasicActivity
        implements RecyclerClickListener.OnItemClickListener,
        FloatingActionButton.OnClickListener, CityPresenter.Persistence {

    private RecyclerView mRecyclerView;
    private CityAdapter mAdapter;
    private FloatingActionButton mAddCity;
    private AlertDialog mDialog;
    private List<City> mData;
    private CityPresenter mPresenter;
    private EditText mUserInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        mPresenter = new CityPresenter(this, this);
        mData = mPresenter.loadAllCities();
        loadView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Load and register views.
     */
    private void loadView() {
        buildDialog();
        mAddCity = (FloatingActionButton) findViewById(R.id.add_city);
        mAddCity.setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.cities);
        mRecyclerView.addOnItemTouchListener(new RecyclerClickListener(this, this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CityAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mPresenter.deleteCity(mData.get(viewHolder.getAdapterPosition()));
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * Create add city dialog.
     */
    private void buildDialog() {

        mDialog = new AlertDialog.Builder(this).
                setPositiveButton(getResources().getString(R.string.add), null)
                .setNegativeButton(getResources().getString(R.string.cancel), null)
                .create();
        View view = getLayoutInflater().inflate(R.layout.add_city, null);
        mDialog.setView(view);
        mDialog.setTitle(getResources().getString(R.string.city));

        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                Button negButton = mDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negButton.setTextColor(ContextCompat.
                        getColor(getApplicationContext(), R.color.colorAccent));
                negButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText editText = (EditText) mDialog.findViewById(R.id.city_name);
                        editText.setText("");
                        editText.setError(null);
                        mDialog.dismiss();
                    }
                });

                Button addBtn = mDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                addBtn.setTextColor(ContextCompat.
                        getColor(getApplicationContext(), R.color.colorAccent));
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mUserInput = (EditText) mDialog.findViewById(R.id.city_name);
                        String cityName = mUserInput.getText().toString();
                        mPresenter.saveCity(cityName);
                    }
                });

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.closeDB();
    }

    @Override
    public void onItemClick(View v, int position) {
        //listener for recycler view.
        if (checkInternetConnection()) {
            String city = mData.get(position).getName();
            Intent intent = new Intent("DETAILS");
            intent.putExtra("city", city);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        mDialog.show();
    }

    @Override
    public void refreshData(List list) {
        if (mUserInput != null) {
            mUserInput.setText("");
        }
        mData = (List<City>) list;
        mAdapter.setDataSet(mData);
        mAdapter.notifyDataSetChanged();
        mDialog.dismiss();
    }

    @Override
    public void showInvalidInput() {
        if (mUserInput != null) {
            mUserInput.setError(getResources().getString(R.string.mandatory));
        }
    }


}
