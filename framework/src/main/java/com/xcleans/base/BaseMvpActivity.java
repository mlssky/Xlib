package com.xcleans.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @param <V> view layer
 * @param <P> present layer
 */
public abstract class BaseMvpActivity<V, P extends ABasePresent<V>> extends BaseActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mPresenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    /**
     * init yourself present layer object
     *
     * @return
     */
    public abstract P initPresenter();

}