package http;


import java.util.Map;

import bean.MessageBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhangyueyi on 2017/12/15.
 */
//http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=840ddae38ed346a197a76b46b448067e
public interface ApiService {
    @GET("front/homePageApi/homePage.do")
    Flowable<MessageBean> getNews(@QueryMap Map<String, String> map);

}
