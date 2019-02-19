package cn.wangj.baslib.app;

import android.app.Application;
import android.content.res.Configuration;

public abstract class BaseApplication extends Application {

    abstract void initLog();

    @Override
    public void onCreate() {
        super.onCreate();

        initLog();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
