package com.zhang.my.test.divideritemdecoration.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.sd.lib.utils.extend.FDrawable;
import com.sd.lib.views.utils.FDividerItemDecoration;
import com.zhang.library.utils.context.ResUtils;
import com.zhang.my.test.divideritemdecoration.R;
import com.zhang.my.test.divideritemdecoration.adapter.RecyclerAdapter;
import com.zhang.my.test.divideritemdecoration.view.EqualDividerItemDecoration;
import com.zhang.my.test.divideritemdecoration.view.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final int TYPE_CUSTOM = 0;
    private static final int TYPE_F = 1;
    private static final int TYPE_ANDROIDX = 2;
    private static final int TYPE_CUSTOM_EQUAL = 3;

    private RecyclerView rv_content;

    private int mType = TYPE_CUSTOM;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        rv_content = findViewById(R.id.rv_content);

        ((RadioGroup) findViewById(R.id.view_radio_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_custom:
                    default:
                        mType = TYPE_CUSTOM;
                        break;
                    case R.id.rb_custom_equal:
                        mType = TYPE_CUSTOM_EQUAL;
                        break;
                    case R.id.rb_fdidiver:
                        mType = TYPE_F;
                        break;
                    case R.id.rb_androidx:
                        mType = TYPE_ANDROIDX;
                        break;
                }
            }
        });

//        rv_content.setLayoutManager(getLayoutManager());

//        addMyDivider(rv_content);
//        addFDivider(view);
//        addAndroidxDivider(view);

        adapter = new RecyclerAdapter();
        rv_content.setAdapter(adapter);

        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(new Object());
        }
        adapter.getDataHolder().setDataList(list);
    }

    private static final int DIVIDER_SIZE = ResUtils.dp2px(10);

    public void onClickLayoutManagerButton(View view) {
        RecyclerView.LayoutManager manager;
        switch (view.getId()) {
            case R.id.btn_linear_vertical:
            default:
                manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
                adapter.isVertical = true;
                break;
            case R.id.btn_linear_horizontal:
                manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
                adapter.isVertical = false;
                break;
            case R.id.btn_grid_vertical:
                manager = new GridLayoutManager(this, RecyclerAdapter.SPAN_COUNT, RecyclerView.VERTICAL, false);
                adapter.isVertical = true;
                break;
            case R.id.btn_grid_horizontal:
                manager = new GridLayoutManager(this, RecyclerAdapter.SPAN_COUNT, RecyclerView.HORIZONTAL, false);
                adapter.isVertical = false;
                break;
        }
        rv_content.setLayoutManager(manager);
        adapter.notifyDataSetChanged();
    }

    public void onClickDecorationButton(View view) {
        switch (view.getId()) {
            case R.id.btn_clear_all:
                while (rv_content.getItemDecorationCount() > 0) {
                    rv_content.removeItemDecorationAt(0);
                }
                break;
            case R.id.btn_add_vertical:
                rv_content.addItemDecoration(getDecorationVertical());
                break;
            case R.id.btn_reduce_vertical:
                rv_content.removeItemDecoration(getDecorationVertical());
                break;
            case R.id.btn_add_horizontal:
                rv_content.addItemDecoration(getDecorationHorizontal());
                break;
            case R.id.btn_reduce_horizontal:
                rv_content.removeItemDecoration(getDecorationHorizontal());
                break;
        }
    }

    private RecyclerView.ItemDecoration getDecorationVertical() {
        RecyclerView.ItemDecoration decoration;
        switch (mType) {
            case TYPE_ANDROIDX:
                if (mAndroidxV == null) {
                    mAndroidxV = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                    mAndroidxV.setDrawable(getResources().getDrawable(R.drawable.divider_line));
                }
                decoration = mAndroidxV;
                break;
            case TYPE_F:
                if (mFV == null) {
                    mFV = new FDividerItemDecoration(FDividerItemDecoration.HORIZONTAL);
                    mFV.setDrawable(new FDrawable().color(Color.YELLOW).size(DIVIDER_SIZE));
                }
                decoration = mFV;
                break;
            case TYPE_CUSTOM_EQUAL:
                if (mEqualV == null) {
                    Drawable drawable = ResUtils.getResources().getDrawable(R.mipmap.guizi_jiazi_hong);
                    mEqualV = new EqualDividerItemDecoration(RecyclerView.VERTICAL, drawable);
                    mEqualV.setSize(drawable.getIntrinsicHeight());
//                    mEqualV = new EqualDividerItemDecoration(RecyclerView.VERTICAL, DIVIDER_SIZE, Color.RED);
                }
                decoration = mEqualV;
                break;
            case TYPE_CUSTOM:
            default:
                if (mMyV == null) {
                    mMyV = new MyDividerItemDecoration(RecyclerView.VERTICAL, DIVIDER_SIZE, Color.GRAY)
//                .setPaddingLeft(ResUtils.dp2px(3))
//                .setPaddingRight(ResUtils.dp2px(15))
                    ;
                }
                decoration = mMyV;
                break;
        }
        return decoration;
    }

    private RecyclerView.ItemDecoration getDecorationHorizontal() {
        RecyclerView.ItemDecoration decoration;
        switch (mType) {
            case TYPE_ANDROIDX:
                if (mAndroidxH == null) {
                    mAndroidxH = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
                    mAndroidxH.setDrawable(getResources().getDrawable(R.drawable.divider_line));
                }
                decoration = mAndroidxH;
                break;
            case TYPE_F:
                if (mFH == null) {
                    mFH = new FDividerItemDecoration(FDividerItemDecoration.VERTICAL);
                    mFH.setDrawable(new FDrawable().color(Color.GRAY).size(DIVIDER_SIZE));
                }
                decoration = mFH;
                break;
            case TYPE_CUSTOM_EQUAL:
                if (mEqualH == null) {
//                    mEqualH = new EqualDividerItemDecoration(RecyclerView.HORIZONTAL, DIVIDER_SIZE, Color.RED);
                    mEqualH = new EqualDividerItemDecoration(RecyclerView.HORIZONTAL, ResUtils.getResources().getDrawable(R.mipmap.guizi_jiazi_hong));
                }
                decoration = mEqualH;
                break;
            case TYPE_CUSTOM:
            default:
                if (mMyH == null) {
                    mMyH = new MyDividerItemDecoration(RecyclerView.HORIZONTAL, DIVIDER_SIZE, Color.GRAY)
//                .setPadding(ResUtils.dp2px(10))
//                .setPaddingTop(ResUtils.dp2px(23))
//                .setPaddingBottom(ResUtils.dp2px(13))
                    ;
                }
                decoration = mMyH;
                break;
        }
        return decoration;
    }

    private MyDividerItemDecoration mMyV;
    private MyDividerItemDecoration mMyH;
    private EqualDividerItemDecoration mEqualV;
    private EqualDividerItemDecoration mEqualH;
    private FDividerItemDecoration mFV;
    private FDividerItemDecoration mFH;
    private DividerItemDecoration mAndroidxV;
    private DividerItemDecoration mAndroidxH;
}