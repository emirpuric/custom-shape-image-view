package com.medium.customshapeimageview.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.medium.customshapeimageview.R;
import com.medium.customshapeimageview.StarActivity;

import java.util.LinkedList;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    //region Instance Variables

    private Context mContext;
    private LinkedList<ListItem> mListItems;

    //endregion

    //region Constructor

    public ListAdapter(Context context, LinkedList<ListItem> listItems) {
        mContext = context;
        mListItems = listItems;
    }

    //endregion

    //region Methods

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.list_view, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {
        ListItem listItem = mListItems.get(position);

        holder.firstLine.setText(listItem.getFirstLine());
        holder.secondLine.setText(listItem.getSecondLine());

        Glide.with(mContext)
                .load(listItem.getCoverId())
                .into(holder.crest);

        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, StarActivity.class);
                intent.putExtra(ListItem.POSITION, position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    //endregion
}
