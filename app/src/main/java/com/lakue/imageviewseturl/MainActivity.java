package com.lakue.imageviewseturl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_image = findViewById(R.id.iv_image);

        new DownloadFilesTask().execute("https://www.shutterstock.com/ko/blog/wp-content/uploads/sites/17/2017/12/shutterstock_116222914.jpg?w=760&h=492");
    }

    private class DownloadFilesTask extends AsyncTask<String,Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                String img_url = strings[0]; //url of the image
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            // doInBackground 에서 받아온 total 값 사용 장소
            iv_image.setImageBitmap(result);
        }
    }
}
