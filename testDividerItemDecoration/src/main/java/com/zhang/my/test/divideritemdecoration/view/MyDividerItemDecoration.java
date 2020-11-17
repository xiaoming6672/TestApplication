package com.zhang.my.test.divideritemdecoration.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.zhang.library.utils.context.ResUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * 自定义RecyclerView的透明分割线
 *
 * @author ZhangXiaoMing 2020-11-05 17:15 星期四
 */
public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    /** 分割线方向，如果设置的是{@link RecyclerView#VERTICAL}，则表示在列表竖直方向话分割线，即分割线是横向的；反之是竖向的 */
    private @RecyclerView.Orientation
    int mOrientation = RecyclerView.VERTICAL;

    /** 分割间距，单位：px */
    private int mSize;
    /** 左边缩进，{@link #mOrientation}为{@link RecyclerView#VERTICAL}的时候生效 */
    private int mPaddingLeft;
    /** 右边缩进，{@link #mOrientation}为{@link RecyclerView#VERTICAL}的时候生效 */
    private int mPaddingRight;
    /** 顶部缩进，{@link #mOrientation}为{@link RecyclerView#HORIZONTAL}的时候生效 */
    private int mPaddingTop;
    /** 底部缩进，{@link #mOrientation}为{@link RecyclerView#HORIZONTAL}的时候生效 */
    private int mPaddingBottom;

    /** 分割线Drawable */
    private Drawable mDivider = new ColorDrawable(Color.TRANSPARENT);

    private final Rect mBounds = new Rect();

    public MyDividerItemDecoration() {
        this.mSize = ResUtils.dp2px(1);
    }

    public MyDividerItemDecoration(@RecyclerView.Orientation int orientation, int size) {
        checkOrientation(orientation);

        this.mOrientation = orientation;
        this.mSize = size;
    }

    public MyDividerItemDecoration(@RecyclerView.Orientation int orientation, Drawable drawable) {
        checkOrientation(orientation);
        checkDrawable(drawable);

        this.mOrientation = orientation;
        this.mDivider = drawable;
        this.mSize = drawable.getIntrinsicWidth();
    }

    public MyDividerItemDecoration(@RecyclerView.Orientation int orientation, int size, int color) {
        checkOrientation(orientation);

        this.mOrientation = orientation;
        this.mSize = size;
        this.mDivider = new ColorDrawable(color);
    }

    private void checkOrientation(int orientation) {
        if (orientation == RecyclerView.VERTICAL
                || orientation == RecyclerView.HORIZONTAL) {
            return;
        }
        throw new IllegalArgumentException("Orientation must be RecyclerView.HORIZONTAL or RecyclerView.VERTICAL");
    }

    private void checkDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
    }

    //<editor-fold desc="设置、获取属性">
    public MyDividerItemDecoration setOrientation(@RecyclerView.Orientation int orientation) {
        checkOrientation(orientation);

        this.mOrientation = orientation;
        return this;
    }

    /** 设置分割线图案 */
    public MyDividerItemDecoration setDrawable(Drawable drawable) {
        checkDrawable(drawable);

        this.mDivider = drawable;
        this.mSize = mDivider.getIntrinsicWidth();
        return this;
    }

    /** 设置分割线大小 */
    public MyDividerItemDecoration setSize(int size) {
        this.mSize = size;
        return this;
    }

    /** 设置分割线缩进大小 */
    public MyDividerItemDecoration setPadding(int padding) {
        if (mOrientation == RecyclerView.VERTICAL) {
            this.mPaddingLeft = padding;
            this.mPaddingRight = padding;
        } else {
            this.mPaddingTop = padding;
            this.mPaddingBottom = padding;
        }
        return this;
    }

    /** 设置分割线左缩进大小 */
    public MyDividerItemDecoration setPaddingLeft(int paddingLeft) {
        this.mPaddingLeft = paddingLeft;
        return this;
    }

    /** 设置分割线右缩进大小 */
    public MyDividerItemDecoration setPaddingRight(int paddingRight) {
        this.mPaddingRight = paddingRight;
        return this;
    }

    /** 设置分割线上缩进大小 */
    public MyDividerItemDecoration setPaddingTop(int paddingTop) {
        this.mPaddingTop = paddingTop;
        return this;
    }

    /** 设置分割线下缩进大小 */
    public MyDividerItemDecoration setPaddingBottom(int paddingBottom) {
        this.mPaddingBottom = paddingBottom;
        return this;
    }

    /** 设置分割线颜色 */
    public void setColor(int color) {
        mDivider = new ColorDrawable(color);
    }

    /** 获取分割线大小 */
    public int getSize() {
        return mSize;
    }

    /** 获取分割线在列表中的方向 */
    public int getOrientation() {
        return mOrientation;
    }
    //</editor-fold>

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() == null || mDivider == null) {
            return;
        }
        if (mOrientation == RecyclerView.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /** 是否是线性布局 */
    protected boolean isLinearLayoutManager(RecyclerView parent) {
        return !isGridLayoutManager(parent) && parent.getLayoutManager() instanceof LinearLayoutManager;
    }

    /** 是否是表格布局 */
    protected boolean isGridLayoutManager(RecyclerView parent) {
        return parent.getLayoutManager() instanceof GridLayoutManager;
    }

    /** 是否是瀑布流布局 */
    protected boolean isStaggeredGridLayoutManager(RecyclerView parent) {
        return parent.getLayoutManager() instanceof StaggeredGridLayoutManager;
    }

    /** 绘制列表垂直方向 */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        if (isGridLayoutManager(parent)) {
            drawGridVertical(canvas, parent);
        } else if (isLinearLayoutManager(parent)) {
            drawLinearVertical(canvas, parent);
        } else if (isStaggeredGridLayoutManager(parent)) {
            drawStaggeredGridVertical(canvas, parent);
        }
    }

    /** 绘制线性垂直方向 */
    private void drawLinearVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();

        int left;
        int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final int childCount = parent.getChildCount();
        for (int index = 0; index < childCount; index++) {
            final View child = parent.getChildAt(index);
            if (isFirstRow(parent, parent.getChildAdapterPosition(child))) {
                continue;
            }

            parent.getDecoratedBoundsWithMargins(child, mBounds);

            int top = mBounds.top + Math.round(child.getTranslationY());
            int bottom = top + mSize;
            mDivider.setBounds(left + mPaddingLeft, top, right - mPaddingRight, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    /** 绘制表格垂直方向 */
    private void drawGridVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        //查找其他已设置的列表横向方向上的分割线，计算出两边的分割线大小，进行偏移计算，使列表纵向上的分割线，
        //不会有突出列表两边的情况
        int offsetLeft = 0;
        int offsetRight = 0;
        if (((GridLayoutManager) parent.getLayoutManager()).getOrientation() == RecyclerView.VERTICAL) {
            for (int index = 0; index < parent.getItemDecorationCount(); index++) {
                RecyclerView.ItemDecoration decor = parent.getItemDecorationAt(index);
                if (!(decor instanceof MyDividerItemDecoration))
                    continue;
                MyDividerItemDecoration myDecor = (MyDividerItemDecoration) decor;

                if (myDecor.getOrientation() == mOrientation)
                    continue;

                int size = myDecor.getSize();
                int rightSize = Math.round(size * 1.0F / 2);
                offsetRight += rightSize;
                offsetLeft += size - rightSize;
            }
        }

        final int childCount = parent.getChildCount();
        for (int index = 0; index < childCount; index++) {
            final View child = parent.getChildAt(index);
            int position = parent.getChildAdapterPosition(child);

            if (isFirstRow(parent, position) /*|| !isFirstColumn(parent, position)*/) {
                continue;
            }

            parent.getDecoratedBoundsWithMargins(child, mBounds);

            int top = mBounds.top + Math.round(child.getTranslationY());
            int bottom = top + mSize;
            mDivider.setBounds(left + offsetLeft + mPaddingLeft, top, right - offsetRight - mPaddingRight, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    /** 绘制瀑布流垂直方向 */
    private void drawStaggeredGridVertical(Canvas canvas, RecyclerView parent) {
    }

    /** 绘制列表水平方向 */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        if (isGridLayoutManager(parent)) {
            drawGridHorizontal(canvas, parent);
        } else if (isLinearLayoutManager(parent)) {
            drawLinearHorizontal(canvas, parent);
        } else if (isStaggeredGridLayoutManager(parent)) {
            drawStaggeredGridHorizontal(canvas, parent);
        }
    }

    /** 绘制线性水平方向 */
    private void drawLinearHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();

        final int top;
        final int bottom;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        final int childCount = parent.getChildCount();
        for (int index = 0; index < childCount; index++) {
            final View child = parent.getChildAt(index);
            if (isFirstColumn(parent, parent.getChildAdapterPosition(child))) {
                continue;
            }

            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);

            int left = mBounds.left + Math.round(child.getTranslationX());
            int right = left + mSize;

            mDivider.setBounds(left, top + mPaddingTop, right, bottom - mPaddingBottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    /** 绘制表格水平方向 */
    private void drawGridHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();

        int childCount = parent.getChildCount();
        if (manager.getOrientation() == RecyclerView.VERTICAL) {
            //表格列表纵向显示

            int spanCount = manager.getSpanCount();
            for (int index = 0; index < childCount; index++) {
                final View child = parent.getChildAt(index);
                int position = parent.getChildAdapterPosition(child);
                if (position >= spanCount)
                    continue;

                parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);

                int rightSize = Math.round(mSize * 1.0F / 2);
                int leftSize = mSize - rightSize;

                int left;
                int right;
                //先渲染右边
                if (!isLastColumn(parent, index)) {
                    right = mBounds.right + Math.round(child.getTranslationX());
                    left = right - rightSize;
                    mDivider.setBounds(left, top + mPaddingTop, right, bottom - mPaddingBottom);
                    mDivider.draw(canvas);
                } else {
                    right = mBounds.right + Math.round(child.getTranslationX());
                    left = right - rightSize;

                    ColorDrawable transparentDivider = new ColorDrawable(Color.TRANSPARENT);
                    transparentDivider.setBounds(left, top, right, bottom);
                    transparentDivider.draw(canvas);
                }

                //再渲染左边
                if (!isFirstColumn(parent, index)) {
                    left = mBounds.left + Math.round(child.getTranslationX());
                    right = left + leftSize;
                    mDivider.setBounds(left, top + mPaddingTop, right, bottom - mPaddingBottom);
                    mDivider.draw(canvas);
                } else {
                    left = mBounds.left + Math.round(child.getTranslationX());
                    right = left + leftSize;

                    ColorDrawable transparentDivider = new ColorDrawable(Color.TRANSPARENT);
                    transparentDivider.setBounds(left, top, right, bottom);
                    transparentDivider.draw(canvas);
                }
            }
        } else {
            //表格列表横向显示

            for (int index = 0; index < childCount; index++) {
                View child = parent.getChildAt(index);
                int position = parent.getChildAdapterPosition(child);

                if (isFirstColumn(parent, position) || !isFirstRow(parent, position))
                    continue;

                parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);

                int left = mBounds.left + Math.round(child.getTranslationX());
                int right = left + mSize;
                mDivider.setBounds(left, top + mPaddingTop, right, bottom - mPaddingBottom);
                mDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    /** 绘制瀑布流水平方向 */
    private void drawStaggeredGridHorizontal(Canvas canvas, RecyclerView parent) {
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);

        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (isGridLayoutManager(parent) || isStaggeredGridLayoutManager(parent)) {
            getGridDividerOffsets(parent, outRect, position);
        } else {
            if (isLinearLayoutManager(parent)) {
                int orientation = ((LinearLayoutManager) manager).getOrientation();
                if (mOrientation != orientation) {
                    return;
                }
            }
            getLinearDividerOffsets(parent, outRect, position);
        }
    }

    /**
     * 判断当前item是否是第一行
     *
     * @param parent   RecyclerView列表
     * @param position 当前item位置
     */
    protected boolean isFirstRow(RecyclerView parent, int position) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (isGridLayoutManager(parent)) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            final int spanCount = manager.getSpanCount();

            if (manager.getOrientation() == RecyclerView.VERTICAL) {
                return position < spanCount;
            } else {
                return position % spanCount == 0;
            }
        } else if (isLinearLayoutManager(parent)) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            return manager.getOrientation() == RecyclerView.HORIZONTAL || position == 0;
        }

        return false;
    }

    /**
     * 判断当前item是否是最后一行
     *
     * @param parent   RecyclerView列表
     * @param position 当前item位置
     */
    protected boolean isLastRow(RecyclerView parent, int position) {
        final int childCount = parent.getAdapter() == null ? 0 : parent.getAdapter().getItemCount();

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (isGridLayoutManager(parent)) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            final int spanCount = manager.getSpanCount();
            if (manager.getOrientation() == RecyclerView.VERTICAL) {
                int result = childCount % spanCount;
                if (result == 0) {
                    return position >= childCount - spanCount;
                } else {
                    return position >= childCount - result;
                }
            } else {
                return (position + 1) % spanCount == 0;
            }
        } else if (isLinearLayoutManager(parent)) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            return manager.getOrientation() == RecyclerView.HORIZONTAL      //横向
                    || position == childCount - 1;                          //纵向
        }
        return false;
    }

    /**
     * 判断当前位置的item是否是第一列
     *
     * @param parent   RecyclerView列表
     * @param position 当前item的位置
     */
    protected boolean isFirstColumn(RecyclerView parent, int position) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (isGridLayoutManager(parent)) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            final int spanCount = manager.getSpanCount();
            if (manager.getOrientation() == RecyclerView.VERTICAL) {
                return position % spanCount == 0;
            } else {
                return position < spanCount;
            }
        } else if (isLinearLayoutManager(parent)) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            return manager.getOrientation() == RecyclerView.VERTICAL || position == 0;
        }
        return false;
    }

    /**
     * 判断当前位置的item是否是最后一列
     *
     * @param parent   RecyclerView列表
     * @param position 当前item的位置
     */
    protected boolean isLastColumn(RecyclerView parent, int position) {
        final int childCount = parent.getAdapter() == null ? 0 : parent.getAdapter().getItemCount();

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (isGridLayoutManager(parent)) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            int spanCount = manager.getSpanCount();
            if (manager.getOrientation() == RecyclerView.VERTICAL) {
                return (position + 1) % spanCount == 0;
            } else {
                int result = childCount % spanCount;
                if (result == 0) {
                    return position >= childCount - spanCount;
                } else {
                    return position >= childCount - result;
                }
            }
        } else if (isLinearLayoutManager(parent)) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            return manager.getOrientation() == RecyclerView.VERTICAL || position == childCount - 1;
        }
        return false;
    }

    /** 计算填充表格列表item的分割线偏移量 */
    private void getGridDividerOffsets(@NonNull RecyclerView parent, @NonNull Rect rect, int position) {
//        processGridTopDivider(parent, rect, position);
        if (mOrientation == RecyclerView.VERTICAL) {
            if (isFirstRow(parent, position)) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(0, mSize, 0, 0);
            }
        }

