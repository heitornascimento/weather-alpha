package lm.com.br.weatherdemo.core;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import lm.com.br.weatherdemo.R;
import lm.com.br.weatherdemo.model.FutureWeather;
import lm.com.br.weatherdemo.utils.DateUtils;

/**
 * Created by heitornascimento on 7/22/16.
 */
public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder> {

    private ViewHolder mView;
    private List<FutureWeather> mWeathers;
    private WeakReference<Context> mWeakReference;
    private static final int SIX_OCLOCK = 3;


    public FutureWeatherAdapter(Context context, List<FutureWeather> weathers) {
        this.mWeathers = weathers;
        this.mWeakReference = new WeakReference<Context>(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_weather, parent, false);
        mView = new ViewHolder(view);
        return mView;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        FutureWeather futureWeather = mWeathers.get(position);
        String day = DateUtils.getDayOfWeek(futureWeather.getDate());
        holder.day.setText(day);
        Picasso.with(mWeakReference.get()).load(futureWeather.getHourly().get(SIX_OCLOCK).getIcon().get(0).getUrl())
                .resize(50, 50).into(holder.icon);
        holder.tempC.setText(String.valueOf(futureWeather.getHourly().get(SIX_OCLOCK).getTempC()));
        holder.tempF.setText(String.valueOf(futureWeather.getHourly().get(SIX_OCLOCK).getTempF()));
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView day;
        ImageView icon;
        TextView tempC;
        TextView tempF;

        public ViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            tempC = (TextView) itemView.findViewById(R.id.tempC);
            tempF = (TextView) itemView.findViewById(R.id.tempF);
        }
    }
}
