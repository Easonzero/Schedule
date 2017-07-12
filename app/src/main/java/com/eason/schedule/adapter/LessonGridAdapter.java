package com.eason.schedule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eason.schedule.R;
import com.eason.schedule.ScheduleFunc;
import com.eason.schedule.data.LessonData;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by eason on 7/3/17.
 */

class LessonGridVH{
    @ViewInject(R.id.lesson_name)
    public TextView lesson_name;
    @ViewInject(R.id.location)
    public TextView location;
    @ViewInject(R.id.class_from_to)
    public TextView class_from_to;
    @ViewInject(R.id.time)
    public TextView time;

    public LessonGridVH(View itemView) {
        x.view().inject(this,itemView);
    }
}

public class LessonGridAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<LessonData> lessonDatas = null;

    public LessonGridAdapter(Context context){
        update();
        inflater = LayoutInflater.from(context);
    }

    public void update(){
        lessonDatas = ScheduleFunc.getInstance().find();
        if(lessonDatas == null) lessonDatas = new ArrayList();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lessonDatas.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return lessonDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LessonGridVH holder = null;
        if (null == convertView){
            convertView = inflater.inflate(R.layout.lesson_gird_item,parent,false);
            holder = new LessonGridVH(convertView);
            convertView.setTag(holder);
        }
        else{
            holder = (LessonGridVH)convertView.getTag();
        }
        holder.lesson_name.setText(lessonDatas.get(position).getLessonName());
        holder.location.setText(lessonDatas.get(position).getClassRoom());
        holder.class_from_to.setText(lessonDatas.get(position).getFromClass() + " - "
                + lessonDatas.get(position).getToClass() + "节");
        holder.time.setText(ScheduleFunc.getInstance().getTimeStr(Integer.parseInt(lessonDatas.get(position).getFromClass())));

        if(Integer.parseInt(lessonDatas.get(position).getFromClass()) < 5){
            holder.class_from_to.setBackgroundResource(R.drawable.ic_home_blue);
        }
        else{
            holder.class_from_to.setBackgroundResource(R.drawable.ic_home_green);
        }
        return convertView;
    }

}
