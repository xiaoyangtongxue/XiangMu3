package activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bwie.xiangmu3.R;
import com.example.bwie.xiangmu3.TabFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.Xiangqingbean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XiangQingActivity extends AppCompatActivity {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            xiangqingbean= (Xiangqingbean) msg.obj;
            jiecaoPlayer.setUp(xiangqingbean.getRet().getHDURL(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,title);
            initView();
        }
    };
    String s1;
    @BindView(R.id.xq_back)
    ImageView xqBack;
    @BindView(R.id.xq_title)
    TextView xqTitle;
    @BindView(R.id.xq_biaoti)
    RelativeLayout xqBiaoti;
    @BindView(R.id.jiecao_Player)
    JCVideoPlayerStandard jiecaoPlayer;
    private String title;
    private List<String> list;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Xiangqingbean xiangqingbean;
    private String jieko;
    private String dataId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        ButterKnife.bind(this);

        Intent intent = getIntent();
//        intent.putExtra("title",childList.get(postion).getTitle());
//        intent.putExtra("description",childList.get(postion).getDescription());
//        intent.putExtra("dataId",childList.get(postion).getDataId());
        title = intent.getStringExtra("title");
        dataId = intent.getStringExtra("dataId");
        jieko = intent.getStringExtra("jieko");
        if(!title.equals("")){
            xqTitle.setText(title);
        }
        if(!dataId.equals("")){
            OkHttpClient client = new OkHttpClient();
            Request build = new Request.Builder()
                    .get()
                    .url(jieko)
                    .build();
            Call call = client.newCall(build);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    Gson gson=new Gson();
                    xiangqingbean = gson.fromJson(string, Xiangqingbean.class);

                    if(xiangqingbean !=null){
                        Message message= Message.obtain();
                        message.obj=xiangqingbean;
                        handler.sendMessage(message);
                    }
                }
            });

        }


    }
    @OnClick({R.id.xq_back, R.id.xq_title, R.id.jiecao_Player})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xq_back:
                finish();
                break;
            case R.id.xq_title:
                break;
            case R.id.jiecao_Player:

                break;
        }
    }

    @Override
    public void onBackPressed() {

        if(jiecaoPlayer.backPress()){
            return;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        jiecaoPlayer.releaseAllVideos();
    }
    private void initView() {
        list = new ArrayList<>();
        list.add("简介");
        list.add("评价");
        mTabLayout = (TabLayout) findViewById(R.id.tl_tab);
        mViewPager = (ViewPager) findViewById(R.id.vp_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @Override
           public Fragment getItem(int position) {
                TabFragment fragment = new TabFragment();
                Bundle bundle = new Bundle();
                if(list.get(position).endsWith("简介")){
                    if(xiangqingbean!=null) {
                        bundle.putString("name","简介");
                        bundle.putString("description",xiangqingbean.getRet().getDescription());
                        bundle.putString("daoyan",xiangqingbean.getRet().getDirector());
                        bundle.putString("yanyuan",xiangqingbean.getRet().getActors());
                        bundle.putString("jieko",jieko);
                        bundle.putString("dataId",dataId);
                    }
                }else if(list.get(position).endsWith("评价")){
                    bundle.putString("name","评价");
                    bundle.putString("dataId",dataId);
                }
                fragment.setArguments(bundle);
                return fragment;
           }

           @Override
           public int getCount() {
               return list.size();
           }
       });
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
