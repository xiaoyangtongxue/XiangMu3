package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bwie.xiangmu3.R;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import activity.XiangQingActivity;
import activitylei.ImgApp;
import adapter.FoundXqAdapter;
import bean.MessageBean;
import jieko.FoundJk;
import presenter.NewsPresenter;
import view.IView;

/**
 * Created by zhangyueyi on 2017/12/15.
 */

public class Found extends Fragment implements IView {
    private NewsPresenter presenter;
    private Banner ban;
    private List<MessageBean.RetBean.ListBean> list;
    private List<MessageBean.RetBean.ListBean.ChildListBean> childList;
    private RecyclerView review;
    private int zhi=4;
    private PullToRefreshLayout pull;
    private FoundXqAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=View.inflate(getActivity(), R.layout.found_item,null);

        ban = v.findViewById(R.id.ban);
        review = v.findViewById(R.id.found_recyv);
        pull = v.findViewById(R.id.pull);
        Map<String,String> map=new HashMap<>();
        map.put("mediaId","840ddae38ed346a197a76b46b448067e");
        //map.put("http://api.svipmovie.com/front/homePageApi/homePage.do");
        presenter=new NewsPresenter();
        presenter.attachView(this);
        presenter.getData(map);
        pull.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shuaxing();
                        pull.finishRefresh();
                    }
                },2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shuaxing();
                        pull.finishLoadMore();
                    }
                },2000);
            }
        });
        return v;
    }

    @Override
    public void onSuccess(MessageBean messageBean) {

        list = messageBean.getRet().getList();
            List<String> imgs=new ArrayList<>();
            childList=list.get(0).getChildList();
            for(int j=0;j<childList.size();j++){
                imgs.add(childList.get(j).getPic());
            }
            ban.setImageLoader(new ImgApp());//引用ImgApp,加载里面的东西
            ban.setImages(imgs);
            ban.isAutoPlay(true);
            ban.setDelayTime(2000);
            ban.start();
        shuaxing();
    }

    private void shuaxing() {
        final List<MessageBean.RetBean.ListBean.ChildListBean> childList = list.get(zhi).getChildList();
        Log.d("aaa","详情数据"+childList.toString());
        if(childList!=null) {
            adapter = new FoundXqAdapter(getActivity(), childList, new FoundJk() {
                @Override
                public void Chuanzhi(int postion) {
                    Intent intent = new Intent(getActivity(), XiangQingActivity.class);
                    intent.putExtra("title",childList.get(postion).getTitle());
                    intent.putExtra("description",childList.get(postion).getDescription());
                    intent.putExtra("dataId",childList.get(postion).getDataId());
                    intent.putExtra("jieko",childList.get(postion).getLoadURL());
                    startActivity(intent);
                }
            });
            review.setLayoutManager(new LinearLayoutManager(getActivity()));
            review.setAdapter(adapter);
        }
    }


    @Override
    public void onFailed(Exception e) {

    }
}
