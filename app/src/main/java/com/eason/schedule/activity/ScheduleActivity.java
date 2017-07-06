package com.eason.schedule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.eason.schedule.data.LessonData;
import com.eason.schedule.R;
import com.eason.schedule.ScheduleFunc;
import com.eason.schedule.Utils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by eason on 7/3/17.
 */

@ContentView(R.layout.activity_schedule)
public class ScheduleActivity extends BaseActivity{
    private int[][] lessonLocation;
    private int[] weekDays;
    private int id = -1;

    @ViewInject(R.id.lessons)
    private ScrollView scroll;
    @ViewInject(R.id.weekoftoday)
    private TextView weekOfToday;
    @ViewInject(R.id.extra)
    private EditText extra;

    private ListView lvPopupList;
    private TextView lesson;
    private LinearLayout today;
    private PopupWindow pwMyPopWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lessonLocation = new int[][]{{R.id.date,R.id.date_mon,R.id.date_tue,R.id.date_wed,R.id.date_thu,R.id.date_fri,R.id.date_sta,R.id.date_sun},
                {R.id.lesson_1,R.id.mon_lesson_1,R.id.tue_lesson_1,R.id.wed_lesson_1,R.id.thu_lesson_1,R.id.fri_lesson_1,R.id.sta_lesson_1,R.id.sun_lesson_1},
                {R.id.lesson_2,R.id.mon_lesson_2,R.id.tue_lesson_2,R.id.wed_lesson_2,R.id.thu_lesson_2,R.id.fri_lesson_2,R.id.sta_lesson_2,R.id.sun_lesson_2},
                {R.id.lesson_3,R.id.mon_lesson_3,R.id.tue_lesson_3,R.id.wed_lesson_3,R.id.thu_lesson_3,R.id.fri_lesson_3,R.id.sta_lesson_3,R.id.sun_lesson_3},
                {R.id.lesson_4,R.id.mon_lesson_4,R.id.tue_lesson_4,R.id.wed_lesson_4,R.id.thu_lesson_4,R.id.fri_lesson_4,R.id.sta_lesson_4,R.id.sun_lesson_4},
                {R.id.lesson_5,R.id.mon_lesson_5,R.id.tue_lesson_5,R.id.wed_lesson_5,R.id.thu_lesson_5,R.id.fri_lesson_5,R.id.sta_lesson_5,R.id.sun_lesson_5},
                {R.id.lesson_6,R.id.mon_lesson_6,R.id.tue_lesson_6,R.id.wed_lesson_6,R.id.thu_lesson_6,R.id.fri_lesson_6,R.id.sta_lesson_6,R.id.sun_lesson_6},
                {R.id.lesson_7,R.id.mon_lesson_7,R.id.tue_lesson_7,R.id.wed_lesson_7,R.id.thu_lesson_7,R.id.fri_lesson_7,R.id.sta_lesson_7,R.id.sun_lesson_7},
                {R.id.lesson_8,R.id.mon_lesson_8,R.id.tue_lesson_8,R.id.wed_lesson_8,R.id.thu_lesson_8,R.id.fri_lesson_8,R.id.sta_lesson_8,R.id.sun_lesson_8},
                {R.id.lesson_9,R.id.mon_lesson_9,R.id.tue_lesson_9,R.id.wed_lesson_9,R.id.thu_lesson_9,R.id.fri_lesson_9,R.id.sta_lesson_9,R.id.sun_lesson_9},
                {R.id.lesson_10,R.id.mon_lesson_10,R.id.tue_lesson_10,R.id.wed_lesson_10,R.id.thu_lesson_10,R.id.fri_lesson_10,R.id.sta_lesson_10,R.id.sun_lesson_10},
                {R.id.lesson_11,R.id.mon_lesson_11,R.id.tue_lesson_11,R.id.wed_lesson_11,R.id.thu_lesson_11,R.id.fri_lesson_11,R.id.sta_lesson_11,R.id.sun_lesson_11},
                {R.id.lesson_12,R.id.mon_lesson_12,R.id.tue_lesson_12,R.id.wed_lesson_12,R.id.thu_lesson_12,R.id.fri_lesson_12,R.id.sta_lesson_12,R.id.sun_lesson_12}};
        weekDays = new int[]{R.id.mon,R.id.tue,R.id.wed,R.id.thu,R.id.fri,R.id.sta,R.id.sun};

        today = (LinearLayout) findViewById(weekDays[ScheduleFunc.getInstance()._today-1]);
        today.setBackgroundResource(R.drawable.class_lesson_tody);

        initWeekOfToday();
        initLesson(ScheduleFunc.getInstance()._weekOfToday);
        initSetLessonListener();

