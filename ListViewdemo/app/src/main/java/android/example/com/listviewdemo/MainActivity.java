package android.example.com.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = findViewById(R.id.myListView);

        final ArrayList<String> myFriends = new ArrayList<>(asList("Lucas", "Gustavo", "Eduardo", "Carol", "Rafael", "Cassio", "Ana", "Matheus"));

        ArrayAdapter myListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, myFriends);

        myListView.setAdapter(myListAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Clicked on " + myFriends.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
