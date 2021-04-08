package com.zhang.test.applist;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.morgoo.droidplugin.pm.PluginManager;
import com.morgoo.helper.compat.PackageManagerCompat;
import com.zhang.library.adapter.callback.OnItemClickCallback;
import com.zhang.library.utils.CollectionUtils;
import com.zhang.library.utils.context.ContextUtils;
import com.zhang.library.utils.context.FileUtils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Activity mActivity;

    private RecyclerView mRvContent;

    private AppListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextUtils.set(this);
        mActivity = this;

        setContentView(R.layout.activity_main);

        mRvContent = findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new GridLayoutManager(this, 5));

        adapter = new AppListAdapter();
        adapter.getCallbackHolder().addOnItemClickCallback(new OnItemClickCallback<ApplicationInfo>() {
            @Override
            public void onItemClick(View itemView, ApplicationInfo data, int position) {
                String sourceDir = data.sourceDir;
                String publicSourceDir = data.publicSourceDir;
                String content = "sourceDir = [" + sourceDir + "]\n"
                        + "publicSourceDir = [" + publicSourceDir + "]";
                Log.d(TAG, content);

                File file = new File(sourceDir);
                Log.d(TAG, "sourceDir file is exist ? " + file.exists());

                String cachePath = FileUtils.getCachePath();
                Log.d(TAG, "cachePath = " + cachePath);

                String targetFileName = data.packageName + ".apk";
                FileUtils.copyFile(sourceDir, cachePath, targetFileName);

                String targetFilePath = cachePath + targetFileName;

                try {
                    List<ApplicationInfo> list = PluginManager.getInstance().getInstalledApplications(PackageManagerCompat.INSTALL_REPLACE_EXISTING);
                    if (CollectionUtils.isEmpty(list)) {
                        Log.i(TAG, "installed applications is empty");
                    } else {
                        StringBuilder builder = new StringBuilder();
                        for (ApplicationInfo info : list) {
                            builder.append(info.packageName).append("\n");
                        }
                        Log.i(TAG, "installed applications list :" + builder.toString());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    int result = PluginManager.getInstance().deletePackage(data.packageName, 0);
                    Log.w(TAG, "delete result = " + result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                try {
                    int result = PluginManager.getInstance().installPackage(targetFilePath, PackageManagerCompat.INSTALL_REPLACE_EXISTING);
                    Log.d(TAG, "install result = " + result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        mRvContent.setAdapter(adapter);

        processQueryAppList();
    }

    @Override
    public void onClick(View v) {
    }

    private void processQueryAppList() {
        PackageManager manager = getPackageManager();

        List<ApplicationInfo> list = manager.getInstalledApplications(PackageManager.GET_META_DATA);
        Iterator<ApplicationInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            //判断是否是系统应用

            ApplicationInfo info = iterator.next();
            boolean isSysApp = (info.flags & ApplicationInfo.FLAG_SYSTEM) == 1;
            boolean isSysUpd = (info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1;

            if (isSysApp || isSysUpd) {
                iterator.remove();
            }
        }

        adapter.getDataHolder().setDataList(list);
    }

}