        extra.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(id!=-1){
                    ScheduleFunc.getInstance().extra.setExtra(v.getText().toString());
                    ScheduleFunc.getInstance().updateExtra();
                }
                extra.clearFocus();
                return false;
            }
        });
    }

    @Event(R.id.weekoftoday)
    private void onWeekClick(View view){
        if (pwMyPopWindow.isShowing()) {
            pwMyPopWindow.dismiss();
        } else {
            pwMyPopWindow.showAsDropDown(view,-50,0);
        }
    }

    @Event(value=R.id.lessons,type=View.OnTouchListener.class)
    private boolean onLessonsTouch(View arg0, MotionEvent event){
        if(MotionEvent.ACTION_MOVE == event.getAction()){
            if(id != (-1)){
                lesson = (TextView) findViewById(id);
                if(lesson.getTag().toString().equals("add")){
                    lesson.setBackgroundResource(R.drawable.bg_lesson);
                    lesson.setTag("none");
                }else if(lesson.getTag().toString().equals("select")){
                    setLessonBackground("noraml",lesson,Integer.parseInt(lesson.getTag(R.id.resindex).toString()));
                    lesson.setTag("lesson");
                }
                id = -1;
            }
        }
        return false;
    }

    private void initWeekOfToday(){
        weekOfToday.setText(ScheduleFunc.getInstance().getWeekNumStr(ScheduleFunc.getInstance()._weekOfToday));
        initPopupWindow();
    }

    private void initPopupWindow() {
        ArrayList<Map<String, String>> setList = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        for(int i = 0;i < 25;i++){
            map = new HashMap<String, String>();
            if(i < 9)
                map.put("share_key", "第  " + (i+1) + "  周");
            else
                map.put("share_key", "第 " + (i+1) + "周");
            setList.add(map);
        }
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.task_detail_popupwindow, null);
        lvPopupList = (ListView) layout.findViewById(R.id.lv_popup_list);
        lvPopupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ScheduleFunc.getInstance().currentWeek = position+1;
                reset(ScheduleFunc.getInstance().currentWeek);
                pwMyPopWindow.dismiss();
            }
        });
        pwMyPopWindow = new PopupWindow(layout);
        pwMyPopWindow.setFocusable(true);
        lvPopupList.setAdapter(new SimpleAdapter(ScheduleActivity.this, setList,
                R.layout.list_item_text, new String[] { "share_key" },
                new int[] { R.id.tv_list_item }));
        lvPopupList.setSelection(ScheduleFunc.getInstance()._weekOfToday-2);
        pwMyPopWindow.setBackgroundDrawable(ScheduleActivity.this.getResources().getDrawable(R.drawable.popupwindow));

        lvPopupList.measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        pwMyPopWindow.setWidth(lvPopupList.getMeasuredWidth());
        pwMyPopWindow.setHeight((lvPopupList.getMeasuredHeight() + 20)
                * 3);

        pwMyPopWindow.setOutsideTouchable(true);
    }

    private void initLesson(int week){
        List<LessonData> lessonDatas = ScheduleFunc.getInstance().find(week+"");
        if(lessonDatas != null){
            for(int i = 0;i < lessonDatas.size();i++){
                LessonData ld= lessonDatas.get(i);
                TextView lesson = (TextView) findViewById(lessonLocation[Integer.parseInt(ld.getFromClass())][Integer.parseInt(ld.getWeekDay())]);
                lesson.setText(ld.getLessonName()+"@"+ld.getClassRoom());
                int t = setLessonBackground("normal",lesson);
                int dt = Integer.parseInt(ld.getToClass()) - Integer.parseInt(ld.getFromClass());
                for(int j = 1;j <= dt;j++){
                    TextView lessongone = (TextView) findViewById(lessonLocation[Integer.parseInt(ld.getFromClass())+j][Integer.parseInt(ld.getWeekDay())]);
                    lessongone.setVisibility(View.GONE);
                }
                lesson.setPadding(0, 0, 0, 0);
                lesson.getLayoutParams().height = Utils.dip2px(ScheduleActivity.this,80*(dt+1));
                lesson.setTag("lesson");
                lesson.setTag(R.id.resindex,t+"");
            }
        }
    }

    private void initSetLessonListener(){

        for(int i = 1;i <= 12;i++){
            for(int j = 1;j <= 7;j++){
                lesson = (TextView) findViewById(lessonLocation[i][j]);
                final boolean hasLesson = !lesson.getText().toString().equals("");
                lesson.setTag(hasLesson?"lesson":"none");
                final int weekday = j;
                final int classnum = i;
                lesson.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        // TODO Auto-generated method stub
                        if(view.getTag().toString().equals("lesson")){
                            setLessonBackground("select",view,Integer.parseInt(view.getTag(R.id.resindex).toString()));
                            if(id != (-1)){
                                lesson = (TextView) findViewById(id);
                                if(lesson.getTag().toString().equals("add")){
                                    lesson.setBackgroundResource(R.drawable.bg_lesson);
                                    lesson.setTag("none");
                                }else if(lesson.getTag().toString().equals("select")){
                                    setLessonBackground("noraml",lesson,Integer.parseInt(lesson.getTag(R.id.resindex).toString()));
                                    lesson.setTag("lesson");
                                }
                            }
                            ScheduleFunc.getInstance().getExtra(ScheduleFunc.getInstance().currentWeek+"",weekday+"",classnum+"");
                            extra.setText(ScheduleFunc.getInstance().extra.getExtra());
                            id = view.getId();
                            view.setTag("select");
                        }
                        else if(view.getTag().toString().equals("select")){
                            view.setTag("lesson");
                            id = -1;
                            Intent intent = new Intent(ScheduleActivity.this,WatchLesson.class);
                            ScheduleFunc.getInstance().lesson.setWeekDay(weekday+"");
                            ScheduleFunc.getInstance().lesson.setFromClass(classnum+"");
                            startActivityForResult(intent,1);
                            setLessonBackground("noraml",view,Integer.parseInt(view.getTag(R.id.resindex).toString()));
                        }
                        else if(view.getTag().toString().equals("none")){
                            view.setBackgroundResource(R.drawable.bg_lesson_add);
                            if(id != (-1)){
                                lesson = (TextView) findViewById(id);
                                if(lesson.getTag().toString().equals("add")){
                                    lesson.setBackgroundResource(R.drawable.bg_lesson);
                                    lesson.setTag("none");
                                }else if(lesson.getTag().toString().equals("select")){
                                    setLessonBackground("noraml",lesson,Integer.parseInt(lesson.getTag(R.id.resindex).toString()));
                                    lesson.setTag("lesson");
                                }
                            }
                            ScheduleFunc.getInstance().getExtra(ScheduleFunc.getInstance().currentWeek+"",weekday+"",classnum+"");
                            extra.setText(ScheduleFunc.getInstance().extra.getExtra());
                            id = view.getId();
                            view.setTag("add");
                        }
                        else if(view.getTag().toString().equals("add")){
                            view.setTag("none");
                            id = -1;
                            Intent intent = new Intent(ScheduleActivity.this,CreateLesson.class);
                            ScheduleFunc.getInstance().lesson.setWeekDay(weekday+"");
                            ScheduleFunc.getInstance().lesson.setFromClass(classnum+"");
                            startActivityForResult(intent,0);
                            view.setBackgroundResource(R.drawable.bg_lesson);
                        }
                    }

                });
            }
        }
    }

    private void reset(int weeknum){
        weekOfToday.setText(ScheduleFunc.getInstance().getWeekNumStr(weeknum));
        for(int i = 1;i <= 12;i++){
            for(int j = 1;j <= 7;j++){
                lesson = (TextView) findViewById(lessonLocation[i][j]);
                lesson.setVisibility(View.VISIBLE);
                lesson.setBackgroundResource(R.drawable.bg_lesson);
                lesson.getLayoutParams().height = Utils.dip2px(ScheduleActivity.this,80);
                lesson.setText("");
            }
        }
        initLesson(weeknum);
        initSetLessonListener();
        lvPopupList.setSelection(weeknum-2);
    }

    public void onBackPressed() {
        super.onBackPressed();

    }

    @Event(R.id.back)
    private void onBackClick(View view){
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch(requestCode){
            case 0:
                ScheduleFunc.getInstance().currentWeek = ScheduleFunc.getInstance()._weekOfToday;
                reset(ScheduleFunc.getInstance().currentWeek);
                break;
            case 1:
                reset(ScheduleFunc.getInstance().currentWeek);
                break;
        }
    }

    private int setLessonBackground(String flag,View lesson){
        Random random = new Random();
        int[] color;
        int i = random.nextInt(4);
        if(flag.equals("select")){
            color = new int[]{R.drawable.bg_lesson_green_select,R.drawable.bg_lesson_purple_select,
                    R.drawable.bg_lesson_blue_select,R.drawable.bg_lesson_grey_select};
        }else{
            color = new int[]{R.drawable.bg_lesson_green,R.drawable.bg_lesson_purple,
                    R.drawable.bg_lesson_blue,R.drawable.bg_lesson_grey};
        }
        lesson.setBackgroundResource(color[i]);
        return i;
    }

    private int setLessonBackground(String flag,View lesson,int i){
        int[] color;
        if(flag.equals("select")){
            color = new int[]{R.drawable.bg_lesson_green_select,R.drawable.bg_lesson_purple_select,
                    R.drawable.bg_lesson_blue_select,R.drawable.bg_lesson_grey_select};
        }else{
            color = new int[]{R.drawable.bg_lesson_green,R.drawable.bg_lesson_purple,
                    R.drawable.bg_lesson_blue,R.drawable.bg_lesson_grey};
        }
        lesson.setBackgroundResource(color[i]);
        return i;
    }
}
