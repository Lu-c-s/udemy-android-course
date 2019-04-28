package android.example.com.weatherapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    ImageView background;
    EditText cityEditText;
    TextView place,weather,description;
    String API_KEY = "4845c9cf439e71431d887b002d70c31d";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background =findViewById(R.id.backgroundImageView);
        cityEditText = findViewById(R.id.cityEditText);
        description = findViewById(R.id.weatherTextView2);
        place = findViewById(R.id.placeTextView);
        weather = findViewById(R.id.weatherTextView);

        String url = "https://images.unsplash.com/photo-1552009385-41fe264032dc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1980&q=1080";
        setBackground(url);
    }

    public void setBackground(String imageUrl) {
        try{
            Bitmap image;
            getImage imageBack = new getImage();
            image = imageBack.execute(imageUrl).get();

            background.setImageBitmap(image);
        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void getWeather(String location){
        try{
            getLocationWeather lw = new getLocationWeather();

            lw.execute(location).get();

            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(cityEditText.getWindowToken(), 0);
        }catch(Exception e){
            Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void findWeather(View view){
         String userInput = cityEditText.getText().toString();
        String encodedCityName = null;
        try {
            encodedCityName = URLEncoder.encode(userInput, "UTF-8");
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=" + API_KEY;

         getWeather(url);
         place.setText(userInput);
    }

    public class getImage extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... urls) {
           try{
               URL url = new URL(urls[0]);
               HttpURLConnection connection = (HttpURLConnection) url.openConnection();

               InputStream in = connection.getInputStream();
               Bitmap myBitmap = BitmapFactory.decodeStream(in);

               return myBitmap;
           } catch (Exception e) {
               e.printStackTrace();
               return null;
           }
        }
    }



    public class getLocationWeather extends AsyncTask<String, Void , String>{
        @Override
        protected String doInBackground(String... urls) {
            String result = "";

            try{
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;

                    data = reader.read();
                }

                return result;

            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");

                JSONArray arr = new JSONArray(weatherInfo);

                JSONObject jsonPart = arr.getJSONObject(0);
                weather.setText(jsonPart.getString("main"));
                description.setText(jsonPart.getString("description"));


                //JSONArray arr = new JSONArray(urls);

            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }



}
