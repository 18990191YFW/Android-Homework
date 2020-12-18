package com.example.zuoye;
import  android.context.Context;

public class zuoye {
}
public class utils
{
    public static int dip2px(Context context,float dpValue) {
        final float scale = comtext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int px2dip(Context context,float dpValue) {
        final float scale = comtext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}

