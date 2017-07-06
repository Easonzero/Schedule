package com.eason.schedule.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.eason.schedule.R;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by eason on 17-6-21.
 */

public class PickDateDialog extends MaterialDialog {
    public View view;
    private DatePicker datePicker;
    public PickDateDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        LayoutInflater factory = LayoutInflater.from(context);
        view = factory.inflate(R.layout.pick_date_dialog, null);

        datePicker = (DatePicker)view.findViewById(R.id.datePicker);

        this.setContentView(view);
    }

    public String getDate(){
        return datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth();
    }
}
