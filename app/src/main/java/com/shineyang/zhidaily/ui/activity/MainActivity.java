package com.shineyang.zhidaily.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jaeger.library.StatusBarUtil;
import com.shineyang.zhidaily.R;
import com.shineyang.zhidaily.ui.fragment.FragmentChannel;
import com.shineyang.zhidaily.ui.fragment.FragmentExplore;
import com.shineyang.zhidaily.ui.fragment.FragmentMore;
import com.shineyang.zhidaily.utils.SharedPreferencesUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_bottom_bar)
    BottomNavigationBar bottomNavigationBar;

    FragmentExplore fragmentExplore = new FragmentExplore();
    FragmentChannel fragmentChannel = new FragmentChannel();
    FragmentMore fragmentMore = new FragmentMore();
    private Fragment fromFragment;

    private static Boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpBottomBar();
        addDefaultFragment();
        StatusBarUtil.setTranslucentForImageView(this, null);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //注释掉以防view重叠
        //super.onSaveInstanceState(outState);
    }

    public void addDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_container, fragmentExplore);
        fragmentTransaction.commit();
        fromFragment = fragmentExplore;
    }

    public void setUpBottomBar() {
        SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(getApplicationContext());

        if (sharedPreferencesUtils.getBoolean("is_night_mode")) {
            bottomNavigationBar
                    .addItem(new BottomNavigationItem(R.mipmap.ic_bottombar_explore_inactive, "")
                            .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_bottombar_explore_active)))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_bottombar_channel_inactive, "")
                            .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_bottombar_channel_active)))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_bottombar_more_inactive, "")
                            .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_bottombar_more_active)))
                    .setFirstSelectedPosition(0)
                    .setBarBackgroundColor(R.color.grey1000)
                    .initialise();
        } else {
            bottomNavigationBar
                    .addItem(new BottomNavigationItem(R.mipmap.ic_bottombar_explore_active, "")
                            .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_bottombar_explore_inactive)))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_bottombar_channel_active, "")
                            .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_bottombar_channel_inactive)))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_bottombar_more_active, "")
                            .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_bottombar_more_inactive)))
                    .setFirstSelectedPosition(0)
                    .initialise();
        }


        //bottomNavigationBar.initBlurView(getApplicationContext(), decorView);

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        switchContent(fromFragment, fragmentExplore);
                        break;
                    case 1:
                        switchContent(fromFragment, fragmentChannel);
                        break;
                    case 2:
                        switchContent(fromFragment, fragmentMore);
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            //第二次点击
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTabReselected(int position) {
                switch (position) {
                    case 0:
                        fragmentExplore.scrollToTop();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 控制从activity
     */
    public void switchContent(Fragment from, Fragment to) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!to.isAdded()) { // 先判断是否被add过
            fragmentTransaction.hide(from).add(R.id.fl_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            Log.i("currentFragment", "------not added");
        } else {
            fragmentTransaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            Log.i("currentFragment", "------added");
        }

        fromFragment = to;
    }


    /**
     * 双击退出监听
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }

    private void exitBy2Click() {
        Timer tExit;
        if (!isExit) {
            isExit = true;
            Toast.makeText(MainActivity.this, getResources().getString(R.string.text_click_to_exit), Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);

        } else {
            finish();
            System.exit(0);
        }
    }

}
