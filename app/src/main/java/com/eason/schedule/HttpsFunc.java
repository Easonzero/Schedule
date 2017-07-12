package com.eason.schedule;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.eason.schedule.data.LessonData;
import com.eason.schedule.data.ServerExtraData;
import com.eason.schedule.data.ServerLessonData;
import com.eason.schedule.data.UserData;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.IOException;
import java.util.List;

/**
 * Created by eason on 7/10/17.
 */

public class HttpsFunc {
    public static String host = "http://localhost:3000";

    private static final HttpsFunc INSTANCE = new HttpsFunc();

    private Handler handler = null;
    private Context context;
    public UserData userData;

    private void HttpsFunc(){}

    public static HttpsFunc getInstance(){
        return INSTANCE;
    }

    public void init(Context context){
        this.context = context;
    }

    public HttpsFunc connect(Handler handler){
        if(this.handler != handler)
            this.handler = handler;
        return INSTANCE;
    }

    public void disconnect(){
        this.handler = null;
    }

    public void login(String uid,String pwd) {
        try {
            DbManager db = getDB("user");
            UserData userData = db.findById(UserData.class,uid);
            if(userData==null){
                handler.sendEmptyMessage(1);
            }else if(userData.getPwd().equals(pwd)){
                this.userData = userData;
                handler.sendEmptyMessage(0);
            }else{
                handler.sendEmptyMessage(2);
            }
            db.close();
        } catch (DbException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void register(String uid,String pwd){
        try {
            DbManager db = getDB("user");
            UserData userData = db.findById(UserData.class,uid);
            if(userData!=null){
                handler.sendEmptyMessage(1);
            }else{
                userData = new UserData();
                userData.setUid(uid);
                userData.setPwd(pwd);
                db.save(userData);
                handler.sendEmptyMessage(0);
            }
            db.close();
        } catch (DbException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sync(String uid){
        try {
            DbManager db = getDB("serverLesson");
            if(uid.equals(userData.getUid())){
                DbManager dbExtra = getDB("serverExtra");
                List<ServerExtraData> extraDatas = dbExtra.selector(ServerExtraData.class).where("uid","=",uid).findAll();
                Message msg2 = new Message();
                msg2.obj = extraDatas;
                msg2.what = 2;
                handler.sendMessage(msg2);
                dbExtra.close();
            }
            List<ServerLessonData> lessonDatas = db.selector(ServerLessonData.class).where("uid","=",uid).findAll();
            Message msg = new Message();
            msg.obj = lessonDatas;
            msg.what = 0;
            handler.sendMessage(msg);
            db.close();
        } catch (DbException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upload(List<ServerLessonData> lessonDatas,List<ServerExtraData> extraDatas){
        try {
            DbManager db = getDB("serverLesson");
            ServerLessonData sld = new ServerLessonData();
            sld.setUid(userData.getUid());
            db.delete(sld);
            db.save(lessonDatas);
            db.close();
            DbManager dbExtra = getDB("serverExtra");
            ServerExtraData sed = new ServerExtraData();
            sed.setUid(userData.getUid());
            dbExtra.delete(sed);
            dbExtra.save(extraDatas);
            dbExtra.close();
            handler.sendEmptyMessage(1);
        } catch (DbException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DbManager getDB(String dbName){
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName(dbName)
                .setDbVersion(1);
        return x.getDb(daoConfig);
    }
}
