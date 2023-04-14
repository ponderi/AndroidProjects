package com.websarva.wings.android.photo_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        Handler hdl = new Handler();
        // 1秒間splashを表示する
        hdl.postDelayed(new splashHandler(),1000);
    }

    class splashHandler implements Runnable {
        public void run() {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}