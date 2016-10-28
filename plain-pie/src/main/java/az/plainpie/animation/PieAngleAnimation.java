package az.plainpie.animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import az.plainpie.PieView;

/**
 * @author Alejandro Zürcher (alejandro.zurcher@gmail.com)
 */
public class PieAngleAnimation extends Animation {

    private PieView arcView;

    private float oldAngle;
    private float newAngle;

    public PieAngleAnimation(PieView arcView, int newAngle) {
        this.oldAngle = arcView.getPieAngle();
        this.newAngle = newAngle;
        this.arcView = arcView;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = 0 + ((newAngle - oldAngle) * interpolatedTime);

        arcView.setPieAngle(angle);
        arcView.requestLayout();
    }
}