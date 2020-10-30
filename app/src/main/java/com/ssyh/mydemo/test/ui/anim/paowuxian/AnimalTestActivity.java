package com.ssyh.mydemo.test.ui.anim.paowuxian;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.ssyh.mydemo.R;
import com.ssyh.mydemo.test.ui.view.RandomImgLayout;

import java.util.ArrayList;
import java.util.List;

public class AnimalTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_test);
        findViewById(R.id.iv_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.rl_animal).setVisibility(View.INVISIBLE);
                AnimalUtils.add(v,
                        /*findViewById(R.id.iv_destin)*/(ViewGroup) findViewById(R.id.random_iv_layout),
                        /*(ViewGroup) findViewById(R.id.rl_anim_contanier)*/(ViewGroup) getWindow().getDecorView(),
                        R.drawable.icon_hongbao1,
                        AnimalTestActivity.this,
                        new AnimalUtils.AnimatorListener() {

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                findViewById(R.id.rl_animal).setVisibility(View.VISIBLE);
                                ObjectAnimator alpha = ObjectAnimator.ofFloat(findViewById(R.id.rl_animal), "alpha", 0f, 1f);
                                //alpha.setRepeatCount(ObjectAnimator.INFINITE);
                                //alpha.setInterpolator(new LinearInterpolator());
                                alpha.setDuration(300);
                                alpha.start();

                                ((RandomImgLayout)findViewById(R.id.random_iv_layout)).show(true);

                            }
                        });
            }
        });

    }
}