package com.xcleans.notify;

/**
 * Created by mengliwei on 2017/7/15.
 * Function:
 */
public interface IXNotification<Msg extends IXMessage> {

    void showMessage(Msg msg);

    void cancelMessage(Msg msg);


    public class Builder {

        IXNotification build() {
            return null;
        }



    }
}
