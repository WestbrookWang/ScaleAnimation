package com.westbrook.scaleanimation;

import android.content.Context;

public class Util {


    public static int dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }



}
