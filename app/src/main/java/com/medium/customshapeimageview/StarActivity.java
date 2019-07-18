package com.medium.customshapeimageview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.medium.customshapeimageview.list.ListItem;

public class StarActivity extends AppCompatActivity {
    //region Instance Variables

    private CustomShapeImageView mStarImage, mHeartImage;

    private int[] mImages = new int[]{R.drawable.lc00, R.drawable.lc01, R.drawable.lc02};

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);

        mStarImage = findViewById(R.id.star_image);
        mHeartImage = findViewById(R.id.heart_image);
        Intent intent = getIntent();
        int position = intent.getIntExtra(ListItem.POSITION, 0);

        CustomShapeImageView loadInto;
        if (position % 2 == 0) {
            mHeartImage.setVisibility(View.GONE);
            loadInto = mStarImage;
        } else {
            mStarImage.setVisibility(View.GONE);
            loadInto = mHeartImage;
        }

        Glide.with(this)
                .load(mImages[position])
                .into(loadInto);
    }
}
