package com.skystudio.gifdemo;

import utils.ExceptionUtils;

/**
 * 全局applicatin类
 * Created by Administrator on 2016/7/5.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ExceptionUtils exceptionUtils=ExceptionUtils.getInstance();
        exceptionUtils.init(getApplicationContext());
    }
}
