package com.websarva.wings.android.photo_list;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.squareup.picasso.Transformation;

public class ResizeTransformation implements Transformation {
    Bitmap result;

    @Override
    public Bitmap transform(Bitmap source) {

        result = source;

        // 写真のサイズを取得して縦・横を変更する
        BitmapFactory.Options imageOption = new BitmapFactory.Options();
        imageOption.inJustDecodeBounds = true;
        int targetWidth = source.getWidth() / 10;
        int targetHeight = source.getHeight() / 10;

        if (source.getWidth() > 700 || source.getHeight() > 700) {
            // result = Bitmap.createBitmap(source, 0, 0, targetWidth, targetHeight, new Matrix(), true);
            result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight,true);
            source.recycle();
            return result;
        }
        return source;
    }

    @Override public String key() { return "square()"; }
}