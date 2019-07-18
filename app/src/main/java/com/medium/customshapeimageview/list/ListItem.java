package com.medium.customshapeimageview.list;

public class ListItem {
    //Constants

    public static String POSITION = "POSITION";

    //endregion

    //region Instance Variables

    private String mFirstLine;
    private String mSecondLine;
    private int mCoverId;

    //endregion

    //region Constructor

    public ListItem(String firstLine, String secondLine, int coverId) {
        mFirstLine = firstLine;
        mSecondLine = secondLine;
        mCoverId = coverId;
    }

    //endregion

    //region Getters

    public String getFirstLine() {
        return mFirstLine;
    }

    public String getSecondLine() {
        return mSecondLine;
    }

    public int getCoverId() {
        return mCoverId;
    }


    //endregion
}
