package com.eason.schedule;

import android.content.Context;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by eason on 17-6-21.
 */

public class ScheduleFunc{
    private static final ScheduleFunc INSTANCE = new ScheduleFunc();
    private static final String[] weekdays = new String[]{"周一","周二","周三","周四","周五","周六","周日"};
    private static final String[] weeksNum = new String[]{"第1周","第2周","第3周","第4周","第5周","第6周","第7周","第8周","第9周","第10周","第11周","第12周",
            "第13周","第14周","第15周","第16周","第17周","第18周","第19周","第20周","第21周","第22周","第23周","第24周","第25周",};
    private static final String[] classToTime = new String[]{"8:00","9:00","10:00","11:00","13:45","14:45","15:45","16:45","6:30","7:30","8:30","9:30"};
    public int initWeek;
    public int initDate;//表示一年中第initdate周为校历第initweek周
    public int _today;//表示今天星期几
    public int _weekOfToday;//表示今天的周数
    public int currentWeek;//当前访问周数
    public LessonData lesson;
    public ExtraData extra;
    private boolean hasExtra = false;
    private DbManager db;
    private DbManager dbExtra;

    private ScheduleFunc(){}

    public void init(Context context){
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("schedule")
                .setDbVersion(1);
        DbManager.DaoConfig daoExtraConfig = new DbManager.DaoConfig()
                .setDbName("scheduleExtra")
                .setDbVersion(1);
        db = x.getDb(daoConfig);
        dbExtra = x.getDb(daoExtraConfig);
        int[] data = getScheduleData();
        initWeek = data[0];
        if(data[1] == 0){
            initDate = getWeekNumber();
        }else{
            initDate = data[1];
        }
        _weekOfToday = initWeek + (getWeekNumber() - initDate);
        _today = getWeekOfDate();
        currentWeek = _weekOfToday;
        lesson = new LessonData();
        extra = new ExtraData();
    }

    public String getWeekStr(int weekday){
        return weekdays[weekday-1];
    }

    public String getTimeStr(int classNum){return classToTime[classNum-1];}

    public String getWeekNumStr(int weeknum){
        return weeksNum[weeknum-1];
    }

    public static ScheduleFunc getInstance(){
        return INSTANCE;
    }

    public static int[] getScheduleData(){
        Date date=new Date();
        int[] data = new int[2];
        data[0] = 1;
        if(date.getMonth()>7)
            data[1] = 36;
        else if(date.getMonth()<7)
            data[1] = 10;
        else data[1] = 0;
        return data;
    }

    public List<LessonData> find(String week) {
        List<LessonData> dataList = null;
        try {
            dataList = db.selector(LessonData.class).where("week", "=", week).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public List<LessonData> find() {
        List<LessonData> dataList = null;
        try {
            dataList = db.selector(LessonData.class).where("week", "=", _weekOfToday).and("weekDay","=",_today).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public LessonData find(String week,String weekDay,String fromClass) {
        LessonData dataList = null;
        try {
            dataList = db.selector(LessonData.class).where("week", "=", week).and("weekDay","=",weekDay).and("fromClass","=",fromClass).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public void getExtra(String week,String weekDay,String fromClass){
        try {
            ExtraData tmp = dbExtra.selector(ExtraData.class).where("week", "=", week).and("weekDay","=",weekDay).and("fromClass","=",fromClass).findFirst();
            if(tmp!=null) {
                extra = tmp;
                hasExtra = true;
            }
            else{
                hasExtra = false;
                extra.setExtra("");
                extra.setWeek(week);
                extra.setWeekDay(weekDay);
                extra.setFromClass(fromClass);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void add() {
        try {
            db.save(lesson);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            db.delete(lesson);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            db.saveOrUpdate(lesson);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void updateExtra(){
        try {
            if(hasExtra)
                dbExtra.saveOrUpdate(extra);
            else
                dbExtra.save(extra);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private int getWeekOfDate() {
        Calendar calendar = Calendar.getInstance();
        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if(isFirstSunday){
            weekDay = weekDay - 1;
            if(weekDay == 0){
                weekDay = 7;
            }
        }

        return weekDay;
    }

    private int getWeekNumber(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int c = calendar.get(Calendar.WEEK_OF_YEAR);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if(weekDay == 0){
            c = c - 1;
        }
        return c;
    }
}