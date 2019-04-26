package com.westbrook.scaleanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TextViewBuilder {

    private Context context;

    private TextView textView;

    private ViewAnimFactory viewAnimFactory = new ViewAnimFactory();

    private ObjectAnimator objectAnimator;

    private boolean animationIsRunning;

    private View parentView;

    private int widthDp;

    private int heightDp;

    public TextViewBuilder(View parentView, Context context) {

        this.context = context;

        this.textView = new TextView(context);

        this.parentView = parentView;

    }


    public TextViewBuilder setWidthAndHeight(int widthDp, int heightDp) {

        this.widthDp = widthDp;

        this.heightDp = heightDp;

        textView.setWidth(Utils.dp2Px(context, widthDp));

        textView.setHeight(Utils.dp2Px(context, heightDp));

        textView.setText("cg"+"\n"+"123");

        viewAnimFactory.setView(textView);

        return this;

    }


    public TextViewBuilder setResumeScope(int startDp, int maxDp, long duration) {


        objectAnimator = ObjectAnimator.ofFloat(viewAnimFactory, "alpha", 0, 1, 0);

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animationIsRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationIsRunning = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        objectAnimator.setDuration(calulateDuration(startDp, maxDp, duration));

        return this;
    }


    private long calulateDuration(float startDp, float maxDp, long duration) {

        float resumeProportion = (maxDp - startDp - widthDp) / (maxDp - startDp);
        return (long) (duration * resumeProportion);

    }


    private TextViewBuilder addToView() {


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.leftMargin = Utils.dp2Px(context, 60);

        textView.setLayoutParams(params);

        textView.setTag(textView);

        ((RelativeLayout) parentView).addView(textView);

        return this;

    }


    public void startAnimation() {

        if (!animationIsRunning && objectAnimator != null) {

            if (textView.getParent() == null) {
                addToView();
            }

            objectAnimator.start();
        }
    }


}
