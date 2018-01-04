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

import bean.Xiangqingbean;
import jieko.FoundJk;


/**
 * Created by zhangyueyi on 2017/12/30.
 */

public class TabJianshuAdapter extends RecyclerView.Adapter<TabJianshuAdapter.ViewHolder> {
  private Context context;
    private List<Xiangqingbean.RetBean.ListBean.ChildListBean> listBeen;
    private FoundJk foundJk;
    public TabJianshuAdapter(Context context,List<Xiangqingbean.RetBean.ListBean.ChildListBean> listBeen,FoundJk jieko){
        this.context=context;
        this.listBeen=listBeen;
        this.foundJk=jieko;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.tab_item_jianshu,null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.text.setText(listBeen.get(position).getTitle());
        Glide.with(context).load(listBeen.get(position).getPic()).into(holder.img);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foundJk.Chuanzhi(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView text;
        public View view;
        private View getView(){return view;}
        public ViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
            img = itemView.findViewById(R.id.tab_jianshu_img);
            text = itemView.findViewById(R.id.tab_jianshu_text);
        }
    }
}
