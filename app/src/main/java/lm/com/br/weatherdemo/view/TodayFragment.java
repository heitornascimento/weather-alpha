package lm.com.br.weatherdemo.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lm.com.br.weatherdemo.R;
import lm.com.br.weatherdemo.model.Data;
import me.grantland.widget.AutofitHelper;

/**
 * Created by heitornascimento on 7/21/16.
 */
public class TodayFragment extends Fragment {

    private TextView mDescription;
    private TextView mTempC;
    private TextView mTempF;
    private TextView mFeelLike;
    private TextView mWind;
    private ImageView mIcon;
    private TextView mTxtFeelsLike;
    private TextView mTxtWind;

    private final static char DEGREE = '°';
    private final static String MPH = "MPH";

    public static final String ACTION = "DATA_LOAD";
    public static TodayFragment newInstance() {
        return new TodayFragment();
    }

    public TodayFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        registerBroadcast();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.today, container, false);

        mDescription = (TextView) view.findViewById(R.id.description);
        AutofitHelper.create(mDescription);
        mTempC = (TextView) view.findViewById(R.id.tempC);
        mTempF = (TextView) view.findViewById(R.id.tempF);
        mFeelLike = (TextView) view.findViewById(R.id.feeltempC);
        mWind = (TextView) view.findViewById(R.id.wind);
        mIcon = (ImageView) view.findViewById(R.id.icon);
        mTxtFeelsLike = (TextView)view.findViewById(R.id.feelTxt);
        mTxtWind = (TextView)view.findViewById(R.id.windTxt);

        return view;
    }

    /**
     * Register BC to receive data from Activity.
     */
    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(dataLoaded, filter);
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(dataLoaded);
    }


    /**
     * Load current forecast
     * @param data
     */
    private void loadData(Data data) {

        mTxtFeelsLike.setVisibility(View.VISIBLE);
        mTxtWind.setVisibility(View.VISIBLE);
        mDescription.setText(data.getCurrentCondition().
                get(0).getDescription().
                get(0).getContent());
        mTempC.setText(String.valueOf(data.getCurrentCondition().
                get(0).
                getTemp_C()) + DEGREE);
        mTempF.setText(String.valueOf(data.getCurrentCondition().
                get(0).getTemp_F()) + DEGREE);
        mFeelLike.setText(String.valueOf(data.getCurrentCondition().
                get(0).getFeelLikeC()) + DEGREE);
        mWind.setText(String.valueOf(data.getCurrentCondition().
                get(0).getWindMPH()) + MPH);
        Picasso.with(getContext()).load(data.getCurrentCondition().
                get(0).getIcon().get(0).getUrl())
                .resize(200, 200).into(mIcon);
    }

    private final BroadcastReceiver dataLoaded = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION)) {
                Bundle bundle = intent.getExtras();
                Data data = bundle.getParcelable("data");
                loadData(data);
            }
        }
    };

}
