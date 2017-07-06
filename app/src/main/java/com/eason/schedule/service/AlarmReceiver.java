package com.eason.schedule.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.eason.schedule.Utils;
import com.eason.schedule.activity.ClockAlarmActivity;

/**
 * Created by eason on 7/3/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String msg = intent.getStringExtra("msg");
        long intervalMillis = intent.getLongExtra("intervalMillis", 0);
        if (intervalMillis != 0) {
            Utils.setAlarmTime(context, System.currentTimeMillis() + intervalMillis,
                    intent);
        }
        int flag = intent.getIntExtra("soundOrVibrator", 0);
        Intent clockIntent = new Intent(context, ClockAlarmActivity.class);
        clockIntent.putExtra("msg", msg);
        clockIntent.putExtra("flag", flag);
        clockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(clockIntent);
    }
}