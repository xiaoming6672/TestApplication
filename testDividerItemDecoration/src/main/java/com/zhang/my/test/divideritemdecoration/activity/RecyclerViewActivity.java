package com.zhang.my.test.divideritemdecoration.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.sd.lib.utils.extend.FDrawable;
import com.sd.lib.views.utils.FDividerItemDecoration;
import com.zhang.library.utils.context.ResUtils;
import com.zhang.my.test.divideritemdecoration.R;
import com.zhang.my.test.divideritemdecoration.adapter.RecyclerAdapter;
import com.zhang.my.test.divideritemdecoration.view.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, RecyclerViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView view = findViewById(R.id.rv_content);

        view.setLayoutManager(getLayoutManager());

//        addMyDivider(view);
//        addFDivider(view);
        addAndroidxDivider(view);

        RecyclerAdapter adapter = new RecyclerAdapter();
        view.setAdapter(adapter);

        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(new Object());
        }
        adapter.getDataHolder().setDataList(list);
    }

    private RecyclerView.LayoutManager getLayoutManager() {
//        return  new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        return new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        return new GridLayoutManager(this, RecyclerAdapter.SPAN_COUNT, RecyclerView.VERTICAL, false);
//        return new GridLayoutManager(this, RecyclerAdapter.SPAN_COUNT, RecyclerView.HORIZONTAL, false);
    }

    private static final int DIVIDER_SIZE = ResUtils.dp2px(21);

    private void addMyDivider(RecyclerView view) {
        view.addItemDecoration(new MyDividerItemDecoration(RecyclerView.VERTICAL, ResUtils.dp2px(11), Color.MAGENTA));
        view.addItemDecoration(new MyDividerItemDecoration(RecyclerView.VERTICAL, ResUtils.dp2px(11), Color.MAGENTA));
        view.addItemDecoration(new MyDividerItemDecoration(RecyclerView.HORIZONTAL, DIVIDER_SIZE, Color.CYAN));
    }

    private void addFDivider(RecyclerView view) {
        FDividerItemDecoration vertical = new FDividerItemDecoration(FDividerItemDecoration.VERTICAL);
        vertical.setDrawable(new FDrawable().color(Color.MAGENTA).size(DIVIDER_SIZE));
        view.addItemDecoration(vertical);

        FDividerItemDecoration horizontal = new FDividerItemDecoration(FDividerItemDecoration.HORIZONTAL);
        horizontal.setDrawable(new FDrawable().color(Color.YELLOW).size(DIVIDER_SIZE));
        view.addItemDecoration(horizontal);
    }

    private void addAndroidxDivider(RecyclerView view) {
        {
            DividerItemDecoration vertical = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            vertical.setDrawable(getResources().getDrawable(R.drawable.divider_line));
            view.addItemDecoration(vertical);
        }
        {
            DividerItemDecoration vertical = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            vertical.setDrawable(getResources().getDrawable(R.drawable.divider_line));
            view.addItemDecoration(vertical);
        }

        DividerItemDecoration horizontal = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        horizontal.setDrawable(getResources().getDrawable(R.drawable.divider_line));
        view.addItemDecoration(horizontal);
    }
}