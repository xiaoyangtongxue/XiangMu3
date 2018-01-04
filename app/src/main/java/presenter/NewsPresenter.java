package presenter;

import android.util.Log;

import java.util.Map;

import bean.MessageBean;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import model.NewsModlel;
import view.IView;

/**
 * Created by zhangyueyi on 2017/12/15.
 */

public class NewsPresenter implements BasePresenter{
    private IView iv;
    private DisposableSubscriber<MessageBean> subscriber;

    public void attachView(IView iv){
        this.iv=iv;
    }
    public void detachView(){
        if(iv!=null){
            iv=null;
        }
        if(subscriber!=null){
            if(!subscriber.isDisposed()){
                subscriber.dispose();
            }
        }
    }
    @Override
    public void getData(Map<String, String> map) {
        NewsModlel modlel = new NewsModlel(this);
        modlel.getData(map);
    }

    public void getNews(Flowable<MessageBean> flowable){
        subscriber = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean>() {
                    @Override
                    public void onNext(MessageBean messageBean) {
                        Log.d("aaa",messageBean.toString());
                        iv.onSuccess(messageBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
