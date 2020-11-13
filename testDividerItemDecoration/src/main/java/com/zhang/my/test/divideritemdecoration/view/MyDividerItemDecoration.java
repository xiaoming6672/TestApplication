package com.zhang.my.test.divideritemdecoration.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.zhang.library.utils.LogUtils;

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

    private static final int DEFAULT_SIZE_ZERO = 0;

    /** 父类（即RecyclerView）的列表方向 */
    private Integer mParentOrientation;

    /** 分割线方向，如果设置的是{@link RecyclerView#VERTICAL}，则表示在列表竖直方向话分割线，即分割线是横向的；反之是竖向的 */
    private @RecyclerView.Orientation
    int mOrientation = RecyclerView.VERTICAL;

    /** 分割间距，单位：px */
    private int mSize;

    private Drawable mDivider = new ColorDrawable(Color.TRANSPARENT);
    private Drawable mTransparentDivider = new ColorDrawable(Color.TRANSPARENT);

    private final Rect mBounds = new Rect();

    public MyDividerItemDecoration() {
    }

    public MyDividerItemDecoration(@RecyclerView.Orientation int orientation, int size) {
        this.mOrientation = orientation;
        this.mSize = size;
    }

    public MyDividerItemDecoration(@RecyclerView.Orientation int orientation, Drawable drawable) {
        this.mOrientation = orientation;
        this.mDivider = drawable;
        this.mSize = drawable.getIntrinsicWidth();
    }

    public MyDividerItemDecoration(@RecyclerView.Orientation int orientation, int size, int color) {
        this.mOrientation = orientation;
        this.mSize = size;
        this.mDivider = new ColorDrawable(color);
    }

    public void setOrientation(@RecyclerView.Orientation int orientation) {
        this.mOrientation = orientation;
    }

    public void setSize(int size) {
        this.mSize = size;
    }

    public void setColor(int color) {
        mDivider = new ColorDrawable(color);
    }

    public int getSize() {
        return mSize;
    }

    public int getOrientation() {
        return mOrientation;
    }

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

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
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
            if (isFirstRow(parent, index)) {
                continue;
            }

            final View child = parent.getChildAt(index);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
//            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
//            final int top = bottom - mSize;
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(canvas);

            int top = mBounds.top + Math.round(child.getTranslationY());
            int bottom = top + mSize;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
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
//            if (isLastColumn(parent, index)) {
//                continue;
//            }

            final View child = parent.getChildAt(index);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
//            final int right = mBounds.right + Math.round(child.getTranslationX());
//            final int left = right - mSize;
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(canvas);

            int rightSize = Math.round(mSize * 1.0F / 2);
            int leftSize = mSize - rightSize;

            //先渲染右边
            if (!isLastColumn(parent, index)) {
                int right = mBounds.right + Math.round(child.getTranslationX());
                int left = right - rightSize;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            } else {
                int right = mBounds.right + Math.round(child.getTranslationX());
                int left = right - rightSize;
                mTransparentDivider.setBounds(left, top, right, bottom);
                mTransparentDivider.draw(canvas);
            }

            //再渲染左边
            if (!isFirstColumn(parent, index)) {
                int left = mBounds.left + Math.round(child.getTranslationX());
                int right = left + leftSize;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            } else {
                int left = mBounds.left + Math.round(child.getTranslationX());
                int right = left + leftSize;
                mTransparentDivider.setBounds(left, top, right, bottom);
                mTransparentDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);
