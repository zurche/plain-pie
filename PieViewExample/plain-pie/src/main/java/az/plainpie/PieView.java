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
import az.plainpie.annotation.FloatRange;

/**
 * @author Alejandro Zürcher (alejandro.zurcher@gmail.com)
 */
public class PieView extends View {

    private RelativeLayout baseLayout;
    private TextView mPercentageTextView = null;
    private int mPercentageSize;
    private int mInnerCirclePadding;
    private Paint mPercentageFill;
    private Paint mBackgroundFill;
    private Paint mCenterFill;
    private RectF mRect;
    private RectF mRectCent;
    private float mPercentage = 0;
    private float mAngle = 0;

    public PieView(Context context) {
        super(context);

        mPercentage = 0;
        mAngle = 0;

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        baseLayout = new RelativeLayout(context);
        baseLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mPercentageTextView = new TextView(context);
        int roundedPercentage = (int) (mPercentage * 100);
        mPercentageTextView.setText(Integer.toString(roundedPercentage) + "%");
        baseLayout.addView(mPercentageTextView);
        mPercentageSize = 50;
        mPercentageTextView.setTextSize(mPercentageSize);

        init();
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupWidgetWithParams(context, attrs, 0);
        init();
    }

    public PieView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupWidgetWithParams(context, attrs, defStyle);
        init();
    }

    private void setupWidgetWithParams(Context context, AttributeSet attrs, int defStyle) {
        mPercentageTextView = new TextView(context);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PieView,
                0, 0);
        try {
            this.mPercentage = a.getFloat(R.styleable.PieView_percentage, 0) / 100;
            this.mAngle = (360 * this.mPercentage);
            this.mPercentageSize = a.getInteger(R.styleable.PieView_percentage_size, 0);
            this.mInnerCirclePadding = a.getInteger(R.styleable.PieView_inner_pie_padding, 0);
            this.mPercentageTextView.setText(a.getString(R.styleable.PieView_inner_text));
            this.mPercentageTextView.setVisibility(a.getBoolean(R.styleable.PieView_inner_text_visibility, true) ? View.VISIBLE : View.INVISIBLE);
        } finally {
            a.recycle();
        }
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        baseLayout = new RelativeLayout(context);
        baseLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (mPercentageTextView.getText().toString().trim().equals("")) {
            int roundedPercentage = (int) (mPercentage * 100);
            mPercentageTextView.setText(Integer.toString(roundedPercentage) + "%");
        }
        mPercentageTextView.setTextSize(mPercentageSize);
        baseLayout.addView(mPercentageTextView);
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

        canvas.drawArc(mRect, -90, 360, true, mBackgroundFill);

        if (mPercentage != 0) {
            canvas.drawArc(mRect, -90, this.mAngle, true, mPercentageFill);
            canvas.drawArc(mRectCent, -90, 360, true, mCenterFill);
        }

        mPercentageTextView.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        mPercentageTextView.layout(left, top, left + width, top + height);
        mPercentageTextView.setGravity(Gravity.CENTER);

        baseLayout.draw(canvas);
    }

    public float getPieAngle() {
        return this.mAngle;
    }

    public void setPieAngle(float arcAngle) {
        this.mAngle = arcAngle;
    }

    /**
     * Toggle the visibility of the inner label of the widget
     *
     * @param visibility One of {@link #VISIBLE}, {@link #INVISIBLE}, or {@link #GONE}.
     */
    public void setInnerTextVisibility(int visibility) {
        this.mPercentageTextView.setVisibility(visibility);
        invalidate();
    }

    /**
     * Set the text of the inner label of the widget
     *
     * @param text any valid String value
     */
    public void setInnerText(String text) {
        this.mPercentageTextView.setText(text);
        invalidate();
    }

    /**
     * Determine the thickness of the mPercentage pie bar
     *
     * @param padding value ranging from 1 to the width of the widget
     */
    public void setPieInnerPadding(int padding) {
        this.mInnerCirclePadding = padding;
        invalidate();
    }

    /**
     * Get the percentage
     */
    public float getPercentage() {
        return mPercentage * 100;
    }

    /**
     * Set a mPercentage between 0 and 100
     *
     * @param mPercentage any float value from 0 to 100
     */
    public void setPercentage(@FloatRange(from = 0, to = 100) float mPercentage) {
        this.mPercentage = mPercentage / 100;
        int roundedPercentage = (int) mPercentage;
        this.mPercentageTextView.setText(Integer.toString(roundedPercentage) + "%");
        this.mAngle = (360 * mPercentage);
        invalidate();
    }

    /**
     * Set the size of the inner text of the widget
     *
     * @param size any valid float
     */
    public void setPercentageTextSize(float size) {
        this.mPercentageTextView.setTextSize(size);
        invalidate();
    }

    /**
     * Determine the background color of the center of the widget where the label is shown
     *
     * @param color The new color (including alpha) to set in the paint.
     */
    public void setInnerBackgroundColor(@ColorInt int color) {
        mCenterFill.setColor(color);
    }

    /**
     * Determine the background color of the bar representing the mPercentage set to the widget
     *
     * @param color The new color (including alpha) to set in the paint.
     */
    public void setPercentageBackgroundColor(@ColorInt int color) {
        mPercentageFill.setColor(color);
    }

    /**
     * Determine the background color of the back of the widget
     *
     * @param color The new color (including alpha) to set in the paint.
     */
    public void setMainBackgroundColor(@ColorInt int color) {
        mBackgroundFill.setColor(color);
    }

    /**
     * Determine the color of the text
     *
     * @param color The new color (including alpha) to set in the paint.
     */
    public void setTextColor(@ColorInt int color) {
        mPercentageTextView.setTextColor(color);
    }
}
