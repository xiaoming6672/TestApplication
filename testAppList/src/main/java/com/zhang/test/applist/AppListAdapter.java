package com.zhang.test.applist;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.library.adapter.BaseRecyclerAdapter;
import com.zhang.library.adapter.viewholder.base.BaseRecyclerViewHolder;

import androidx.annotation.NonNull;

/**
 * @author ZhangXiaoMing 2021-04-04 11:22 星期日
 */
public class AppListAdapter extends BaseRecyclerAdapter<ApplicationInfo> {
    @Override
    protected BaseRecyclerViewHolder<ApplicationInfo> onCreateVHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    protected void onBindData(BaseRecyclerViewHolder<ApplicationInfo> viewHolder, ApplicationInfo data, int position) {

    }

    private static final class ViewHolder extends BaseRecyclerViewHolder<ApplicationInfo> {
        private ImageView mIvIcon;
        private TextView mTvName;


        public ViewHolder(@NonNull ViewGroup parent) {
            super(parent, R.layout.item_app_list);
        }

        @Override
        public void onInit() {
            mIvIcon = findViewById(R.id.iv_icon);
            mTvName = findViewById(R.id.tv_name);
        }

        @Override
        public void onBindData(ApplicationInfo item, int position) {
            PackageManager manager = itemView.getContext().getPackageManager();

            Drawable drawable = item.loadIcon(manager);
            mIvIcon.setImageDrawable(drawable);

            CharSequence label = manager.getApplicationLabel(item);
            mTvName.setText(label);
//            mTvName.setText(info.loadLabel(manager));
        }
    }

}
