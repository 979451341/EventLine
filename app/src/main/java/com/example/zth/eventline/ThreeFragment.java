package com.example.zth.eventline;

/**
 * Created by ZTH on 2018/1/26.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/15.
 */
public class ThreeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_three,container,false);
        Button btn  = (Button)view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBean dataBean = new DataBean();
                dataBean.data = "来自ThreeFragment的消息";
                EventLine.getInstance().postData(dataBean);
            }
        });

        return view;
    }

}
