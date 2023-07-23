package com.zhang.test.room;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.zhang.test.room.bean.UserDto;
import com.zhang.test.room.dao.UserDbDao;
import com.zhang.test.room.db.UserDb;

import java.util.List;

/**
 * @author ZhangXiaoMing 2023-07-22 21:06 周六
 */
public class DbViewModel extends AndroidViewModel {

    private static final String TAG = "DbViewModel";

    private UserDb mUserDb;


    public DbViewModel(@NonNull Application application) {
        super(application);

        initUserDb();
    }

    private final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE CtmAccount ADD COLUMN gender INTEGER DEFAULT 0");
        }
    };

    private final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE CtmAccount ADD COLUMN country TEXT");
        }
    };

    private final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table CtmAccount add column age integer not null default 0");
        }
    };


    private void initUserDb() {
        mUserDb = Room.databaseBuilder(getApplication(), UserDb.class, RoomConstant.DATABASE_NAME)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        Log.d(TAG, "onCreate: ");
                    }

                    @Override
                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                        Log.d(TAG, "onDestructiveMigration: ");
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        Log.d(TAG, "onOpen: ");
                    }
                })
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                .allowMainThreadQueries()
                .build();
    }


    public void insertUser(UserDto... users) {
        UserDbDao dao = mUserDb.getUserDao();
        dao.insertUserInfo(users);
    }

    public List<UserDto> loadAllUser() {
        UserDbDao dao = mUserDb.getUserDao();
        return dao.getAllUser();
    }

    public UserDto queryUserById(String id) {
        UserDbDao dao = mUserDb.getUserDao();
        return dao.getUserById(id);
    }

    public UserDto queryUserByCode(String code) {
        UserDbDao dao = mUserDb.getUserDao();
        return dao.getUserByAccountCode(code);
    }

    public int updateUser(UserDto user) {
        UserDbDao dao = mUserDb.getUserDao();
        return dao.updateUserInfo(user);
    }

    public void deleteUser(UserDto user) {
        UserDbDao dao = mUserDb.getUserDao();
        int result = dao.deleteUserInfo(user);
        Log.d(TAG, "deleteUser: result=" + result);
    }

}
