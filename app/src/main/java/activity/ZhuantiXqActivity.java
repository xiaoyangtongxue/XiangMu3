package activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bwie.xiangmu3.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import adapter.ZhuantiXqAdapter;
import adapter.ZhuantiXqBean;
import jieko.FoundJk;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZhuantiXqActivity extends AppCompatActivity {
    private ZhuantiXqAdapter adapter;
    private String jieko;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ZhuantiXqBean bean = (ZhuantiXqBean) msg.obj;
            final List<ZhuantiXqBean.RetBean.ListBean> list = bean.getRet().getList();
            adapter=new ZhuantiXqAdapter(ZhuantiXqActivity.this, list, new FoundJk() {
                @Override
                public void Chuanzhi(int postion) {
                    Intent intent = new Intent(ZhuantiXqActivity.this, XiangQingActivity.class);
                    intent.putExtra("title",list.get(postion).getTitle());
                    intent.putExtra("description",list.get(postion).getDescription());
                    intent.putExtra("dataId",list.get(postion).getDataId());
                    intent.putExtra("jieko",list.get(postion).getLoadURL());
                    startActivity(intent);
                }
            });
            re.setLayoutManager(new GridLayoutManager(ZhuantiXqActivity.this,3));
            re.setAdapter(adapter);
        }
    };
    private RecyclerView re;
    private String wenben;
    private ImageView back;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanti);

        back = (ImageView) findViewById(R.id.zhuantixq_back);
        textView = (TextView) findViewById(R.id.zhuantixq_biaoti_text);
        Intent intent = getIntent();
        jieko = intent.getStringExtra("jieko");
        wenben = intent.getStringExtra("wenben");

        textView.setText(wenben);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Log.d("aaa","jieko"+jieko);
        re = (RecyclerView) findViewById(R.id.zhuantixq_re);
        init();
    }
    private void init() {
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
                ZhuantiXqBean messageBean = gson.fromJson(string, ZhuantiXqBean.class);
                if(messageBean !=null){
                    Message message= Message.obtain();
                    message.obj=messageBean;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
