package com.example.cincin;


import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePick {

    private final Calendar calendar = Calendar.getInstance();
    private final String dateFormat = "dd/MM/yyyy";
    private TextView textView = null;

    public DatePick(final Context context, TextView textView) {
        this.textView = textView;

        // nustato listiner, jei TextView nera null
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDatePickerDialog(context).show();
                }
            });
        }
    }

 //grazina datos parinkimo listiner susieta su tekstiniu laukeliu
    private DatePickerDialog.OnDateSetListener getOnDateSetListener() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
                textView.setText(sdf.format(calendar.getTime()));
            }
        };
    }

    // grazina DatePickerDialog laukeliui
    private DatePickerDialog getDatePickerDialog(Context context) {
        return new DatePickerDialog(context, getOnDateSetListener(), calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

}