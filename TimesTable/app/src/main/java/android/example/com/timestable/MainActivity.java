package android.example.com.timestable;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int maxTimeTableValue = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeTimeProgram();
    }

    public ArrayList<String> generateTimeTable(int number) {
        ArrayList<String> timesTable = new ArrayList<>();

        for(int i = 1 ; i <= maxTimeTableValue ; i++){
            timesTable.add( i + " x " + number + " = " + Integer.toString(i*number) );
        }

        return timesTable;
    }

    public void setTimeTable(int number){
        ListView myListView   = findViewById(R.id.myListView);

        ArrayList<String> timeTable = generateTimeTable(number);

        ArrayAdapter timeTableAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timeTable);

        myListView.setAdapter(timeTableAdapter);
    }

    public void initializeTimeProgram(){
        final SeekBar numberControl = findViewById(R.id.numberSeekbar);
        final TextView actualNumber = findViewById(R.id.timesValue);

        actualNumber.setText(Integer.toString(numberControl.getProgress()));

        final int numberControlCorrection = 1;
        numberControl.setProgress(0);

        setTimeTable(1);

        numberControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int val = numberControl.getProgress();

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                val = progress + numberControlCorrection;
                actualNumber.setText(Integer.toString(val));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setTimeTable(val);
            }
        });



    }
}
