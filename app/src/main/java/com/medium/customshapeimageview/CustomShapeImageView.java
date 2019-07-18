package com.medium.customshapeimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CustomShapeImageView extends AppCompatImageView {
    //region Constants

    private static final int SHAPE_CIRCLE = 0;
    private static final int SHAPE_ROUND = 1;
    private static final int SHAPE_DIAGONAL = 2;
    private static final int SHAPE_STAR = 3;
    private static final int SHAPE_HEART = 4;

    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;
    private static final int DEFAULT_BORDER_RADIUS = 0;
    private static final int DEFAULT_SHAPE = SHAPE_CIRCLE;

    //endregion

    //region Instance Variables

    private int mBorderWidth = DEFAULT_BORDER_WIDTH;
    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mBorderRadius = DEFAULT_BORDER_RADIUS;
    private int mShape = DEFAULT_SHAPE;
    private boolean mHasBorder = false;

    private Paint mBorderPaint;
    private Paint mBitmapPaint;

    //endregion

    //region Constructors

    public CustomShapeImageView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CustomShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CustomShapeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomShapeImageView, defStyle, 0);

            mBorderWidth = typedArray.getDimensionPixelSize(R.styleable.CustomShapeImageView_civ_borderWidth, DEFAULT_BORDER_WIDTH);
            mBorderColor = typedArray.getColor(R.styleable.CustomShapeImageView_civ_borderColor, DEFAULT_BORDER_COLOR);
            mBorderRadius = typedArray.getDimensionPixelSize(R.styleable.CustomShapeImageView_civ_borderRadius, DEFAULT_BORDER_RADIUS);
            mShape = typedArray.getInt(R.styleable.CustomShapeImageView_civ_shape, DEFAULT_SHAPE);

            typedArray.recycle();
        }

        mHasBorder = mBorderWidth > 0 && mShape != SHAPE_STAR && mShape != SHAPE_HEART;

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setColor(mBorderColor);
    }

    //endregion

    //region Methods

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        BitmapShader shader = createBitmapShader(width, height);
        if (shader == null) return;
        mBitmapPaint.setShader(shader);

        switch (mShape) {
            case SHAPE_CIRCLE:
                drawCircle(canvas, width, height);
                break;
            case SHAPE_DIAGONAL:
                drawDiagonal(canvas, width, height);
                break;
            case SHAPE_ROUND:
                drawRound(canvas, width, height);
                break;
            case SHAPE_STAR:
                drawStar(canvas, width, height);
                break;
            case SHAPE_HEART:
                drawHeart(canvas, width, height);
                break;
        }
    }

    private BitmapShader createBitmapShader(int width, int height) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();

        if (bitmapDrawable != null) {
            Bitmap bitmap = bitmapDrawable.getBitmap();

            if (bitmap != null) {
                return new BitmapShader(Bitmap.createScaledBitmap(bitmap, width, height, false),
                        Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
        }

        return null;
    }

    private void drawCircle(Canvas canvas, int width, int height) {
        int borderRadius = Math.min(width, height) / 2;
        int bitmapRadius = borderRadius - mBorderWidth;

        canvas.drawCircle(borderRadius, borderRadius, borderRadius, mBorderPaint);
        canvas.drawCircle(borderRadius, borderRadius, bitmapRadius, mBitmapPaint);
    }

    private void drawRound(Canvas canvas, int width, int height) {
        RectF borderRect = new RectF();
        borderRect.set(0f, 0f, width, height);

        int bitmapWidth = width - mBorderWidth, bitmapHeight = height - mBorderWidth;
        RectF bitmapRect = new RectF();
        bitmapRect.set(mBorderWidth, mBorderWidth, bitmapWidth, bitmapHeight);

        canvas.drawRoundRect(borderRect, mBorderRadius, mBorderRadius, mBorderPaint);
        canvas.drawRoundRect(bitmapRect, mBorderRadius, mBorderRadius, mBitmapPaint);
    }

    private void drawDiagonal(Canvas canvas, int width, int height) {
        Path borderPath = createDiagonalPath(0, width, height);

        int bitmapWidth = width - mBorderWidth, bitmapHeight = height - mBorderWidth;
        Path bitmapPath = createDiagonalPath(mBorderWidth, bitmapWidth, bitmapHeight);

        canvas.drawPath(borderPath, mBorderPaint);
        canvas.drawPath(bitmapPath, mBitmapPaint);
    }

    private Path createDiagonalPath(int start, int width, int height) {
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        int bottomRightPointY = (int)(0.75 * height);

        path.moveTo(start, start);
        path.lineTo(start, height);
        path.lineTo(width, bottomRightPointY);
        path.lineTo(width, start);
        path.lineTo(start, start);
        path.close();

        return path;
    }

    private void drawStar(Canvas canvas, int width, int height) {
        int d = Math.min(width, height);
        int dw = width - d < 0 ? 0 : (width - d) / 2;
        int dh = height - d < 0 ? 0 : (height - d) / 2;
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        int _d2 = d/2, _d9 = d/9, _5d18 = 5*d/18, _8d9 = 8*d/9, _7d18 = 7*d/18,
        _13d18 = 13*d/18, _11d18 = 11*d/18;

        path.moveTo(dw + _d2, dh + 0);
        path.lineTo(dw + _7d18, dh + _7d18);
        path.lineTo(dw + 0, dh + _7d18);
        path.lineTo(dw + _5d18, dh + _11d18);
        path.lineTo(dw + _d9, dh + d);
        path.lineTo(dw + _d2, dh + _13d18);
        path.lineTo(dw + _8d9, dh + d);
        path.lineTo(dw + _13d18, dh + _11d18);
        path.lineTo(dw + d, dh + _7d18);
        path.lineTo(dw + _11d18, dh + _7d18);
        path.lineTo(dw + _d2, dh + 0);
        path.close();

        canvas.drawPath(path, mBitmapPaint);
    }

    private void drawHeart(Canvas canvas, int width, int height) {
        int d = Math.min(width, height);
        int dw = width - d < 0 ? 0 : (width - d) / 2;
        int dh = height - d < 0 ? 0 : (height - d) / 2;
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        int _d2 = d/2, _5d18 = 5*d/18, _d3 = d/3, _2d9 = 2*d/9, _2d3 = 2*d/3, _7d9 = 7*d/9;

        path.moveTo(dw + _d2, dh + _5d18);
        path.cubicTo(dw + _d3, dh + 0, dw + 0, dh + _5d18, dw + _2d9, dh + _2d3);
        path.lineTo(dw + _d2, dh + d);
        path.lineTo(dw + _7d9, dh + _2d3);
        path.cubicTo(dw + d, dh + _5d18, dw + _2d3, dh + 0, dw + _d2, dh + _5d18);
        path.close();

        canvas.drawPath(path, mBitmapPaint);
    }

    //endregion
}
