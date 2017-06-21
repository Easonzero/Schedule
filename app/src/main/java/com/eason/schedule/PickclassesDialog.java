package com.eason.schedule;

/**
 * Created by eason on 17-6-21.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import me.drakeet.materialdialog.MaterialDialog;

import java.util.Arrays;

public class PickclassesDialog extends MaterialDialog {
    public int date,fromClass,toClass;
    private String[] dateStr = {"周一", "周二", "周三", "周四", "周五","周六","周日"};
    private String[] fromClassStr = {"第1节", "第2节", "第3节", "第4节", "第5节","第6节","第7节","第8节", "第9节", "第10节", "第11节", "第12节"};
    private String[] toClassStr = {"到1节", "到2节", "到3节", "到4节", "到5节","到6节","到7节","到8节", "到9节", "到10节", "到11节", "到12节"};
    public PickclassesDialog(Context context,String weekday,String classfrom) {
        super(context);
        // TODO Auto-generated constructor stub
        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.pick_classes_dialog, null);

        final WheelView date = (WheelView) view.findViewById(R.id.date);
        final WheelView fromClass = (WheelView) view.findViewById(R.id.fromClass);
        final WheelView toClass = (WheelView) view.findViewById(R.id.toClass);

        date.setItems(Arrays.asList(dateStr));
        date.setOffset(1);
        date.setSeletion(Integer.parseInt(weekday)-1);
        fromClass.setItems(Arrays.asList(fromClassStr));
        fromClass.setOffset(1);
        fromClass.setSeletion(Integer.parseInt(classfrom)-1);
        toClass.setItems(Arrays.asList(toClassStr));
        toClass.setOffset(1);
        toClass.setSeletion(Integer.parseInt(classfrom)-1);

        this.date = date.getSeletedIndex()+1;
        this.fromClass = fromClass.getSeletedIndex()+1;
        this.toClass = toClass.getSeletedIndex()+1;

        date.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                PickclassesDialog.this.date = selectedIndex;
            }
        });

        fromClass.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                PickclassesDialog.this.fromClass = selectedIndex;
                if(PickclassesDialog.this.fromClass > PickclassesDialog.this.toClass){
                    toClass.setSeletion(PickclassesDialog.this.fromClass-1);
                }
            }
        });

        toClass.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                if(PickclassesDialog.this.fromClass > selectedIndex){
                    toClass.setSeletion(PickclassesDialog.this.fromClass-1);
                }
                PickclassesDialog.this.toClass = selectedIndex;
            }
        });

        this.setContentView(view);
    }
}