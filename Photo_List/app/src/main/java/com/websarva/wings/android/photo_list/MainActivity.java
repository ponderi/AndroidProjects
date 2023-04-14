package com.websarva.wings.android.photo_list;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity{
    private String[] fileList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ツールバー設定
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.toolbar_icon);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbarLayout);
        toolbarLayout.setTitle(getString(R.string.toolbar_title));
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);

        // fileListにassets/photosのファイル名を取得
        AssetManager assetManager = getResources().getAssets();
        try {
            fileList = assetManager.list("photos");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // GridViewのインスタンスを生成
        GridView gridview = findViewById(R.id.gridview);

        // BaseAdapter を継承したGridAdapterのインスタンスを生成
        GridAdapter adapter = new GridAdapter(
                this.getApplicationContext(),
                R.layout.grid_items,
                fileList);

        // gridViewにadapterをセット
        gridview.setAdapter(adapter);

        // GridViewの画像タップ処理
        gridview.setOnItemClickListener(new GridImageClickListener());
    }

    private class GridImageClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // intent呼び出し
            Intent intent = new Intent(MainActivity.this, WholeImageActivity.class);
            // 第2画面に送るデータを格納。
            intent.putExtra("fileList",fileList);
            intent.putExtra("position", position);
            // 第2画面の起動
            startActivity(intent);
        }
    }

}