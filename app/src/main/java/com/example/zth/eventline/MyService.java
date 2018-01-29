package com.example.zth.eventline;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/12.
 */
public class MyService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        EventLine.getInstance().add(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DataBean dataBean = new DataBean();
        dataBean.data = "来自MyService的消息";
        EventLine.getInstance().postData(dataBean);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        EventLine.getInstance().remove(this);
        super.onDestroy();
    }

    @Process(EventLine.MainThread)
    public void receive(DataBean dataBean){
        Log.v("zzw","在主线程MyService 接收到了"+dataBean.data);
    }
}