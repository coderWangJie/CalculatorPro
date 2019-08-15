package cn.wangj.calcpro;

import android.util.Log;

import cn.wangj.baslib.app.BaseApplication;
import cn.wangj.baslib.utils.LogUtil;

public class MyApplication extends BaseApplication {

    @Override
    protected void initLog() {
        LogUtil.setLogLevel(Log.DEBUG);

//        LogUtil.d("WangJ", "Wang", "Jie", "is My", "Name");
        LogUtil.d("WangJ", "");
    }

}
