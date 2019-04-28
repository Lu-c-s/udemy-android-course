package android.example.com.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ImageView celebImage;
    LinearLayout linearLayout;
    ArrayList<Celebrity> celebrities = new ArrayList<>();
    ArrayList<String> celebUrls = new ArrayList<>();
    ArrayList<String> celebName = new ArrayList<>();
    String rightAnswer = null;
    Random r;
    int celebritiesSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = new Random();

        celebImage = findViewById(R.id.imageView3);
        linearLayout = findViewById(R.id.linearLayout);

        loadCelebrities();

        celebritiesSize = celebrities.size();

        setQuestion(celebrities.get(r.nextInt(celebritiesSize)));
    }

    public void chooseAnswer(View view) {
        Button b  = (Button) view;
        String buttonText = b.getText().toString();

        if(buttonText.equals(rightAnswer)){
            Toast.makeText(this, "Right :)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wroooong :)", Toast.LENGTH_SHORT).show();
        }

        setQuestion(celebrities.get(r.nextInt(celebritiesSize)));
    }


    public void loadCelebrities(){
        GetCelebContent task = new GetCelebContent();
        String result = null;
        try{
            result = task.execute("http://www.posh24.se/kandisar").get();
            String[] splitResult = result.split("<div class=\"articleContainer contentBlock \">");

            Pattern p = Pattern.compile("img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[1]);

            while(m.find()){
                celebUrls.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[1]);

            while(m.find()){
                celebName.add(m.group(1));
            }

            for(int i = 0 ; i < celebUrls.size() ; i++){
                Celebrity c = new Celebrity();
                c.setImage(celebUrls.get(i));
                c.setName(celebName.get(i));

                celebrities.add(c);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setQuestion(Celebrity c){
        rightAnswer = c.getName();
        Bitmap myImage;

        try{
            GetCelebImage task = new GetCelebImage();
            myImage = task.execute(c.getImage()).get();
            celebImage.setImageBitmap(myImage);

            Collections.shuffle(celebrities);
            int random = r.nextInt(4);

            for(int i = 0 ; i < linearLayout.getChildCount() ; i++){
                if(i == random){
                    Button b = (Button) linearLayout.getChildAt(i);
                    b.setText(rightAnswer);
                } else {
                    Button b = (Button) linearLayout.getChildAt(i);
                    b.setText(celebrities.get(i).getName());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public class GetCelebImage extends AsyncTask<String , Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);

                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public class GetCelebContent extends AsyncTask<String, Void , String> {
        @Override
        protected String doInBackground(String... urls) {
            URL url;
            HttpURLConnection connection = null;
            String result = "";

            try{
                url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;

                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


}
