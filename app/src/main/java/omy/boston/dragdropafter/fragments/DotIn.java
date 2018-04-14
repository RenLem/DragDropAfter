package omy.boston.dragdropafter.fragments;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

/**
 * Created by LosFrancisco on 19-Apr-17.
 */

public class DotIn extends View implements View.OnDragListener {
    private Paint mNormalPaint;
    private Paint mDraggingPaint;
    private int mColor = Color.MAGENTA;
    private int mRadius = 20;
    private boolean inDrag;

    public DotIn(Context context, AttributeSet attrs) {
        super(context, attrs);
        mNormalPaint = new Paint();
        mNormalPaint.setColor(mColor);
        mNormalPaint.setAntiAlias(true);
        mDraggingPaint = new Paint();
        mDraggingPaint.setColor(Color.YELLOW);
        mDraggingPaint.setAntiAlias(true);
        setOnLongClickListener(lcListener);
        setOnDragListener(this);
    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        float cx = this.getWidth() / 2 + getLeftPaddingOffset();
        float cy = this.getHeight() / 2 + getTopPaddingOffset();
        Paint paint = mNormalPaint;
        if (inDrag){
            paint = mDraggingPaint;
        }
        canvas.drawCircle(cx, cy, mRadius, paint);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec){
        int size = 2 * mRadius + getPaddingLeft() + getPaddingRight();
        setMeasuredDimension(size, size);
    }

    private static View.OnLongClickListener lcListener = new OnLongClickListener() {
        private boolean mDragInProgress;
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("DragData", (String) v.getTag());
            mDragInProgress = v.startDrag(data, new View.DragShadowBuilder(v), (Object) v, 8);
            return false;
        }
    };

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        if (dragEvent.getLocalState() != this) {
            return false;
        }
        boolean result = true;
        int action = dragEvent.getAction();
        float x = dragEvent.getX();
        float y = dragEvent.getY();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                inDrag = true;
            case DragEvent.ACTION_DRAG_LOCATION:
            case DragEvent.ACTION_DRAG_ENTERED:
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                result = false;
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                inDrag = false;
                break;
            default:
                result = false;
                break;
        }

        return result;
    }
}
