package android.example.com.memorableplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {
    ArrayList myLocations;
    ListView myLocationsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLocations = new ArrayList<>(asList("Add new place..."));
        myLocationsListView = findViewById(R.id.myLocations);
        loadListView(myLocations);
    }

    public void loadListView(final ArrayList list){
        ArrayAdapter myListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);

        myLocationsListView.setAdapter(myListAdapter);

        myLocationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                } else {

                }

            }
        });

    }

}
