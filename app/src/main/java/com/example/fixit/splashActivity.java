package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class splashActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Animation topAnim;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.iv_logo);

        // Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        imageView.setAnimation(topAnim);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splashActivity.this, loginActivity.class));
                finish();
            }
        }, 3000);
    }
}