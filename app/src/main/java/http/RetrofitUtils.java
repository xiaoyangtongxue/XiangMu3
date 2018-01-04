package http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangyueyi on 2017/12/15.
 */

public class RetrofitUtils {
    private static volatile RetrofitUtils instance;
    private ApiService apiService;
    private RetrofitUtils(){
        Retrofit build = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://api.svipmovie.com/")
                .build();
        apiService = build.create(ApiService.class);
    }
    public static RetrofitUtils getInstance(){
        if(instance==null){
            synchronized (RetrofitUtils.class){
                if(null==instance){
                    instance=new RetrofitUtils();
                }
            }
        }
        return instance;
    }
    public ApiService getApiService(){
        return apiService;
    }
}
