package com.zhang.test.gridlayoutmanger;

import android.view.ViewGroup;

import com.zhang.library.adapter.BaseRecyclerAdapter;
import com.zhang.library.adapter.viewholder.base.BaseRecyclerViewHolder;

/**
 * @author ZhangXiaoMing 2021-07-20 17:21 星期二
 */
public class TestAdapter extends BaseRecyclerAdapter<Object> {

    private static final int VIEW_TYPE_1 = 1;

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 2)
            return VIEW_TYPE_1;
        return super.getItemViewType(position);
    }

    @Override
    protected BaseRecyclerViewHolder<Object> onCreateVHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindData(BaseRecyclerViewHolder<Object> viewHolder, Object data, int position) {

    }
}
