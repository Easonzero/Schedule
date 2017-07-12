package com.eason.schedule.data;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by eason on 7/12/17.
 */

@Table(name = "user")
public class UserData {
    @Column(name = "uid", isId = true)
    private String uid;
    @Column(name = "pwd")
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public String getUid() {
        return uid;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}