package android.example.com.otakutimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    SeekBar timerControl;
    TextView timer;
    CountDownTimer counter;
    Button stopButton;
    Button startButton;
    MediaPlayer mediaPlayer;
    boolean isSoundPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerControl = findViewById(R.id.timeSeekBar);
        timer = findViewById(R.id.timerTextView);
        mediaPlayer = MediaPlayer.create(this, R.raw.alert);

        int initialTime = 0; // 0 minutes
        int maxTime = 600000; // 10 Minute

        timerControl.setMax(maxTime);

        timerControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timer.setText(getTimerValue(progress));
                if(isSoundPlaying){
                    mediaPlayer.pause();
                    isSoundPlaying = false;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void startTimer(final View view){
        int selectedTime = timerControl.getProgress();
        if(isSoundPlaying){
            mediaPlayer.pause();
            isSoundPlaying = false;
        }

        stopButton = findViewById(R.id.stopButton);
        final ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.chika2);


        timerControl.setEnabled(false);
        view.setVisibility(View.INVISIBLE);

        stopButton.setVisibility(View.VISIBLE);


        counter = new CountDownTimer(selectedTime, 1000){
            @Override
            public void onTick(long millisecondsUtilGone){
                timer.setText(getTimerValue(millisecondsUtilGone));
                timerControl.setProgress((int) millisecondsUtilGone);
            }

            @Override
            public void onFinish(){
                timerControl.setEnabled(true);
                view.setVisibility(View.VISIBLE);
                image.setImageResource(R.drawable.fujiwara);
                stopButton.setVisibility(View.INVISIBLE);
                if(!isSoundPlaying) {
                    mediaPlayer.start();
                    isSoundPlaying = true;
                }
            }
        };

        counter.start();
    }

    public void stopTimer(View view){
        startButton = findViewById(R.id.startButton);

        if(isSoundPlaying) {
            mediaPlayer.start();
            isSoundPlaying = true;
        }

        counter.cancel();
        timerControl.setEnabled(true);

        stopButton.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);
    }

    public String getTimerValue(long millisecondsValue){
        long minutes = (millisecondsValue / 1000) / 60;
        long seconds = (millisecondsValue / 1000) % 60;

        String secondStr = String.valueOf(seconds);

        if(seconds < 10)
            secondStr = "0" + secondStr;

        return minutes + ":" + secondStr;
    }


}
