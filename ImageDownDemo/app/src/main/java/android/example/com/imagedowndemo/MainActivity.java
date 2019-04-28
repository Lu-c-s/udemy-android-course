package android.example.com.imagedowndemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView2);
    }

    public class ImageDownloader extends AsyncTask<String, Void , Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
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

    public void downloadImage(View view) {
        ImageDownloader task = new ImageDownloader();

        Bitmap myImage;

        try {
            myImage = task.execute("").get();
            imageView.setImageBitmap(myImage);
            imageView.animate().alpha(100f);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
