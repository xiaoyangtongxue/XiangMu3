package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bwie.xiangmu3.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bean.FaxianBean;
import jieko.FoundJk;

/**
 * Created by zhangyueyi on 2018/1/1.
 */

public class FaXianAdapter extends RecyclerView.Adapter<FaXianAdapter.ViewHolder>{
    private Context context;
    private List<FaxianBean.RetBean.ListBean> list;
    private FoundJk jieko;
    public FaXianAdapter(Context context, List<FaxianBean.RetBean.ListBean> list, FoundJk jieko){
        this.context=context;
        this.list=list;
        this.jieko=jieko;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("aaa","sasasa"+list.toString());
        View v=View.inflate(context, R.layout.fx__item,null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Uri uri=Uri.parse(list.get(position).getPic());
        holder.img.setImageURI(uri);
        holder.name.setText(list.get(position).getTitle());
        holder.title.setText("      "+list.get(position).getDescription());
        holder.getV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jieko.Chuanzhi(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView title;
        private View v;
        public View getV(){return v;}
        public ViewHolder(View itemView) {
            super(itemView);
            this.v=itemView;
            img = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.item_name);
            title = itemView.findViewById(R.id.item_title);
        }
    }
}

