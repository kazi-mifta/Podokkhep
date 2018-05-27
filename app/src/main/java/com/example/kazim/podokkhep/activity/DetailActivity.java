package com.example.kazim.podokkhep.activity;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.kazim.podokkhep.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DetailActivity extends AppCompatActivity {

    TextView textName, textDetail, textPhone, textAdress;

    ImageView imageView;

    ProgressBar progressBar;

    String name = "";
    String phone = "";

    String adress = "";
    String imgUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textName = findViewById(R.id.txt_name);
        textDetail = findViewById(R.id.txt_detail);
        textPhone = findViewById(R.id.txt_phone);
        textAdress = findViewById(R.id.txt_adress);

        imageView = (ImageView) findViewById(R.id.img_view);

        progressBar = findViewById(R.id.progress);


        Bundle bundle = getIntent().getExtras();

        name = bundle.getString("name");
        phone = bundle.getString("phone");

        textName.setText("Name : "+ bundle.getString("name"));
        textDetail.setText(bundle.getString("details"));
        textPhone.setText("Phone : 0"+ bundle.getString("phone"));


        new JSONParse().execute();


    }


    protected class JSONParse extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... params) {

            String str = "http://nsc.comeze.com/wp-content/themes/dhak2/amit.php?name=" + name + "&phone=" + phone;

            URLConnection urlConn = null;

            Log.d("URL",str);

            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(str);
                urlConn = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

                StringBuffer stringBuffer = new StringBuffer();
                String line;


                stringBuffer.append(bufferedReader.readLine());

                return new JSONArray(stringBuffer.toString());

            } catch (Exception ex) {
                Log.e("App", "yourDataTask", ex);
                return null;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        @Override
        protected void onPostExecute(JSONArray response) {

            if (response != null) {
                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject oneObject = response.getJSONObject(i);
                        // Pulling items from the array
                        adress = oneObject.getString("adress");
                        imgUrl = oneObject.getString("image");


                    } catch (JSONException e) {


                    }

                }

                Log.e("App", "Success: " + response.toString());
                textAdress.setText("Adress : "+ adress);


                Glide.with(DetailActivity.this)
                        .load(imgUrl)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.setVisibility(View.GONE);
                                return false;
                            }
                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.setVisibility(View.GONE);
                                imageView.setVisibility(View.VISIBLE);
                                return false;
                            }
                        }).into(imageView);


            }
        }
    }
}