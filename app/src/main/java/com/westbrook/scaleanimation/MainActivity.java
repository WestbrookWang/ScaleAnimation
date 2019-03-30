package com.westbrook.scaleanimation;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout button;

    private TextViewBuilder textViewBuilder;


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


    private void startAnimation() {

        if(textViewBuilder == null){
            textViewBuilder = new TextViewBuilder(button, this);
            textViewBuilder.setWidthAndHeight(60, 100).setResumeScope(60, 360, 5000);
        }


        ViewAnimFactory viewAnimFactory = new ViewAnimFactory();

        viewAnimFactory.setView(button);

        viewAnimFactory.setListener(new ViewAnimFactory.ViewAnimListener() {
            @Override
            public void onWidth(int width) {
                if (Utils.px2dp(MainActivity.this, width) > 120) {
                    textViewBuilder.startAnimation();
                }
            }
        });

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewAnimFactory, "width", dp2Px(60), dp2Px(360), dp2Px(60));

        objectAnimator.setDuration(6000);

        objectAnimator.start();
    }


    private int dp2Px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
