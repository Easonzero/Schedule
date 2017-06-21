package com.eason.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by eason on 17-6-21.
 */

@ContentView(R.layout.watchlesson)
public class WatchLesson extends BaseActivity {
    @ViewInject(R.id.lessontitle)
    private TextView title;
    @ViewInject(R.id.lesson_name)
    private TextView l_lessonName;
    @ViewInject(R.id.location)
    private TextView l_classroom;
    @ViewInject(R.id.teacher)
    private TextView l_teacher;
    @ViewInject(R.id.classnum)
    private TextView l_classnum;
    @ViewInject(R.id.weeknum)
    private TextView l_weeknum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        String classnum = ScheduleFunc.getInstance().lesson.getFromClass();
        String weekDay = ScheduleFunc.getInstance().lesson.getWeekDay();
        String weeknum = ScheduleFunc.getInstance().currentWeek+"";

        ScheduleFunc.getInstance().lesson = ScheduleFunc.getInstance().find(weeknum,weekDay,classnum);
        initData();
    }

    @Event(R.id.Cancel)
    private void onCancelClick(View view){
        WatchLesson.this.finish();
    }

    @Event(R.id.edit)
    private void onEditClick(View view){
        WatchLesson.this.finish();
        Intent intent = new Intent(WatchLesson.this,ChangeLesson.class);
        startActivity(intent);
    }

    @Event(R.id.delete)
    private void onDeleteClick(View view){
        ScheduleFunc.getInstance().delete();
        WatchLesson.this.finish();
    }

    private void initData(){
        title.setText(ScheduleFunc.getInstance().lesson.getLessonName());
        l_lessonName.setText(ScheduleFunc.getInstance().lesson.getLessonName());
        l_classroom.setText(ScheduleFunc.getInstance().lesson.getClassRoom());
        l_teacher.setText(ScheduleFunc.getInstance().lesson.getTeacher());
        l_classnum.setText(ScheduleFunc.getInstance().getWeekStr(Integer.parseInt(ScheduleFunc.getInstance().lesson.getWeekDay()))
                + "  " + ScheduleFunc.getInstance().lesson.getFromClass() + " - " + ScheduleFunc.getInstance().lesson.getToClass() + "节");
        l_weeknum.setText(ScheduleFunc.getInstance().lesson.getWeeknumDelay());
    }
}