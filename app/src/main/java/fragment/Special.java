package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bwie.xiangmu3.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import activity.ZhuantiXqActivity;
import adapter.ZhuantiAdapter;
import bean.MessageBean;
import jieko.FoundJk;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangyueyi on 2017/12/15.
 */

public class Special extends Fragment {

    private RecyclerView re;
    private ZhuantiAdapter adapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MessageBean bean = (MessageBean) msg.obj;
            arrlist = new ArrayList<>();
            final List<MessageBean.RetBean.ListBean> list = bean.getRet().getList();
            for(int i=0;i<list.size();i++) {
                if(!list.get(i).getMoreURL().equals("")){
                    arrlist.add(list.get(i));
                }
                adapter = new ZhuantiAdapter(getActivity(), arrlist, new FoundJk() {
                    @Override
                    public void Chuanzhi(int postion) {
                        Intent intent = new Intent(getActivity(), ZhuantiXqActivity.class);
                        intent.putExtra("jieko", arrlist.get(postion).getMoreURL());
                        intent.putExtra("wenben",arrlist.get(postion).getTitle());
                        startActivity(intent);
                    }
                });
            }
            re.setLayoutManager(new GridLayoutManager(getActivity(),2));
            re.setAdapter(adapter);
        }
    };
    private List<MessageBean.RetBean.ListBean> arrlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=View.inflate(getActivity(), R.layout.special_item,null);
        re = v.findViewById(R.id.special_re);
        init();
        return v;
    }

    private void init() {
        OkHttpClient client = new OkHttpClient();
        Request build = new Request.Builder()
                .get()
                .url("http://api.svipmovie.com/front/homePageApi/homePage.do")
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
                MessageBean messageBean = gson.fromJson(string, MessageBean.class);
                if(messageBean !=null){
                    Message message= Message.obtain();
                    message.obj=messageBean;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
