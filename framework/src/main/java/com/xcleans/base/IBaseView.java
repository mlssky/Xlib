package com.xcleans.base;

import android.view.View;

/**
 * Created by mengliwei on 2017/7/16.
 * Function:
 */
public interface IBaseView {

    void initView();

    View getView();

    void onDestroy();

    void onPause();

    void onResume();

    void onStop();
}
