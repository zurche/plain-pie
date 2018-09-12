package az.plainpie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import az.plainpie.annotation.ColorInt;

/**
 * @author Alejandro Zürcher (alejandro.zurcher@gmail.com)
 */
public class PieView extends View {

    private static final String DEFAULT_PERCENTAGE_TEXT = "0%";
    private static final int DEFAULT_PERCENTAGE_SIZE = 35;
    private static final int DEFAULT_INNER_CIRCLE_PADDING = 15;
    private static final int CIRCLE_DEGREES = 360;

    private RelativeLayout baseLayout;
    private TextView mPercentageTextView = null;
    private int mPercentageSize = DEFAULT_PERCENTAGE_SIZE;
    private int mInnerCirclePadding = DEFAULT_INNER_CIRCLE_PADDING;
    private Paint mPercentageFill;
    private Paint mBackgroundFill;
    private Paint mCenterFill;
    private RectF mRect;
    private RectF mRectCent;
    private float mPercentage = 0;
    private float mMaxPercentage = 100;
    private float mAngle = 0;

    public PieView(Context context) {
        super(context);

        mPercentage = 0;
        mAngle = 0;

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        baseLayout = new RelativeLayout(context);
        baseLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mPercentageTextView = new TextView(context);
        int roundedPercentage = (int) (mPercentage * this.mMaxPercentage);
        mPercentageTextView.setText(Integer.toString(roundedPercentage) + "%");
        baseLayout.addView(mPercentageTextView);
        mPercentageSize = 50;
        mPercentageTextView.setTextSize(mPercentageSize);

        init();
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupWidgetWithParams(context, attrs);
        init();
    }

