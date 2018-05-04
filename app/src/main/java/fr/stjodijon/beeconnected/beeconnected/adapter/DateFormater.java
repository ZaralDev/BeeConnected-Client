package fr.stjodijon.beeconnected.beeconnected.adapter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Zaral on 16/02/2018.
 */

public class DateFormater implements IAxisValueFormatter {


    public DateFormater() {

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(value);
        calendar.setTimeInMillis(Float.valueOf(value).longValue());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sdf.format(calendar.getTime());
    }


}
