package android.example.com.basicphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void play(View view){
        Button tappedButton = (Button) view;
        //int musicID = Integer.parseInt(tappedButton.getTag());

        mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(tappedButton.getTag().toString(), "raw", getPackageName()));

        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }


    }
}
