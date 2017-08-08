package com.xcleans.image;

/**
 * Created by mengliwei on 2017/7/25.
 * Function:
 */
public class ImageWrapp {

    static {
        System.loadLibrary("MagickCore-7");
    }

    public native void handlerImage(String inputPath, String outpath, double roate, int w, int h);
}
