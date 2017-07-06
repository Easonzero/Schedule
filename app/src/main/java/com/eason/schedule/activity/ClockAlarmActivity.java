package com.eason.schedule.activity;

import android.app.Activity;
import android.app.Service;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import com.eason.schedule.R;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by eason on 7/3/17.
 */

public class ClockAlarmActivity extends Activity {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;
    MaterialDialog dialog;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String message = this.getIntent().getStringExtra("msg");
        flag = this.getIntent().getIntExtra("flag", 0);
        showDialogInBroadcastReceiver(message);
    }

    private void showDialogInBroadcastReceiver(String message) {
        if (flag == 1 || flag == 2) {
            mediaPlayer = MediaPlayer.create(this, R.raw.in_call_alarm);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        if (flag == 0 || flag == 2) {
            vibrator = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
            vibrator.vibrate(new long[]{100, 10, 100, 600}, 0);
        }

        dialog = new MaterialDialog(this);
        dialog.setTitle("来自一个课程表的提醒");
        dialog.setMessage(message);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (flag == 1 || flag == 2) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                if (flag == 0 || flag == 2) {
                    vibrator.cancel();
                }
                dialog.dismiss();
                finish();
            }
        });
        dialog.setPositiveButton("确认",new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (flag == 1 || flag == 2) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                if (flag == 0 || flag == 2) {
                    vibrator.cancel();
                }
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

}
