package com.eason.schedule;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by eason on 17-6-21.
 */

@Table(name = "scheduleExtra")
public class ExtraData {
    @Column(name = "id",isId=true,autoGen=false)
    private int id;
    @Column(name = "extra")
    private String extra = "";
    @Column(name = "fromClass")
    private String fromClass;
    @Column(name = "weekDay")
    private String weekDay;
    @Column(name = "week")
    private String week;

    public int getId() {
        return id;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public String getFromClass() {
        return fromClass;
    }

    public String getWeek() {
        return week;
    }

    public void setFromClass(String fromClass) {
        this.fromClass = fromClass;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
