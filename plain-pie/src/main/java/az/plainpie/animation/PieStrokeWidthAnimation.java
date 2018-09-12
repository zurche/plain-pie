package az.plainpie.animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import az.plainpie.PieView;

/**
 * @author Alejandro Zürcher (alejandro.zurcher@gmail.com)
 */
@SuppressWarnings("unused")
public class PieStrokeWidthAnimation extends Animation {

    private PieView arcView;
    private int oldWidth;

    public PieStrokeWidthAnimation(PieView arcView) {
        this.oldWidth = arcView.getPieInnerPadding();
        this.arcView = arcView;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        int width = (int)(oldWidth * interpolatedTime);
        arcView.setPieInnerPadding(width);
        arcView.requestLayout();
    }
}