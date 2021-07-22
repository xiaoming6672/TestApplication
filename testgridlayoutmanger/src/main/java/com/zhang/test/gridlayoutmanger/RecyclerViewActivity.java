package com.zhang.test.gridlayoutmanger;

import android.os.Bundle;
import android.view.ViewGroup;

import com.zhang.library.adapter.BaseRecyclerAdapter;
import com.zhang.library.adapter.viewholder.base.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView rvContent = findViewById(R.id.rv_content);

        TestAdapter adapter = new TestAdapter();
        rvContent.setAdapter(adapter);

        GridLayoutManager manager = (GridLayoutManager) rvContent.getLayoutManager();
        GridLayoutManager.SpanSizeLookup oldSizeLookup = manager.getSpanSizeLookup();

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 3 == 2)
                    return 2;

//                if (position % 6 == 4 || position % 6 == 5)
//                    return 2;

                return oldSizeLookup.getSpanSize(position);
            }
        });

        List<Object> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add(new Object());
        }
        adapter.getDataHolder().setDataList(dataList);
    }

    private static final class TestAdapter extends BaseRecyclerAdapter<Object> {

        private static final int VIEW_TYPE_1 = 1;

        @Override
        public int getItemViewType(int position) {
            if (position % 3 == 2)
                return VIEW_TYPE_1;

//            if (position % 5 == 4 || position % 5 == 3)
//                return VIEW_TYPE_1;

            return super.getItemViewType(position);
        }

        @Override
        protected BaseRecyclerViewHolder<Object> onCreateVHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_1)
                return new TestViewHolder1(parent);
            return new TestViewHolder2(parent);
        }

        @Override
        protected void onBindData(BaseRecyclerViewHolder<Object> viewHolder, Object data, int position) {
        }
    }

    private static final class TestViewHolder1 extends BaseRecyclerViewHolder<Object> {
        public TestViewHolder1(ViewGroup parent) {
            super(parent, R.layout.item_recycler_1);
        }

        @Override
        public void onInit() {
        }

        @Override
        public void onBindData(Object item, int position) {

        }
    }

    private static final class TestViewHolder2 extends BaseRecyclerViewHolder<Object> {
        public TestViewHolder2(ViewGroup parent) {
            super(parent, R.layout.item_recycler_2);
        }

        @Override
        public void onInit() {
        }

        @Override
        public void onBindData(Object item, int position) {

        }
    }


}