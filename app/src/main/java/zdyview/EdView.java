package zdyview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bwie.xiangmu3.R;


/**
 * Created by zhangyueyi on 2017/12/15.
 */

public class EdView extends LinearLayout {
    public EdView(Context context) {
        this(context,null);
    }

    public EdView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EdView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        View v=View.inflate(context, R.layout.zdy_ed_item,null);
        ImageView ed_img = v.findViewById(R.id.ed_img);
        TextView ed_text = v.findViewById(R.id.ed_text);

    }
}
