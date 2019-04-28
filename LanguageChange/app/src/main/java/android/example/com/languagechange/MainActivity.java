package android.example.com.languagechange;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String selectedLanguage = "";
    SharedPreferences sharedPreferences;
    TextView language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        language = findViewById(R.id.text);
        sharedPreferences = this.getSharedPreferences("android.example.com.languagechange", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("firstTime", true)){
            showLanguagePreference();
            sharedPreferences.edit().putBoolean("firstTime",false).apply();
        } else {
            selectedLanguage = sharedPreferences.getString("language","English");
            language.setText(selectedLanguage);
        }
    }

    public void showLanguagePreference(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Choose your language")
                .setMessage("which language do you want to use?")
                .setPositiveButton("Portuguese", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveLanguageToPreferences("Portuguese");
                    }
                })
                .setNegativeButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveLanguageToPreferences("English");
                    }
                })
                .show();
    }

    public void saveLanguageToPreferences (String languageStr) {
        sharedPreferences.edit().putString("language",languageStr).apply();
        language.setText(languageStr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.english:
                saveLanguageToPreferences("English");
                return true;
            case R.id.portuguese:
                saveLanguageToPreferences("Portuguese");
                return true;
            default:
                return false;
        }
    }
}
