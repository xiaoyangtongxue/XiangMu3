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

import bean.PingjiaBean;


/**
 * Created by zhangyueyi on 2017/12/30.
 */

public class TabPingjiaAdapter extends RecyclerView.Adapter<TabPingjiaAdapter.ViewHolder> {
  private Context context;
    private List<PingjiaBean.RetBean.ListBean> listBeen;
    public TabPingjiaAdapter(Context context, List<PingjiaBean.RetBean.ListBean> listBeen){
        this.context=context;
        this.listBeen=listBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.tab_item_pingjia,null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listBeen.get(position).getPhoneNumber());
        holder.shijian.setText(listBeen.get(position).getTime());
        holder.pingjia.setText(listBeen.get(position).getMsg());
        Uri uri=Uri.parse(listBeen.get(position).getUserPic());
        holder.img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView name;
        private final TextView pingjia;
        private final TextView shijian;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.pingjia_img);
            name = itemView.findViewById(R.id.pingjia_name);
            pingjia = itemView.findViewById(R.id.pingjia_pingjia);
            shijian = itemView.findViewById(R.id.pingjia_shijian);
        }
    }
}
