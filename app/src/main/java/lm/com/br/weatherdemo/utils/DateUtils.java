package lm.com.br.weatherdemo.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by heitornascimento on 7/22/16.
 */
public class DateUtils {


    /**
     * Get the day of week as textual
     * @param dateStr
     * @return
     */
    public static String getDayOfWeek(String dateStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
            DateFormat dateFormat = new SimpleDateFormat("EEEE");
            String dayOfWeek =  dateFormat.format(date);
            return  dayOfWeek;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
