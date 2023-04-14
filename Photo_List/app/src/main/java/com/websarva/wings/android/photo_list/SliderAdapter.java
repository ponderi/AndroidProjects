package com.websarva.wings.android.photo_list;

import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{

    private ViewPager2 viewPager2;
    private final List<String> sliderItems = new ArrayList<>();

    SliderAdapter(ViewPager2 viewPager2, String[] fileList) {
        this.viewPager2 = viewPager2;
        // infinity-loop用に3回セット
        Collections.addAll(sliderItems, fileList);
        Collections.addAll(sliderItems, fileList);
        Collections.addAll(sliderItems, fileList);
    }



    class SliderViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView imageView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        RoundedImageView setImage(String sliderItem) {

            Picasso.with(imageView.getContext())
                    .load("file:///android_asset/photos/" + sliderItem)
                    .into(imageView);

            // ViewPager2の画像タップ処理
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    Transliterator trans = Transliterator.getInstance("Latin-Hiragana");

                    // ローマ字を日本語にし、拡張子(「.jpg」)を除いて表示
                    String jName = sliderItem.replace(".jpg" , "");
                    jName = trans.transliterate(jName);
                    Toast.makeText(imageView.getContext(), jName, Toast.LENGTH_SHORT).show();
                }
            });

            return imageView;
        }
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }
}
