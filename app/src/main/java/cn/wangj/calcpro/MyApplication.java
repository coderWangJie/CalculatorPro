package cn.wangj.calcpro;

import android.util.Log;

import cn.wangj.baslib.app.BaseApplication;
import cn.wangj.baslib.utils.Logger;

public class MyApplication extends BaseApplication {

    @Override
    protected void initLog() {
        Logger.setLogLevel(Log.DEBUG);

//        Logger.d("WangJ", "Wang", "Jie", "is My", "Name");
        Logger.d("WangJ", "");
    }

}