    public PieView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupWidgetWithParams(context, attrs);
        init();
    }

    private void setupWidgetWithParams(Context context, AttributeSet attrs) {
        mPercentageTextView = new TextView(context);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PieView,
                0, 0);
        try {
            mPercentage = a.getFloat(R.styleable.PieView_percentage, 0);
            if (mPercentage > 0) {
                mPercentage = a.getFloat(R.styleable.PieView_percentage, 0) / this.mMaxPercentage;
                mAngle = (CIRCLE_DEGREES * this.mPercentage);
            }
            mPercentageSize = a.getInteger(R.styleable.PieView_percentage_size, DEFAULT_PERCENTAGE_SIZE);
            mInnerCirclePadding = a.getInteger(R.styleable.PieView_inner_pie_padding, DEFAULT_INNER_CIRCLE_PADDING);
            mPercentageTextView.setText(a.getString(R.styleable.PieView_inner_text));
            mPercentageTextView.setVisibility(a.getBoolean(R.styleable.PieView_inner_text_visibility, true) ? View.VISIBLE : View.INVISIBLE);
        } finally {
            a.recycle();
        }
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        baseLayout = new RelativeLayout(context);
        baseLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (mPercentageTextView.getText().toString().trim().equals("")) {
            setPercentageText();
        }
        mPercentageTextView.setTextSize(mPercentageSize);
        baseLayout.addView(mPercentageTextView);
    }

    private void setPercentageText() {
        if (mPercentage != 0) {
            int roundedPercentage = (int) (mPercentage * mMaxPercentage);
            mPercentageTextView.setText(Integer.toString(roundedPercentage) + "%");
        } else {
            mPercentageTextView.setText(DEFAULT_PERCENTAGE_TEXT);
        }
    }

    private void init() {
        mPercentageTextView.setTextColor(getContext().getResources().getColor(R.color.percentageTextColor));

        mPercentageFill = new Paint();
        mPercentageFill.setColor(getContext().getResources().getColor(R.color.percentageFillColor));
        mPercentageFill.setAntiAlias(true);
        mPercentageFill.setStyle(Paint.Style.FILL);
        mBackgroundFill = new Paint();
        mBackgroundFill.setColor(getContext().getResources().getColor(R.color.percentageUnfilledColor));
        mBackgroundFill.setAntiAlias(true);
        mBackgroundFill.setStyle(Paint.Style.FILL);

        mCenterFill = new Paint();
        mCenterFill.setColor(getContext().getResources().getColor(R.color.percentageTextBackground));
        mCenterFill.setAntiAlias(true);
        mCenterFill.setStyle(Paint.Style.FILL);

        mRect = new RectF();
        mRectCent = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int left = 0;
        int width = getWidth();
        int height = getHeight();
        int top = 0;

        mRect.set(left, top, left + width, top + width);
        mRectCent.set(left + mInnerCirclePadding, top + mInnerCirclePadding, (left - mInnerCirclePadding) + width, (top - mInnerCirclePadding) + width);

        canvas.drawArc(mRect, -90, CIRCLE_DEGREES, true, mBackgroundFill);

        if (mPercentage != 0) {
            canvas.drawArc(mRect, -90, mAngle, true, mPercentageFill);
        }
        canvas.drawArc(mRectCent, -90, CIRCLE_DEGREES, true, mCenterFill);

        mPercentageTextView.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        mPercentageTextView.layout(left, top, left + width, top + height);
        mPercentageTextView.setGravity(Gravity.CENTER);

        baseLayout.draw(canvas);
    }

    public float getPieAngle() {
        return mAngle;
    }

    public void setPieAngle(final float arcAngle) {
        mAngle = arcAngle;
    }

    /**
     * Toggle the visibility of the inner label of the widget
     *
     * @param visibility One of {@link #VISIBLE}, {@link #INVISIBLE}, or {@link #GONE}.
     */
    @SuppressWarnings("unused")
    public void setInnerTextVisibility(final int visibility) {
        mPercentageTextView.setVisibility(visibility);
        invalidate();
    }

    /**
     * Set the text of the inner label of the widget
     *
     * @param text any valid String value
     */
    @SuppressWarnings("unused")
    public void setInnerText(final String text) {
        mPercentageTextView.setText(text);
        invalidate();
    }

    /**
     * Determine the thickness of the mPercentage pie bar
     *
     * @param padding value ranging from 1 to the width of the widget
     */
    @SuppressWarnings("unused")
    public void setPieInnerPadding(final int padding) {
        mInnerCirclePadding = padding;
        invalidate();
    }

    /**
     * Get the thickness of the mPercentage pie bar
     */
    public int getPieInnerPadding() {
        return mInnerCirclePadding;
    }

    /**
     * Get the percentage
     */
    @SuppressWarnings("unused")
    public float getPercentage() {
        return mPercentage * mMaxPercentage;
    }

    /**
     * Set a mPercentage between 0 and mMaxPercentage
     *
     * @param percentage any float value from 0 to mMaxPercentage
     */
    @SuppressWarnings("unused")
    public void setPercentage(final float percentage) {
        mPercentage = percentage / mMaxPercentage;
        mAngle = (CIRCLE_DEGREES * mPercentage);
        setPercentageText();
        invalidate();
    }

    /**
     * Set the size of the inner text of the widget
     *
     * @param size any valid float
     */
    @SuppressWarnings("unused")
    public void setPercentageTextSize(final float size) {
        mPercentageTextView.setTextSize(size);
        invalidate();
    }

    /**
     * Determine the background color of the center of the widget where the label is shown
     *
     * @param color The new color (including alpha) to set in the paint.
     */
    @SuppressWarnings("unused")
    public void setInnerBackgroundColor(@ColorInt final int color) {
        mCenterFill.setColor(color);
    }

    /**
     * Determine the background color of the bar representing the mPercentage set to the widget
     *
     * @param color The new color (including alpha) to set in the paint.
     */
    @SuppressWarnings("unused")
    public void setPercentageBackgroundColor(@ColorInt final int color) {
        mPercentageFill.setColor(color);
    }

    /**
     * Determine the background color of the back of the widget
     *
     * @param color The new color (including alpha) to set in the paint.
     */
    @SuppressWarnings("unused")
    public void setMainBackgroundColor(@ColorInt final int color) {
        mBackgroundFill.setColor(color);
    }

    /**
     * Determine the color of the text
     *
     * @param color The new color (including alpha) to set in the paint.
     */
    @SuppressWarnings("unused")
    public void setTextColor(@ColorInt final int color) {
        mPercentageTextView.setTextColor(color);
    }

    /**
     * Set the max value (default = 100)
     *
     * @param maxPercentage max value.
     */
    @SuppressWarnings("unused")
    public void setMaxPercentage(final float maxPercentage) {
        mMaxPercentage = maxPercentage;
    }
}
