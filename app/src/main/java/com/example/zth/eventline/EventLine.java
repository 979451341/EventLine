package com.example.zth.eventline;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by ZTH on 2018/1/25.
 */

public class EventLine<T> {


    public static EventLine eventLine;

    public final static int MainThread = 0;
    public final static int SubThread = 1;

    private EventLine(){

    }

    public static EventLine getInstance(){

        if(eventLine == null){
            synchronized (EventLine.class){
                if(eventLine == null)
                    eventLine = new EventLine();
            }
        }
        return eventLine;

    }

    private ArrayList<Object> objects = new ArrayList<Object>();

    public void add(Object object){
        objects.add(object);
    }

    public void remove(Object object){
        objects.remove(object);
    }



    public void postData(final T ojb){

        for(final Object object : objects){
            int value = 0;

            if(object instanceof Activity){
                final Activity activity = (Activity)object;
                Class<? extends Activity> cls = activity.getClass();
                try {
                    final Method declaredMethod = cls.getDeclaredMethod("receive", ojb.getClass());
                    Annotation[] annotations = declaredMethod.getAnnotations();
                    for(Annotation annotation : annotations){
                        if(annotation.annotationType() == Process.class){
                            Process process = (Process)annotation;
                            value = process.value();

                        }
                    }


                    if(value == MainThread){
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    declaredMethod.invoke(activity, (Object) ojb);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    else if(value == SubThread){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    declaredMethod.invoke(activity, (Object) ojb);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }else if(object instanceof Fragment){

                final Fragment fragment = (Fragment)object;
                Class<? extends Fragment> cls = fragment.getClass();
                try {
                    final Method declaredMethod = cls.getDeclaredMethod("receive", ojb.getClass());
                    Annotation[] annotations = declaredMethod.getAnnotations();
                    for(Annotation annotation : annotations){
                        if(annotation.annotationType() == Process.class){
                            Process process = (Process)annotation;
                            value = process.value();

                        }
                    }


                    if(value == MainThread){
                        fragment.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    declaredMethod.invoke(fragment, (Object) ojb);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    else if(value == SubThread){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    declaredMethod.invoke(fragment, (Object) ojb);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }


            }else if(object instanceof Service){

                final Service service = (Service)object;
                Class<? extends Service> cls = service.getClass();
                try {
                    final Method declaredMethod = cls.getDeclaredMethod("receive", ojb.getClass());
                    Annotation[] annotations = declaredMethod.getAnnotations();
                    for(Annotation annotation : annotations){
                        if(annotation.annotationType() == Process.class){
                            Process process = (Process)annotation;
                            value = process.value();

                        }
                    }


                    if(value == MainThread){

                        try {
                            declaredMethod.invoke(service, (Object) ojb);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }

                    }
                    else if(value == SubThread){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    declaredMethod.invoke(service, (Object) ojb);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }


            }


        }

    }

}
