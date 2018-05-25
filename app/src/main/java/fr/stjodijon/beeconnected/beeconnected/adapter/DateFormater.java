package fr.stjodijon.beeconnected.beeconnected.adapter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.security.Timestamp;
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
        long lValue = Float.valueOf(value).longValue();
        System.out.println(lValue);
        calendar.setTimeInMillis(lValue*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(sdf.format(calendar.getTime()));

        return  sdf.format(calendar.getTime());
    }


}
