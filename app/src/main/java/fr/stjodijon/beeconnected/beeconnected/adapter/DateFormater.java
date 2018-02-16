package fr.stjodijon.beeconnected.beeconnected.adapter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Calendar;

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

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        return mDay +"/" + mMonth + " " + hour + ":"+ min ;
    }


}
