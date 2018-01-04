package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bwie.xiangmu3.R;

import java.util.List;

import bean.MessageBean;
import jieko.FoundJk;


/**
 * Created by zhangyueyi on 2018/1/1.
 */

public class ZhuantiAdapter extends RecyclerView.Adapter<ZhuantiAdapter.ViewHolder>{
    private Context context;
    private List<MessageBean.RetBean.ListBean> list;
    private FoundJk jieko;
    private int i;

    public ZhuantiAdapter(Context context, List<MessageBean.RetBean.ListBean> list, FoundJk jieko){
        this.context=context;
        this.list=list;
        this.jieko=jieko;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.zhuanti_item,null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(!list.get(position).getMoreURL().equals("")) {
            holder.name.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getChildList().get(0).getPic()).into(holder.img);
            holder.getV().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jieko.Chuanzhi(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView name;
        private View v;
        public View getV(){return v;}
        public ViewHolder(View itemView) {
            super(itemView);
            this.v=itemView;
            img = itemView.findViewById(R.id.zhuanti_img);
            name = itemView.findViewById(R.id.zhuanti_text);
        }
    }
}

