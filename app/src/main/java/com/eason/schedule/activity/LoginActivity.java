package com.eason.schedule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
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

@ContentView(R.layout.login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.editUserID)
    private EditText userID;
    @ViewInject(R.id.editPassWords)
    private EditText passWords;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,LookupActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    Toast.makeText(LoginActivity.this,"无效的用户名", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(LoginActivity.this,"错误的密码", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Event(R.id.register)
    private void onRegisterClick(View view){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    @Event(R.id.confirm)
    private void onConfirmClick(View view){
        HttpsFunc.getInstance().connect(handler).login(userID.getText().toString(), passWords.getText().toString());
    }
}
