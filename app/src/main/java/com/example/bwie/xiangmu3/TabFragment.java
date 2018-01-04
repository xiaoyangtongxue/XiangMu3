package com.example.bwie.xiangmu3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import activity.XiangQingActivity;
import adapter.TabJianshuAdapter;
import adapter.TabPingjiaAdapter;
import bean.PingjiaBean;
import bean.Xiangqingbean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jieko.FoundJk;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangyueyi on 2017/12/29.
 */

public class TabFragment extends Fragment{
    private boolean flag=true;
    @BindView(R.id.daoyan)
    TextView daoyan;
    @BindView(R.id.yanyuan)
    TextView yanyuan;
    @BindView(R.id.jianshu)
    TextView jianshu;
    @BindView(R.id.zhankai)
    TextView zhankai;
    Unbinder unbinder;
    private RelativeLayout re;
    private TabJianshuAdapter adapter;
    private TabPingjiaAdapter adapter1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==100) {
                Xiangqingbean bean = (Xiangqingbean) msg.obj;
                list = bean.getRet().getList();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        childList = list.get(i).getChildList();
                    }
                    if (childList != null) {
                        adapter = new TabJianshuAdapter(getActivity(), childList, new FoundJk() {
                            @Override
                            public void Chuanzhi(int postion) {
                                Intent intent = new Intent(getActivity(), XiangQingActivity.class);
                                intent.putExtra("title",childList.get(postion).getTitle());
                                intent.putExtra("description",childList.get(postion).getDescription());
                                intent.putExtra("dataId",childList.get(postion).getDataId());
                                intent.putExtra("jieko",childList.get(postion).getLoadURL());
                                startActivity(intent);
                                getActivity().finish();
                            }
                        });
                        review.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                        review.setAdapter(adapter);
                    }
                }
            }else if(msg.arg1==200){
                PingjiaBean bean = (PingjiaBean) msg.obj;
                List<PingjiaBean.RetBean.ListBean> list = bean.getRet().getList();
                adapter1 = new TabPingjiaAdapter(getActivity(), list);
                review.setLayoutManager(new LinearLayoutManager(getActivity()));
                review.setAdapter(adapter1);
            }
        }
    };
    private List<Xiangqingbean.RetBean.ListBean> list;
    private List<Xiangqingbean.RetBean.ListBean.ChildListBean> childList;
    private RecyclerView review;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.tabfragment_item, null);
        unbinder = ButterKnife.bind(this, v);
        review = v.findViewById(R.id.xq_recyview);
        re = v.findViewById(R.id.tab_item_re);
        Bundle bundle = getArguments();
        String description = bundle.getString("description");
        String daoyan1 = bundle.getString("daoyan");
        String yanyuan1 = bundle.getString("yanyuan");
        String jieko = bundle.getString("jieko");
        String id=bundle.getString("dataId");
        Log.d("asa",id);
        String name=bundle.getString("name");
        if(name.equals("简介")) {
            re.setVisibility(View.VISIBLE);
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
                    Xiangqingbean xiangqingbean = gson.fromJson(string, Xiangqingbean.class);
                    if(xiangqingbean !=null){
                        Message message=Message.obtain();
                        message.arg1=100;
                        message.obj=xiangqingbean;
                        handler.sendMessage(message);
                    }
                }
            });
        }else if(name.equals("评价")){
            re.setVisibility(View.GONE);
            OkHttpClient client = new OkHttpClient();
            Request build = new Request.Builder()
                    .get()
                    .url("http://api.svipmovie.com/front/Commentary/getCommentList.do?mediaId="+id)
                    .build();
            Call call = client.newCall(build);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    Log.d("asa",string);
                    Gson gson=new Gson();
                    PingjiaBean pingjiaBean = gson.fromJson(string, PingjiaBean.class);
                    if(pingjiaBean !=null){
                        Message message=Message.obtain();
                        message.arg1=200;
                        message.obj=pingjiaBean;
                        handler.sendMessage(message);
                    }
                }
            });
        }
        yanyuan.setText("主演:" + yanyuan1);
        daoyan.setText("导演:" + daoyan1);
        jianshu.setText("简介:" + description);
        zhankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    flag=false;
                    zhankai.setText("收起");
                    jianshu.setVisibility(View.VISIBLE);
                }else{
                    flag=true;
                    zhankai.setText("展开");
                    jianshu.setVisibility(View.GONE);
                }
            }
        });
        return v;
    }

}
