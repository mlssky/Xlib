package com.xcleans.base;

/**
 * @param <View>
 * @since 1.0
 */
public abstract class ABasePresent<View> {

    /**
     * 关联View
     */
    protected View mView;

    /**
     * 附加关联View
     *
     * @param view
     */
    public void attach(View view) {
        mView = view;
    }

    /**
     * 释放资源
     */
    public void detach() {
        mView = null;
    }
}
