package com.eason.schedule.data;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by eason on 7/12/17.
 */

@Table(name = "serverExtra")
public class ServerExtraData {
    @Column(name = "id",isId=true,autoGen=true)
    private int id;
    @Column(name = "uid")
    private String uid;
    @Column(name = "extra")
    private String extra = "";
    @Column(name = "fromClass")
    private String fromClass;
    @Column(name = "weekDay")
    private String weekDay;
    @Column(name = "week")
    private String week;

    public ServerExtraData(){}

    public ServerExtraData(String uid,ExtraData extraData){
        this.uid = uid;
        this.weekDay = extraData.getWeekDay();
        this.week = extraData.getWeek();
        this.fromClass = extraData.getFromClass();
        this.extra = extraData.getExtra();
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setFromClass(String fromClass) {
        this.fromClass = fromClass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getExtra() {
        return extra;
    }

    public String getFromClass() {
        return fromClass;
    }

    public int getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getWeek() {
        return week;
    }

    public String getWeekDay() {
        return weekDay;
    }
}
