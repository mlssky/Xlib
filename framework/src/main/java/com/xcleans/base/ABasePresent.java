package com.xcleans.base;

/**
 * @param <View>
 */
public abstract class ABasePresent<View> {

    public View mView;

    public void attach(View view) {
        mView = view;
    }

    public void detach() {
        mView = null;
    }
}
