package com.example.zth.eventline;

/**
 * Created by ZTH on 2018/1/26.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/15.
 */
public class MainFragment extends Fragment {


    TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        EventLine.getInstance().add(this);
        tv = (TextView)view.findViewById(R.id.tv);
        return view;
    }

    @Override
    public void onDestroy() {
        EventLine.getInstance().remove(this);
        super.onDestroy();
    }

    @Process(EventLine.MainThread)
    public void receive(DataBean dataBean){
        Log.v("zzw","在主线程MainFragment 接收到了"+dataBean.data);
    }
}
