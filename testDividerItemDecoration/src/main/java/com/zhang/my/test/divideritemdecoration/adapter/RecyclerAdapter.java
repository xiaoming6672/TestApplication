package com.zhang.my.test.divideritemdecoration.adapter;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhang.library.adapter.BaseRecyclerAdapter;
import com.zhang.library.adapter.viewholder.base.BaseRecyclerViewHolder;
import com.zhang.library.utils.context.ViewUtils;
import com.zhang.my.test.divideritemdecoration.R;

/**
 * @author ZhangXiaoMing 2020-11-12 10:58 星期四
 */
public class RecyclerAdapter extends BaseRecyclerAdapter<Object> {

    public static final int SPAN_COUNT = 2;

    @Override
    protected BaseRecyclerViewHolder<Object> onCreateVHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_recyclerview);
    }

    @Override
    protected void onBindData(BaseRecyclerViewHolder<Object> viewHolder, Object data, int position) {
    }

    private static class ViewHolder extends BaseRecyclerViewHolder<Object> {

        private TextView tv_text;

        private ViewHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        public void onInit() {
            tv_text = findViewById(R.id.tv_text);
        }

        @Override
        public void onBindData(Object item, int position) {
            tv_text.setText(String.valueOf(position + 1));

//            int result = position % SPAN_COUNT;
//            if (result == 0) {
//                itemView.setBackgroundColor(Color.RED);
//            } else if (result == 1) {
//                itemView.setBackgroundColor(Color.BLUE);
//            } else if (result == 2) {
//                itemView.setBackgroundColor(Color.GRAY);
//            } else {
//                itemView.setBackgroundColor(Color.GREEN);
//            }
        }
    }

}
