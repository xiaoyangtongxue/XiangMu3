package model;


import java.util.Map;

import bean.MessageBean;
import http.RetrofitUtils;
import io.reactivex.Flowable;
import presenter.NewsPresenter;

/**
 * Created by zhangyueyi on 2017/12/15.
 */

public class NewsModlel implements IModel {
    private NewsPresenter presenter;
    public NewsModlel(NewsPresenter presenter){
        this.presenter=presenter;
    }
    @Override
    public void getData(Map<String, String> map) {
        Flowable<MessageBean>  flowable= RetrofitUtils.getInstance().getApiService().getNews(map);
        presenter.getNews(flowable);
    }
}
