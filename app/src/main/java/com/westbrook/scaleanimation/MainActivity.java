package com.westbrook.scaleanimation;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout button;

    private boolean textAnimationStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
    }


    private void startAnimation(){

        ViewAnimFactory viewAnimFactory = new ViewAnimFactory();

        viewAnimFactory.setListener(new ViewAnimFactory.ViewAnimListener() {
            @Override
            public void onWidth(int width) {

                if(width > dp2Px(100) && !textAnimationStart){
                    startTextAnimation();
                }
            }
        });

        viewAnimFactory.setView(button);

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewAnimFactory,"width",dp2Px(60),dp2Px(350));

        objectAnimator.setDuration(5000);

        objectAnimator.start();
    }


    private void startTextAnimation(){

        textAnimationStart = true;

        TextView textView = new TextView(this);

        textView.setWidth(dp2Px(40));

        textView.setHeight(dp2Px(100));

        textView.setText("gdyugeuiwqvo");

        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);

        alphaAnimation.setDuration(6000);

        textView.setAnimation(alphaAnimation);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.leftMargin = dp2Px(70);

        textView.setLayoutParams(params);

        textView.setTag(9);

        button.addView(textView);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textAnimationStart = false;
                startRepeatAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {



            }
        });

        alphaAnimation.start();


    }


    private void startRepeatAnimation(){


        ViewAnimFactory viewAnimFactory = new ViewAnimFactory();

        viewAnimFactory.setListener(new ViewAnimFactory.ViewAnimListener() {
            @Override
            public void onWidth(int width) {

                if(width < dp2Px(300) && !textAnimationStart){
                    startTextRepeatAnimation();
                }
            }
        });

        viewAnimFactory.setView(button);

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewAnimFactory,"width",dp2Px(350),dp2Px(60));

        objectAnimator.setDuration(5000);

        objectAnimator.start();

    }


    private void startTextRepeatAnimation(){

        final TextView textView = (TextView) button.findViewWithTag(9);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);

        alphaAnimation.setDuration(2000);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                textAnimationStart = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                button.removeView(textView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        textView.setAnimation(alphaAnimation);

        alphaAnimation.start();
    }


    private int dp2Px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
