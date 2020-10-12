package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class marquee extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_marquee;//声明一个为本视图对象
    private  boolean isPaused=false;//跑马灯是否暂停滚动
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        //从布局文件中获取名叫tv_marquee的文本视图
        tv_marquee=findViewById(R.id.tv_marquee);
        //给tv。marquee设置点击监听器
        tv_marquee.setOnClickListener(this);
        tv_marquee.requestFocus();//强制获取焦点让跑马灯滚起来
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_marquee){
            isPaused=!isPaused;
            if(isPaused){
                tv_marquee.setFocusable(false);//不允许获取焦点
                tv_marquee.setFocusableInTouchMode(false);
            }else{
                tv_marquee.setFocusable(true);//允许获取焦点
                tv_marquee.setFocusableInTouchMode(true);
                tv_marquee.requestFocus();
            }
        }
    }
}