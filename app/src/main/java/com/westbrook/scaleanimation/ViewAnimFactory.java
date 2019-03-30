package com.westbrook.scaleanimation;

import android.util.Log;
import android.view.View;

public class ViewAnimFactory {


    private View view;

    private ViewAnimListener listener;

    /**
     * 改变大小
     *
     * @param width
     */
    public void setWidth(int width) {
        view.getLayoutParams().width = width;
        view.requestLayout();

        listener.onWidth(width);

        Log.e(ViewAnimFactory.class.getName(), "setWidth: " + width);
    }

    public void setListener(ViewAnimListener listener){
        this.listener = listener;
    }

    public int getWidth(){

        return view.getLayoutParams().width;

    }

    public void setView(View view) {
        this.view = view;
    }

    public void setHeight(int height) {
        view.getLayoutParams().height = height;
    }



    public interface ViewAnimListener{

        void onWidth(int width);

    }

}