//        processGridBothSidesDivider(parent, rect, position);
        if (mOrientation == RecyclerView.HORIZONTAL) {
            @RecyclerView.Orientation int orientation;
            if (isGridLayoutManager(parent))
                orientation = ((GridLayoutManager) parent.getLayoutManager()).getOrientation();
            else if (isStaggeredGridLayoutManager(parent))
                orientation = ((StaggeredGridLayoutManager) parent.getLayoutManager()).getOrientation();
            else
                orientation = RecyclerView.VERTICAL;

            if (orientation == RecyclerView.VERTICAL) {
                int right = Math.round(mSize * 1.0F / 2);
                int left = mSize - right;
                rect.set(left, 0, right, 0);
            } else {
                if (isFirstColumn(parent, position)) {
                    rect.set(0, 0, 0, 0);
                } else {
                    rect.set(mSize, 0, 0, 0);
                }
            }
        }

    }

    /** 计算填充线性列表item的分割线偏移量 */
    private void getLinearDividerOffsets(@NonNull RecyclerView parent, @NonNull Rect rect, int position) {
        if (mOrientation == RecyclerView.VERTICAL) {
            if (isFirstRow(parent, position))
                rect.set(0, 0, 0, 0);
            else
                rect.set(0, mSize, 0, 0);
        } else {
            if (isFirstColumn(parent, position))
                rect.set(0, 0, 0, 0);
            else
                rect.set(mSize, 0, 0, 0);
        }
    }

}
