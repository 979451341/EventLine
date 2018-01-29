package com.example.zth.eventline;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FrameMetricsAggregator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventLine.getInstance().add(this);
        Button btn  = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TwoActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onDestroy() {
        EventLine.getInstance().remove(this);
        super.onDestroy();

    }

    @Process(EventLine.MainThread)
    public void receive(DataBean dataBean){
        Log.v("zzw","在主线程MainActivity 接收到了"+dataBean.data);
    }

}
