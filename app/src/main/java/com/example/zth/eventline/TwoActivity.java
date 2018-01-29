package com.example.zth.eventline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TwoActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        EventLine.getInstance().add(this);

        Button btn  = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoActivity.this,ThreeActivity.class);
                startActivity(intent);
            }
        });

        tv = (TextView)findViewById(R.id.tv);

    }

    @Override
    protected void onDestroy() {
        EventLine.getInstance().remove(this);
        super.onDestroy();
    }

    @Process(EventLine.SubThread)
    public void receive(DataBean dataBean) throws InterruptedException {
        Log.v("zzw","在子线程TwoActivity 接收到了"+dataBean.data);

    }
}
