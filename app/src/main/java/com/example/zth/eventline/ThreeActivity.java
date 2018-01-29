package com.example.zth.eventline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        Button btn  = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ThreeActivity.this, MyService.class);
                startService(startIntent);
                DataBean dataBean = new DataBean();
                dataBean.data = "来自ThreeActivity的消息";
                EventLine.getInstance().postData(dataBean);
            }
        });

    }

    @Override
    protected void onDestroy() {
        Intent stopIntent = new Intent(this, MyService.class);
        stopService(stopIntent);
        super.onDestroy();
    }
}
