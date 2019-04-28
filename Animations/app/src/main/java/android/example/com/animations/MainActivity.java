package android.example.com.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fade();
    }

    public void fade(){
        ImageView imageView = findViewById(R.id.imageView);
        //ImageView imageView2 = findViewById(R.id.imageView2);
        //imageView.setAlpha(0);
        imageView.setX(-1000);

        imageView.animate().setStartDelay(1000).translationXBy(1000).rotation(3600).setDuration(2000);

    }
}
