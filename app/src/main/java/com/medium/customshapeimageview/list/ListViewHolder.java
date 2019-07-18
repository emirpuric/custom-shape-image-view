package com.medium.customshapeimageview.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medium.customshapeimageview.CustomShapeImageView;
import com.medium.customshapeimageview.R;

public class ListViewHolder extends RecyclerView.ViewHolder {
    //region Instance Variables

    public LinearLayout wrapper;

    public CustomShapeImageView crest;

    public TextView firstLine;

    public TextView secondLine;

    //endregion

    //region Constructors

    public ListViewHolder(View view) {
        super(view);

        wrapper = view.findViewById(R.id.wrapper);
        crest = view.findViewById(R.id.crest);
        firstLine = view.findViewById(R.id.first_line);
        secondLine = view.findViewById(R.id.second_line);
    }
    //endregion

}
