package com.xcleans.base;

import android.app.Application;

import com.xcleans.base.log.XLog;

/**
 * Created by mengliwei on 2017/7/16.
 * Function:
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XLog.init(null);
    }
}
