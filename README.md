# EventLine
事件总线框架实现，目前Activity、Fragment和Service三者能够相互通信，并且能够调整接收函数线程所在


建议在onCreate注册
EventLine.getInstance().add(this);

在onDestroy里注销
EventLine.getInstance().remove(this);

传送消息
                DataBean dataBean = new DataBean();
                dataBean.data = "来自ThreeActivity的消息";
                EventLine.getInstance().postData(dataBean);

通过receive函数接收消息（接收消息需要注册，发送不用）
    public void receive(DataBean dataBean){
        Log.v("zzw","在主线程MainActivity 接收到了"+dataBean.data);
    }
    
然后可以指定receive在子线程或主线程执行
    @Process(EventLine.MainThread)
    @Process(EventLine.SubThread)
    
    
 
    
    