//        if (mOrientation == RecyclerView.HORIZONTAL) {
//            if (isLastRow(parent, position))
//                outRect.set(DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO);
//            else
//                outRect.set(DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, mSize);
//        } else {
//            if (isLastColumn(parent, position))
//                outRect.set(DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO);
//            else
//                outRect.set(DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, mSize, DEFAULT_SIZE_ZERO);
//        }

        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
            int spanCount;
            if (manager instanceof GridLayoutManager) {
                mParentOrientation = ((GridLayoutManager) manager).getOrientation();
                spanCount = ((GridLayoutManager) manager).getSpanCount();
            } else {
                mParentOrientation = ((StaggeredGridLayoutManager) manager).getOrientation();
                spanCount = ((StaggeredGridLayoutManager) manager).getSpanCount();
            }
            processGridDivider(outRect, spanCount, position);
        } else {
            if (manager instanceof LinearLayoutManager) {
                mParentOrientation = ((LinearLayoutManager) manager).getOrientation();
            }
            processLinearDivider(outRect, position);
        }
    }

    /**
     * 判断当前item是否是第一行
     *
     * @param parent   RecyclerView列表
     * @param position 当前item位置
     */
    private boolean isFirstRow(RecyclerView parent, int position) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            final int spanCount = manager.getSpanCount();

            if (manager.getOrientation() == RecyclerView.VERTICAL) {
                return position < spanCount;
            } else {
                return position % spanCount == 0;
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            return manager.getOrientation() == RecyclerView.HORIZONTAL || position == 0;
//            if (manager.getOrientation() == RecyclerView.VERTICAL) {
//                return position == 0;
//            } else {
//                return true;
//            }
        }

        return false;
    }

    /**
     * 判断当前item是否是最后一行
     *
     * @param parent   RecyclerView列表
     * @param position 当前item位置
     */
    private boolean isLastRow(RecyclerView parent, int position) {
        final int childCount = parent.getAdapter() == null ? 0 : parent.getAdapter().getItemCount();

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
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
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            if (manager.getOrientation() == RecyclerView.HORIZONTAL) {
                //横向
                return true;
            }
            //纵向
            return position == childCount - 1;
        }
        return false;
    }

    /**
     * 判断当前位置的item是否是第一列
     *
     * @param parent   RecyclerView列表
     * @param position 当前item的位置
     */
    private boolean isFirstColumn(RecyclerView parent, int position) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            final int spanCount = manager.getSpanCount();
            if (manager.getOrientation() == RecyclerView.VERTICAL) {
                return position % spanCount == 0;
            } else {
                return position < spanCount;
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            if (manager.getOrientation() == RecyclerView.VERTICAL) {
                return true;
            } else {
                return position == 0;
            }
        }
        return false;
    }

    /**
     * 判断当前位置的item是否是最后一列
     *
     * @param parent   RecyclerView列表
     * @param position 当前item的位置
     */
    private boolean isLastColumn(RecyclerView parent, int position) {
        final int childCount = parent.getAdapter() == null ? 0 : parent.getAdapter().getItemCount();

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {
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
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            if (manager.getOrientation() == RecyclerView.VERTICAL) {
                return true;
            }
            return position == childCount - 1;
        }
        return false;
    }

    private boolean isTopSide(int spanCount, int position) {
        return position < spanCount;
    }

    private boolean isLeftSide(int spanCount, int position) {
        return position % spanCount == 0;
    }

    private boolean isRightSide(int spanCount, int position) {
        return position % spanCount == spanCount - 1;
    }

    private void processGridDivider(@NonNull Rect rect, int spanCount, int position) {
        LogUtils.debug(String.valueOf(mOrientation), "spanCount = " + spanCount + "  position = " + position);
        LogUtils.info(String.valueOf(mOrientation), "isTopSide = " + isTopSide(spanCount, position) + "  isLeftSide = " + isLeftSide(spanCount, position));

        processGridTopDivider(rect, spanCount, position);
        processGridBothSidesDivider(rect, spanCount, position);
    }

    private void processGridTopDivider(@NonNull Rect rect, int spanCount, int position) {
        if (mOrientation == RecyclerView.HORIZONTAL) {
            return;
        }

        if (isTopSide(spanCount, position)) {
            rect.set(DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO);
        } else {
            rect.set(DEFAULT_SIZE_ZERO, mSize, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO);
        }
    }

    private void processGridBothSidesDivider(@NonNull Rect rect, int spanCount, int position) {
        if (mOrientation == RecyclerView.VERTICAL) {
            return;
        }

//        if (isLeftSide(spanCount, position))
//            rect.set(DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, mSize / 2, DEFAULT_SIZE_ZERO);
//         else if (isRightSide(spanCount, position))
//            rect.set(mSize / 2, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO);
//         else
        int right = Math.round(mSize * 1.0F / 2);
        int left = mSize - right;
        rect.set(left, DEFAULT_SIZE_ZERO, right, DEFAULT_SIZE_ZERO);

    }

    private void processLinearDivider(@NonNull Rect rect, int position) {
        if (position == 0) {
            if (mParentOrientation == null) {
                processDivider(rect, DEFAULT_SIZE_ZERO);
                return;
            }

            if (mParentOrientation != mOrientation) {
                processDivider(rect, mSize);
                return;
            }
            processDivider(rect, DEFAULT_SIZE_ZERO);
            return;
        }

        processDivider(rect, mSize);
    }

    private void processDivider(@NonNull Rect rect, int size) {
        LogUtils.warn(String.valueOf(mOrientation), "size = " + size);

        if (mOrientation == RecyclerView.VERTICAL) {
            rect.set(DEFAULT_SIZE_ZERO, size, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO);
        } else {
            rect.set(size, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO, DEFAULT_SIZE_ZERO);
        }

    }

}
