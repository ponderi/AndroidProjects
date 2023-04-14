package com.websarva.wings.android.photo_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private final int layoutId;
    private final List<String> imageList = new ArrayList<>();

    GridAdapter(Context context, int layoutId, String[] iList) {
        super();
        this.context = context;
        this.inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layoutId;

        Collections.addAll(imageList, iList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = inflater.inflate(layoutId, parent, false);
        } else {
            view =  convertView;
        }

        ImageView imageView = view.findViewById(R.id.image_view);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Picasso.with(context)
                .load("file:///android_asset/photos/" + imageList.get(position))
                .placeholder(R.drawable.grid_item_icon)
                .transform(new ResizeTransformation())
                .into(imageView);

        return view;
    }

    @Override
    public int getCount() {
        // 全要素数を返す
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}