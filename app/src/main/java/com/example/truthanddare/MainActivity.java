package com.example.truthanddare;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView bottle;
    Button button;
    int initialDirection = 0;
    int newDirection;
    MediaPlayer m1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottle = findViewById(R.id.bottle);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateBottle();
            }
        });
    }
    private void rotateBottle() {

        m1 = MediaPlayer.create(this,R.raw.glass);
        Random random = new Random();
        newDirection = random.nextInt(36000) + 1;
        float pivotX = bottle.getWidth() / 2;
        float pivotY = bottle.getHeight() / 2;
        Animation rotate = new RotateAnimation(initialDirection, newDirection,pivotX,pivotY);
        rotate.setDuration(2000);
        rotate.setFillAfter(true);

        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
               button.setEnabled(false);
              m1.start();

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                button.setEnabled(true);
                m1.stop();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bottle.startAnimation(rotate);
        initialDirection = newDirection;

    }
}
