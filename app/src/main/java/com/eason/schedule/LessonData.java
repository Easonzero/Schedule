package com.eason.schedule;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by eason on 17-6-21.
 */

@Table(name = "schedule")
public class LessonData {
    @Column(name = "id",isId=true,autoGen=true)
    private int id;
    @Column(name = "lessonName")
    private String lessonName;
    @Column(name = "classRoom")
    private String classRoom;
    @Column(name = "teacher")
    private String teacher;
    @Column(name = "weekDay")
    private String weekDay;
    @Column(name = "fromClass")
    private String fromClass;
    @Column(name = "toClass")
    private String toClass;
    @Column(name = "week")
    private String week;
    @Column(name = "weeknumDelay")
    private String weeknumDelay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getClassRoom(){
        return classRoom;
    }

    public void setClassRoom(String classRoom){
        this.classRoom = classRoom;
    }

    public String getTeacher(){
        return teacher;
    }

    public void setTeacher(String teacher){
        this.teacher = teacher;
    }

    public String getWeekDay(){
        return weekDay;
    }

    public void setWeekDay(String weekDay){
        this.weekDay = weekDay;
    }

    public String getFromClass(){
        return fromClass;
    }

    public void setFromClass(String fromClass){
        this.fromClass = fromClass;
    }

    public String getToClass(){
        return toClass;
    }

    public void setToClass(String toClass){
        this.toClass = toClass;
    }

    public String getWeek(){
        return week;
    }

    public void setWeek(String week){
        this.week = week;
    }

    public String getWeeknumDelay(){
        return weeknumDelay;
    }

    public void setWeeknumDelay(String weeknumDelay){
        this.weeknumDelay = weeknumDelay;
    }
}
