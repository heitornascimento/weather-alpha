package lm.com.br.weatherdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import lm.com.br.weatherdemo.R;
import lm.com.br.weatherdemo.utils.Endpoint;

/**
 * Created by heitornascimento on 7/20/16.
 */
public class BasicActivity extends AppCompatActivity {

    public String DEBUG = "weather";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(DEBUG, "base activity on create");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(DEBUG, "base activity on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(DEBUG, "base activity on pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(DEBUG, "base activity on destroy");
    }

    /**
     * Show message through snackbar
     *
     * @param message
     */
    protected void showMessageSnackBar(String message) {
        Snackbar snackbar = Snackbar.
                make(findViewById(R.id.cl),
                        message,
                        Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    protected boolean checkInternetConnection() {


        boolean result = Endpoint.hasValidaConnection(this);

        if (!result) {
            Snackbar snackbar = Snackbar.
                    make(findViewById(R.id.cl),
                            getResources().getString(R.string.no_internet), Snackbar.LENGTH_SHORT).setAction("Settings", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                }
            });
            snackbar.show();
        }
        return result;
    }
}
