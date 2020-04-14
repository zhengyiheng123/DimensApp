package com.example.asus.dimensapp.view.picturetag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.asus.dimensapp.R;

import java.util.List;

/**
 * Created by zyh
 * on 2020/4/14
 */
public class PictureTagView extends FrameLayout {
    public static final String TAG = "PictureTagView";
    private ImageView imageView;

    public PictureTagView(@NonNull Context context) {
        super(context);
        initView();
    }

    public PictureTagView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PictureTagView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        imageView = new ImageView(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.beike_default__small_img);

        addView(imageView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int childCount = getChildCount();

        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i) instanceof TagView) {
                    TagView childView = (TagView) getChildAt(i);
                    int measureWidth = childView.getWidth();
                    int measureHight = childView.getHeight();
                    int width = childView.width;
                    int height = childView.heitht;
                    int childLeft, childRight;

                    if (width + measureWidth >= right) {
                        childLeft = right - measureWidth;
                        childRight = right;
                    } else if (width <= 0) {
                        childLeft = 0;
                        childRight = measureWidth;
                    } else {
                        childLeft = width;
                        childRight = width + measureWidth;
                    }

                    int chiildTop, childBottom;
                    if (height + measureHight >= bottom) {
                        chiildTop = bottom - measureHight;
                        childBottom = bottom;
                    } else if (width < 0) {
                        chiildTop = 0;
                        childBottom = measureHight;
                    } else {
                        chiildTop = height;
                        childBottom = height + measureHight;
                    }
                    childView.layout(childLeft, chiildTop, childRight, childBottom);
                }
            }
        }
    }

    public void bindData(List<TagView> views) {
        if (null != views && views.size() > 0) {
            for (TagView tagView : views) {
                addView(tagView);

                //addview之后必须重新设置子view 的宽高
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tagView.setLayoutParams(params);
            }
        }
        requestLayout();
    }

}
