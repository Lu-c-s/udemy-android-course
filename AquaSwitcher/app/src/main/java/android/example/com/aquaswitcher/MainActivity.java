package android.example.com.aquaswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean toggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSwitch(View view){
        Log.i("Info", "Button Pressed");

        int imageId =  R.drawable.aqua1;

        ImageView aquaView = (ImageView) findViewById(R.id.aquaImageView);

        if(!toggle){
            imageId = R.drawable.aqua1;
            toggle = true;

        } else {
            imageId = R.drawable.aqua2;
            toggle = false;
        }

        aquaView.setImageResource(imageId);
    }
}
