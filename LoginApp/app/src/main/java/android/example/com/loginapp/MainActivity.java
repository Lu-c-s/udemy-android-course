package android.example.com.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLogIn(View view){
        EditText usernameField = (EditText) findViewById(R.id.usernameField);
        EditText passwordField = (EditText) findViewById(R.id.passwordField);

        String username =  usernameField.getText().toString();
        String password =  passwordField.getText().toString();

        Log.i("Values", "Username: " + username + "\n" + "Passowrd : " + password);

        Toast.makeText(this, "Hi " +  username, Toast.LENGTH_SHORT).show();

    }

    public void onClickForgotPass(View view){
        Log.i("Info", "Clicked forgot password");
    }
}
