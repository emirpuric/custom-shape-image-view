package com.medium.customshapeimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.medium.customshapeimageview.list.ListAdapter;
import com.medium.customshapeimageview.list.ListItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    //region Instance Variables

    RecyclerView mRecycleView;
    LinkedList<ListItem> mListItems;
    ListAdapter mListAdapter;

    //endregion

    //region Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomShapeImageView cover = findViewById(R.id.cover);
        Glide.with(this)
                .load(R.drawable.cover)
                .into(cover);

        CustomShapeImageView crest = findViewById(R.id.crest);
        Glide.with(this)
                .load(R.drawable.crest)
                .into(crest);

        mRecycleView = findViewById(R.id.recycler_view);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mListItems = new LinkedList<>();
        mListAdapter = new ListAdapter(this, mListItems);

        mRecycleView.setAdapter(mListAdapter);
        generateListItems();
        mListAdapter.notifyDataSetChanged();
    }

    private void generateListItems() {
        mListItems.add(new ListItem(
                getResources().getString(R.string.lorem_ipsum),
                getResources().getString(R.string.consectetur),
                R.drawable.lc00));

        mListItems.add(new ListItem(
                getResources().getString(R.string.dignissim),
                getResources().getString(R.string.quam_vulputate),
                R.drawable.lc01));

        mListItems.add(new ListItem(
                getResources().getString(R.string.scelerisque),
                getResources().getString(R.string.tristique),
                R.drawable.lc02));
    }
}
