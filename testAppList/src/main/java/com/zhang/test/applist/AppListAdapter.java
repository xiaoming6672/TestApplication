package com.zhang.test.applist;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.zhang.library.adapter.BaseRecyclerAdapter;
import com.zhang.library.adapter.viewholder.base.BaseRecyclerViewHolder;

/**
 * @author ZhangXiaoMing 2021-04-04 11:22 星期日
 */
public class AppListAdapter extends BaseRecyclerAdapter<PackageInfo> {
    @Override
    protected BaseRecyclerViewHolder<PackageInfo> onCreateVHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    protected void onBindData(BaseRecyclerViewHolder<PackageInfo> viewHolder, PackageInfo data, int position) {

    }

    private static final class ViewHolder extends BaseRecyclerViewHolder<PackageInfo> {
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
        public void onBindData(PackageInfo item, int position) {
            PackageManager manager = itemView.getContext().getPackageManager();

            ApplicationInfo info = item.applicationInfo;

            Drawable drawable = info.loadIcon(manager);
            mIvIcon.setImageDrawable(drawable);

            CharSequence label = manager.getApplicationLabel(info);
            mTvName.setText(label);
//            mTvName.setText(info.loadLabel(manager));
        }
    }

}
