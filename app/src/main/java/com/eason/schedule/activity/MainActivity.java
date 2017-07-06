package com.eason.schedule.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.eason.schedule.adapter.LessonGridAdapter;
import com.eason.schedule.R;
import com.eason.schedule.ScheduleFunc;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.enter)
    Button enter;
    @ViewInject(R.id.today_lesson)
    GridView lessonList;
    @ViewInject(R.id.lessonNoItem)
    RelativeLayout noLessonItem;

    LessonGridAdapter adapter_lesson = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScheduleFunc.getInstance().init(this);
        initLessonView(this);
    }

    private void initLessonView(Context context){
        adapter_lesson = new LessonGridAdapter(context);

        if(adapter_lesson.getCount() != 0){
            lessonList.setAdapter(adapter_lesson);
        }
        else{
            lessonList.setVisibility(View.GONE);
            noLessonItem.setVisibility(View.VISIBLE);
        }
    }

    @Event(R.id.enter)
    private void onEnterClick(View view){
        Intent intent = new Intent(MainActivity.this,ScheduleActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLessonView(this);
    }
}
