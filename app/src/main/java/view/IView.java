package view;


import bean.MessageBean;

/**
 * Created by zhangyueyi on 2017/12/15.
 */

public interface IView {
    void onSuccess(MessageBean messageBean);
    void onFailed(Exception e);
}
