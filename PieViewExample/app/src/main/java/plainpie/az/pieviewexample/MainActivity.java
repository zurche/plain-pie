package plainpie.az.pieviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PieView pieView = (PieView) findViewById(R.id.pieView);
        PieView pieViewAnimated = (PieView) findViewById(R.id.pieViewAnimated);

        PieAngleAnimation animation = new PieAngleAnimation(pieViewAnimated, (75/100)*360);
        animation.setDuration(1000);
        pieViewAnimated.startAnimation(animation);



    }
}
