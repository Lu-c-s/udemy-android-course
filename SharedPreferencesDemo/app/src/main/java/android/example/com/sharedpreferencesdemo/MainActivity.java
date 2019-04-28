package android.example.com.sharedpreferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("android.example.com.sharedpreferencesdemo", Context.MODE_PRIVATE);

        //sharedPreferences.edit().putString("username","lucas").apply();

        //String username = sharedPreferences.getString("username","");

        //Log.i("username",username);

        //sharedPreferences.edit().put

        ArrayList<String> friends = new ArrayList<>();

        friends.add("Name1");
        friends.add("Name2");
        friends.add("Name3");
        friends.add("Name4");

        try{
            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();
        }catch( Exception e){
            e.printStackTrace();
        }


        ArrayList<String> newFriends = new ArrayList<>();
        try{
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
        }catch(Exception e){
            e.printStackTrace();
        }

        Log.i("new friends",newFriends.toString());



    }
}
