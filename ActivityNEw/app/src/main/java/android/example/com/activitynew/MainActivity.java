package android.example.com.activitynew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    ListView friends;
    final ArrayList<String> myFriends = new ArrayList<>(asList("Lucas", "Gustavo", "Eduardo", "Carol", "Rafael", "Cassio", "Ana", "Matheus"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        friends = findViewById(R.id.listView);
        loadListView(myFriends);
    }

    public void loadListView(final ArrayList list){

        ArrayAdapter myListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        friends.setAdapter(myListAdapter);

        friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                String name = (String) list.get(position);
                intent.putExtra("name",name);

                startActivity(intent);
            }
        });
    }

    public void goNext(View view) {
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("age",28);
        startActivity(intent);
    }
}
