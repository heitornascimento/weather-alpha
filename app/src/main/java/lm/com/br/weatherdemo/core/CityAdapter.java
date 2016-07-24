package lm.com.br.weatherdemo.core;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lm.com.br.weatherdemo.R;
import lm.com.br.weatherdemo.model.City;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> mCities;
    private ViewHolder mViewHolder;


    public CityAdapter(List<City> cities) {
        mCities = cities;
    }

    public void setDataSet(List<City> cities) {
        mCities = cities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String city = mCities.get(position).getName();
        holder.txtName.setText(city);
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.city);
        }
    }
}
