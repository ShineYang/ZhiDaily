package com.shineyang.zhidaily.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.iVew.ISplashView;
import com.shineyang.zhidaily.presenter.SplashPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends MvpActivity<ISplashView, SplashPresenter> implements ISplashView {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setFullScreen();
        getPresenter().getSplashImageUrl();
    }

    @NonNull
    @Override
    public SplashPresenter createPresenter() {
        return new SplashPresenter();
    }

    public void startActivityWithAnim() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.enlarge);
        animation.setDuration(2500);
        animation.setFillAfter(true);
        if (ivSplash != null) ivSplash.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    /**
     * 开启主页面
     */
    public void startMainActivity() {
        Intent it = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(it);
        finish();
    }

    /**
     * 全屏设置
     */
    public void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Override
    public void setSplashImage(String url) {
        Glide.with(this).load(url)
                .crossFade(500)
                .into(new GlideDrawableImageViewTarget(ivSplash){
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        startActivityWithAnim();
                    }
                });
    }

    @Override
    public void onError() {
        startActivityWithAnim();
    }

    /**
     * 屏蔽back键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return false;
    }

}
