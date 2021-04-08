package com.zhang.library.test.appclone.util;

import android.content.Context;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * 账户逻辑工具类
 *
 * @author ZhangXiaoMing 2021-04-06 9:46 星期二
 */
public class AccountUtils {

    private static final String TAG = AccountUtils.class.getSimpleName();

    private static final int FLAG_CREATE_PROFILE = 0x00000020; // 创建影子用户必要的flag
    private static final String NAME_CREATE_PROFILE = "UserNameTest";

    private static final String FLYME_PARALLEL_SPACE_USER_NAME = "FlymeParallelSpace";// 指定影子用户UserName
    private UserManager mUserManager;
    private Object mFlymeParallelSpaceUserInfo = null; // multi-open UserInfo

    private static int getUserIdFromUserInfo(Object userInfo) {
        int userId = -1;

        try {
            Field idField = userInfo.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            userId = (int) idField.get(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userId;
    }

    public static boolean startUser(int userId) {
        Object iActivityManager = null;
        try {
            iActivityManager = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault").invoke(null);

            return (boolean) iActivityManager.getClass().getMethod("startUserInBackground", int.class)
                    .invoke(iActivityManager, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String createProfile(Context context) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            return "";
        }

        UserManager userManager = (UserManager) context.getSystemService(Context.USER_SERVICE);

        UserHandle userHandle = UserHandle.getUserHandleForUid(0);

        Log.d(TAG, "userHandle = " + userHandle.toString());
        try {
            String methodName = "getIdentifier";
            int identifier = (int) userHandle.getClass().getMethod(methodName).invoke(userHandle);
            Log.d(TAG, "Identifier = " + identifier);
            Object mUserInfo = userManager.getClass().getMethod("createProfileForUser", String.class, int.class, int.class)
                    .invoke(userManager, NAME_CREATE_PROFILE, FLAG_CREATE_PROFILE, identifier);
            if (mUserInfo == null) {
                Log.d(TAG, "mUserInfo is null!");
                return null;
            }
            int userId = getUserIdFromUserInfo(mUserInfo);
            boolean isOk = startUser(userId);
            Log.d(TAG, "startUserInBackground() userId = " + userId + " | isOk = " + isOk);
            return isOk ? "" + userId : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
