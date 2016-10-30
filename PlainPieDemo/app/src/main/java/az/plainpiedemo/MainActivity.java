package az.plainpiedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PieView unanimatedPie = (PieView) findViewById(R.id.unanimated_pie_view);
        // Change the color fill of the bar representing the current percentage
        unanimatedPie.setPercentageBackgroundColor(getResources().getColor(R.color.red));
        // Change the color of the text of the widget
        unanimatedPie.setTextColor(getResources().getColor(R.color.yellow));

        PieView animatedPie2 = (PieView) findViewById(R.id.animated_pie_view_2);
        // Change the color of the text of the widget
        animatedPie2.setPercentageBackgroundColor(getResources().getColor(R.color.yellow));

        PieView animatedPie1 = (PieView) findViewById(R.id.animated_pie_view_1);
        // Change the color fill of the bar representing the current percentage
        animatedPie1.setPercentageBackgroundColor(getResources().getColor(R.color.green));

        PieAngleAnimation animation2 = new PieAngleAnimation(animatedPie2);
        animation2.setDuration(5000);
        animatedPie2.startAnimation(animation2);

        PieAngleAnimation animation1 = new PieAngleAnimation(animatedPie1);
        animation1.setDuration(3000);
        animatedPie1.startAnimation(animation1);
    }
}
