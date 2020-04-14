package com.example.asus.dimensapp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.asus.dimensapp.view.picturetag.PictureTagView;
import com.example.asus.dimensapp.view.picturetag.TagView;
import com.example.asus.dimensapp.view.picturetag.entity.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyh
 * on 2020/4/14
 */
public class SecondActivity extends AppCompatActivity {
    private List<TagView> views = new ArrayList<>();
    private PictureTagView pivtureTagView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_second);
        pivtureTagView = (PictureTagView) findViewById(R.id.pivtureTagView);
        initData();
        pivtureTagView.bindData(views);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        DataEntity dataEntity1 = new DataEntity();
        dataEntity1.isFirst = true;
        dataEntity1.setType(1);
        TagView tagView1 = new TagView(SecondActivity.this, 200, 100);
        tagView1.setData(dataEntity1);

        TagView tagView2 = new TagView(SecondActivity.this, 400, 150);
        DataEntity dataEntity2 = new DataEntity();
        dataEntity2.isFirst = false;
        dataEntity2.setType(2);
        tagView2.setData(dataEntity2);

        DataEntity dataEntity3 = new DataEntity();
        dataEntity3.isFirst = false;
        dataEntity3.setType(3);
        TagView tagView3 = new TagView(SecondActivity.this, 200, 300);
        tagView3.setData(dataEntity3);

        DataEntity dataEntity4 = new DataEntity();
        TagView tagView4 = new TagView(SecondActivity.this, 600, 250);
        dataEntity4.isFirst = false;
        dataEntity4.setType(0);
        tagView4.setData(dataEntity4);

        views.add(tagView1);
        views.add(tagView2);
        views.add(tagView3);
        views.add(tagView4);
    }
}
