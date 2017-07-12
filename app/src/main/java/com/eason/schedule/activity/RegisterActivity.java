package com.eason.schedule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eason.schedule.HttpsFunc;
import com.eason.schedule.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by eason on 7/10/17.
 */

@ContentView(R.layout.register)
public class RegisterActivity extends BaseActivity{
    @ViewInject(R.id.editUserID)
    private EditText userID;
    @ViewInject(R.id.editPassWords)
    private EditText passWords;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            RegisterActivity.this.finish();
                        }
                    }, 500);
                    break;
                case 1:
                    Toast.makeText(RegisterActivity.this,"已经注册的用户名", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Event(R.id.close)
    private void onCloseClick(View view){
        RegisterActivity.this.finish();
    }

    @Event(R.id.confirm)
    private void onConfirmClick(View view){
        HttpsFunc.getInstance().connect(handler).register(userID.getText().toString(), passWords.getText().toString());
    }
}
