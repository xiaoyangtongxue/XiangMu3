package adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bwie.xiangmu3.R;

import java.util.List;

import bean.MessageBean;
import jieko.FoundJk;

/**
 * Created by zhangyueyi on 2017/12/29.
 */

public class FoundXqAdapter extends RecyclerView.Adapter<FoundXqAdapter.ViewHolder>{
    private Context context;
    private List<MessageBean.RetBean.ListBean.ChildListBean> list;
    private FoundJk foundJk;
    public FoundXqAdapter(Context context, List<MessageBean.RetBean.ListBean.ChildListBean> list, FoundJk jk){
        this.context=context;
        this.list=list;
        this.foundJk=jk;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.found_item_item,null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getTitle());
        Uri uri=Uri.parse(list.get(position).getPic());
        holder.img.setImageURI(uri);
        holder.getV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foundJk.Chuanzhi(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView textView;
        private View v;
        public View getV(){return v;}
        public ViewHolder(View itemView) {
            super(itemView);
            this.v=itemView;
            img = itemView.findViewById(R.id.found_item_img);
            textView = itemView.findViewById(R.id.found_item_text);
        }
    }
}
