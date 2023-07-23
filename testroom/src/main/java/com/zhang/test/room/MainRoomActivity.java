package com.zhang.test.room;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.zhang.test.room.bean.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainRoomActivity extends AppCompatActivity {

    private static final String TAG = "MainRoomActivity";

    private DbViewModel mDbVM;
    private List<UserDto> mUserList;

    private UserDto mCurrent;

    private EditText etId;
    private EditText etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_room);

        etId = findViewById(R.id.et_id);
        etCode = findViewById(R.id.et_code);

        mDbVM = new ViewModelProvider(this).get(DbViewModel.class);
        mUserList = new ArrayList<>();
    }

    public void onClickCreate(View view) {
        UserDto user = UserDto.generateUserInfo();
        Log.i(TAG, "onClickCreate: user=" + user.toString());
        mUserList.add(user);
    }

    public void onClickInsert(View view) {
        if (mUserList.isEmpty()) {
            Toast.makeText(this, "Create user data first!", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            UserDto[] users = new UserDto[mUserList.size()];
            mUserList.toArray(users);

            mDbVM.insertUser(users);

            mUserList.clear();
        }).start();
    }

    public void onClickQueryAll(View view) {
        new Thread(() -> {
            List<UserDto> list = mDbVM.loadAllUser();
            Log.d(TAG, "onClickQueryAll: list=" + list.toString());
        }).start();

    }

    public void onClickQueryById(View view) {
        String id = etId.getText().toString();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "Input id first!", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            mCurrent = mDbVM.queryUserById(id);
            Log.d(TAG, "onClickQueryById: mCurrent=" + (mCurrent == null ? "Null" : mCurrent.toString()));
        }).start();
    }

    public void onClickQueryByCode(View view) {
        String code = etCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "Input code first!", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            mCurrent = mDbVM.queryUserByCode(code);
            Log.d(TAG, "onClickQueryByCode: mCurrent=" + (mCurrent == null ? "Null" : mCurrent.toString()));
        }).start();
    }

    public void onClickUpdate(View view) {
        if (mCurrent == null) {
            Toast.makeText(this, "Current user data is Null", Toast.LENGTH_SHORT).show();
            return;
        }

        String code = mCurrent.getCode();
        String[] array = code.split("-");
        mCurrent.setName(array[new Random().nextInt(array.length)]);

        mCurrent.setGender(new Random().nextInt(2) + 1);
        mCurrent.setCountry("CN");

        int result = mDbVM.updateUser(mCurrent);
        Log.i(TAG, "onClickUpdate: result=" + result);
    }

    public void onClickDelete(View view) {
        if (mCurrent == null) {
            Toast.makeText(this, "Current user data is Null", Toast.LENGTH_SHORT).show();
            return;
        }

        mDbVM.deleteUser(mCurrent);
    }
}