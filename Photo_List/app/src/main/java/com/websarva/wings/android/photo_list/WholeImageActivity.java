package com.websarva.wings.android.photo_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

public class WholeImageActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private String[] fileList = null;
    private int viewPosition;
    private int realCount;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_image);

        // intent呼び出し
        Intent intent = getIntent();
        fileList = (String[]) intent.getSerializableExtra("fileList");
        viewPosition = (int) intent.getSerializableExtra("position");

        // ViewPager2のインスタンス生成
        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        // SliderAdapterのインスタンス生成
        SliderAdapter adapter = new SliderAdapter(
                viewPager2,
                fileList
        );
        viewPager2.setAdapter(adapter);
        // GridViewでタップされた画像をviewPager2の初期表示にセット
        viewPager2.setCurrentItem(adapter.getItemCount()/3 + viewPosition,false);

        // ViewPager2-infinity-loop
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()  {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                realCount = adapter.getItemCount()/3;
                currentItem = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE://No operation
                        if (currentItem == realCount -2) {
                            viewPager2.setCurrentItem((realCount*2)-2, false);
                        } else if (currentItem == (realCount*2)+1) {
                            viewPager2.setCurrentItem(realCount+1, false);
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING: //start Sliding
                        if (currentItem == realCount-2) {
                            viewPager2.setCurrentItem((realCount*2)-2, false);
                        } else if (currentItem == (realCount*2)+1) {
                            viewPager2.setCurrentItem(realCount, false);
                        }
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING://end Sliding
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed(){

        finish();
    }
}