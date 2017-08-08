package com.xcleans;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.xcleans.image.ImageWrapp;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageWrapp imageWrapp = new ImageWrapp();
        String input = "/mnt/sdcard/1.gif";
        String output = "/mnt/sdcard/2.gif";
        long t1 = System.currentTimeMillis();
        imageWrapp.handlerImage(input, output, 45, 0, 0);
        Log.d("image2", "total time:" + (System.currentTimeMillis() - t1) + "ms");
    }
}
