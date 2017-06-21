package com.eason.schedule;

import android.app.Application;
import org.xutils.x;

/**
 * Created by eason on 17-6-21.
 */

public class Schedule extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}