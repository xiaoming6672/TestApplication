package com.zhang.test.applist;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRvContent;

    private AppListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvContent = findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new GridLayoutManager(this, 5));

        adapter = new AppListAdapter();
        mRvContent.setAdapter(adapter);

        processQueryAppList();
    }

    @Override
    public void onClick(View v) {
    }

    private void processQueryAppList() {
        PackageManager manager = getPackageManager();

        List<PackageInfo> list = manager.getInstalledPackages(PackageManager.GET_META_DATA);
        Iterator<PackageInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            //判断是否是系统应用

            PackageInfo info = iterator.next();
            boolean isSysApp = (info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1;
            boolean isSysUpd = (info.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1;

            if (isSysApp || isSysUpd) {
                iterator.remove();
            }
        }

        adapter.getDataHolder().setDataList(list);

//        List<ApplicationInfo> list = manager.getInstalledApplications(PackageManager.GET_META_DATA);
//        for (ApplicationInfo info : list) {
//            builder.append(info.packageName).append("\n");
//        }

    }
}
