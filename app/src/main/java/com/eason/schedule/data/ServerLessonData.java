package com.eason.schedule.data;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by eason on 7/12/17.
 */

@Table(name = "serverLesson")
public class ServerLessonData {
    @Column(name = "id",isId=true,autoGen=true)
    private int id;
    @Column(name = "uid")
    private String uid;
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

    public ServerLessonData(){}

    public ServerLessonData(String uid,LessonData lessonData){
        this.uid = uid;
        this.lessonName = lessonData.getLessonName();
        this.classRoom = lessonData.getClassRoom();
        this.teacher = lessonData.getTeacher();
        this.weekDay = lessonData.getWeekDay();
        this.week = lessonData.getWeek();
        this.fromClass = lessonData.getFromClass();
        this.toClass = lessonData.getToClass();
        this.weeknumDelay = lessonData.getWeeknumDelay();
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public void setFromClass(String fromClass) {
        this.fromClass = fromClass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setToClass(String toClass) {
        this.toClass = toClass;
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

    public void setWeeknumDelay(String weeknumDelay) {
        this.weeknumDelay = weeknumDelay;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public String getFromClass() {
        return fromClass;
    }

    public int getId() {
        return id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getToClass() {
        return toClass;
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

    public String getWeeknumDelay() {
        return weeknumDelay;
    }
}
