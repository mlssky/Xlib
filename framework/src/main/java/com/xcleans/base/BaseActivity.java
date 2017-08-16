package com.xcleans.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xcleans.base.log.XLog;

/**
 * @since 1.0
 */
public class BaseActivity extends Activity {

    private static final String TAG = BaseActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XLog.tag(TAG);
        XLog.d("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        XLog.d("");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        XLog.d("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        XLog.d("");
    }

    @Override
    protected void onPause() {
        super.onPause();
        XLog.d("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        XLog.d("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        XLog.d("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XLog.d("");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        XLog.d("");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        XLog.d("");
    }
}
