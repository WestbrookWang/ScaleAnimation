package com.westbrook.scaleanimation;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout button;

    private ImageView button1;

    private TextViewBuilder textViewBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });


        BottomSheetDialog


        test();


    }


    public void test(){


        String s = "abc/";

        String[] sList = s.split("/");

        for(int i = 0;i<sList.length;i++){


            Log.d("MainActivity-------->",sList[i]);


        }
    }


    private void startAnimation(){


        final  ViewAnimFactory viewAnimFactory = new ViewAnimFactory();

        Handler handler = new Handler();

        final TextView textView = new TextView(MainActivity.this);

        textView.setWidth(dp2Px(60));

        textView.setHeight(dp2Px(48));

        textView.setGravity(Gravity.CENTER);

        textView.setText("可享5G"+"\n"+"极速上传");

        viewAnimFactory.setView(button);

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewAnimFactory,"width",dp2Px(50),dp2Px(200));

        BezierInterpolator mBezierInterpolator = new BezierInterpolator(.42f, 0, .58f, 1f);

        objectAnimator.setInterpolator(mBezierInterpolator);

        objectAnimator.setDuration(350);

        objectAnimator.start();

        ViewAnimFactory viewAnimFactory1 = new ViewAnimFactory();

        viewAnimFactory1.setView(textView);

        final ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(viewAnimFactory1,"alpha",0,1);

        objectAnimator1.setDuration(240);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                params.leftMargin = dp2Px(60);

                textView.setLayoutParams(params);

                button.addView(textView);

                objectAnimator1.start();
            }
        },1000);





        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewAnimFactory,"width",dp2Px(50),dp2Px(100));

                BezierInterpolator mBezierInterpolator = new BezierInterpolator(.42f, 0, .58f, 1f);

                objectAnimator.setInterpolator(mBezierInterpolator);

                objectAnimator.setDuration(350);

                objectAnimator.start();
            }
        },250);



















    }



//    private void startAnimation() {
//
//        if(textViewBuilder == null){
//            textViewBuilder = new TextViewBuilder(button, this);
//            textViewBuilder.setWidthAndHeight(60, 100).setResumeScope(60, 360, 5000);
//        }
//
//
//        ViewAnimFactory viewAnimFactory = new ViewAnimFactory();
//
//        viewAnimFactory.setView(button);
//
//        viewAnimFactory.setListener(new ViewAnimFactory.ViewAnimListener() {
//            @Override
//            public void onWidth(int width) {
//                if (Utils.px2dp(MainActivity.this, width) > 120) {
//                    textViewBuilder.startAnimation();
//                }
//            }
//        });
//
//        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewAnimFactory, "width", dp2Px(60), dp2Px(360), dp2Px(60));
//
//        objectAnimator.setDuration(6000);
//
//        objectAnimator.start();
//    }


    private int dp2Px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
