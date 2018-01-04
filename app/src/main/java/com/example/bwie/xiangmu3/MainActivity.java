package com.example.bwie.xiangmu3;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.ButterKnife;
import fragment.Fancy;
import fragment.Found;
import fragment.My;
import fragment.Special;

import static com.example.bwie.xiangmu3.R.drawable.found;

public class MainActivity extends AppCompatActivity {
private int shuzi=3;
   ResideLayout resideLayout;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RelativeLayout zhure1;
    private RelativeLayout zhure2;
    private TextView zi;
     BottomTabBar mb;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            zhure1.setVisibility(View.GONE);
            zi.setVisibility(View.GONE);
            zhure2.setVisibility(View.VISIBLE);
            resideLayout.setVisibility(View.VISIBLE);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        resideLayout=(ResideLayout)findViewById(R.id.sm);
        mb=(BottomTabBar)findViewById(R.id.main_tab) ;
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        transaction.commit();
        zhure1 = (RelativeLayout) findViewById(R.id.zhu_re1);
        zhure2 = (RelativeLayout) findViewById(R.id.zhu_re2);
        zi = (TextView) findViewById(R.id.zhu_zi);


        AnimatorSet animatorSetsuofang = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(zhure1, "scaleX", 1, 1.4f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(zhure1, "scaleY", 1, 1.4f);

        animatorSetsuofang.setDuration(3000);
        animatorSetsuofang.setInterpolator(new DecelerateInterpolator());
        animatorSetsuofang.play(scaleX).with(scaleY);//两个动画同时开始
        animatorSetsuofang.start();
        handler.sendEmptyMessageDelayed(1,3000);

        mb.init(getSupportFragmentManager())
                .setImgSize(60, 60)
                .setFontSize(14)
                .setTabPadding(10, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("精选", found, Found.class)
                .addTabItem("专题", R.drawable.special, Special.class)
                .addTabItem("发现", R.drawable.fancy, Fancy.class)
                .addTabItem("我的", R.drawable.my

                        , My.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(final int position, String name) {
                    }
                }).setTabBarBackgroundResource(R.drawable.bottom_bg)
                .setBackgroundResource(R.drawable.bg_blue);

    }


}
