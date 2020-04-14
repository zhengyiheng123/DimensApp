package com.example.asus.dimensapp.view.picturetag;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asus.dimensapp.R;
import com.example.asus.dimensapp.view.picturetag.entity.DataEntity;

/**
 * Created by zyh
 * on 2020/4/14
 */
public class TagView extends RelativeLayout {
    private ImageView ivTop, ivGuide, iv_guide2;

    public int width;
    public int heitht;

    private DataEntity data;

    public TagView(Context context, final int width, final int height) {
        super(context);
        this.width = width;
        this.heitht = height;
        initView();
    }

    public TagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.tag_view, this);
        ivTop = (ImageView) findViewById(R.id.iv_top);
        ivGuide = (ImageView) findViewById(R.id.iv_guide);
        iv_guide2 = (ImageView) findViewById(R.id.iv_guide2);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "点击事件", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData(DataEntity data) {
        this.data = data;

        if (null != data) {
            switch (data.getType()) {
                case 1:
                    ivTop.setImageResource(R.mipmap.icon_accomplish);
                    showFirst(data.isFirst);
                    break;
                case 2:
                    ivTop.setImageResource(R.mipmap.icon_half_s);
                    showFirst(data.isFirst);
                    break;
                case 3:
                    ivTop.setImageResource(R.mipmap.icon_wrong_s);
                    showFirst(data.isFirst);
                    break;
                case 0:
                    ivTop.setImageResource(R.mipmap.icon_not_checked);
                    iv_guide2.setVisibility(VISIBLE);
                    break;
                default:
            }

        }
    }

    private void showFirst(boolean first) {
        if (data.isFirst) {
            ivGuide.setVisibility(VISIBLE);
        } else {
            ivGuide.setVisibility(GONE);
        }
        if (iv_guide2.getVisibility() == VISIBLE) {
            iv_guide2.setVisibility(GONE);
        }
    }
}
