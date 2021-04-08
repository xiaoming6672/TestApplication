package com.zhang.test.applist;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhang.library.adapter.callback.OnItemClickCallback;
import com.zhang.library.utils.context.ContextUtils;
import com.zhang.library.utils.context.FileUtils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

                FileUtils.copyFile(sourceDir, cachePath, "base.apk");
            }
        });
        mRvContent.setAdapter(adapter);

        processQueryAppList();
        provisionManagedProfile();
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

    private static final int REQUEST_PROVISION_MANAGED_PROFILE = 1;

    private void provisionManagedProfile() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_PROVISION_MANAGED_PROFILE);
        intent.putExtra(DevicePolicyManager.EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME,
                mActivity.getPackageName());
        if (intent.resolveActivity(mActivity.getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_PROVISION_MANAGED_PROFILE);
            mActivity.finish();
        } else {
            Toast.makeText(mActivity, "Device provisioning is not enabled. Stopping.",
                    Toast.LENGTH_SHORT).show();
        }
    }

}

