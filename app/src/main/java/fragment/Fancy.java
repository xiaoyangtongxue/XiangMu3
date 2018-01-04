package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bwie.xiangmu3.R;
import com.google.gson.Gson;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import activity.XiangQingActivity;
import adapter.FaXianAdapter;
import bean.FaxianBean;
import jieko.FoundJk;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangyueyi on 2017/12/15.
 */

public class Fancy extends Fragment{
    private FaXianAdapter adapter;
    private Handler handler=new Handler(){
        private List<FaxianBean.RetBean.ListBean> list;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

                FaxianBean bean = (FaxianBean) msg.obj;
            list = bean.getRet().getList();
            if (list != null) {
                    if (list != null) {
                        recy.setLayoutManager(new OverLayCardLayoutManager());
                        adapter = new FaXianAdapter(getActivity(), list, new FoundJk() {
                            @Override
                            public void Chuanzhi(int postion) {
                                Intent intent = new Intent(getActivity(), XiangQingActivity.class);
                                intent.putExtra("title",list.get(postion).getTitle());
                                intent.putExtra("description",list.get(postion).getDescription());
                                intent.putExtra("dataId",list.get(postion).getDataId());
                                intent.putExtra("jieko",list.get(postion).getLoadURL());
                                startActivity(intent);
                            }
                        });
                        CardConfig.initConfig(getActivity());
                        ItemTouchHelper.Callback callback=new RenRenCallback(recy,adapter, list);
                        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
                        itemTouchHelper.attachToRecyclerView(recy);
                        recy.setAdapter(adapter);
                    }
                }
            }
    };
    private int shu=1;
    private List<FaxianBean.RetBean.ListBean> list;
    private RecyclerView recy;
    private Button huan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=View.inflate(getActivity(), R.layout.fancy_item,null);

        recy = v.findViewById(R.id.fx_re);
        huan = v.findViewById(R.id.fx_button);
        init();
        huan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int min=1;
                int max=108;
                Random random=new Random();
                shu = random.nextInt(max)%(max-min+1) + min;
                init();
                adapter.notifyDataSetChanged();
            }
        });
        return v;
    }

    private void init() {
        OkHttpClient client = new OkHttpClient();
        Request build = new Request.Builder()
                .get()
                .url("http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum="+shu)
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
                FaxianBean faxianBean = gson.fromJson(string, FaxianBean.class);
                if(faxianBean !=null){
                    Message message=Message.obtain();
                    message.obj=faxianBean;
                    handler.sendMessage(message);
                }
            }
        });
    }

}
