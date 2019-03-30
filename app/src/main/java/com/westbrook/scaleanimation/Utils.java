package com.westbrook.scaleanimation;

import android.content.Context;

public class Utils {


    public static int dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }



    public static int px2dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((px-0.5)/scale);
    }



}
