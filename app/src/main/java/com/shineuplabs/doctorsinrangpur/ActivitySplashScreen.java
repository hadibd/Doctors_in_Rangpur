package com.shineuplabs.doctorsinrangpur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    TextView appNameTop, appNameTv;
    LinearLayout BottomTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // For FullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        Animation fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
        Animation FadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        appNameTv = findViewById(R.id.appNameTv);
        appNameTop = findViewById(R.id.appNameTop);
        BottomTv = findViewById(R.id.BottomTv);

        appNameTv.setAnimation(FadeIn);
        appNameTop.setAnimation(FadeIn);
        BottomTv.setAnimation(fromBottom);

/*

        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    }
                }
        );
*/

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), ActivityMain.class));
                finish();
            }
        },3000);

    }
}