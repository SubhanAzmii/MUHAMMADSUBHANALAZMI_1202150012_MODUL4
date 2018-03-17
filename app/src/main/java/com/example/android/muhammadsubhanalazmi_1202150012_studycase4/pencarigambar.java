package com.example.android.muhammadsubhanalazmi_1202150012_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class pencarigambar extends AppCompatActivity {
    EditText edit;
    Button btn;
    ImageView img;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarigambar);


        img = (ImageView) findViewById(R.id.imageView);
        edit = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.cari);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String URL = edit.getText().toString();
                new downloadimg().execute(URL);
            }
        });

    }

    private class downloadimg extends AsyncTask<String, Void, Bitmap> {
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(pencarigambar.this);
            progressDialog.setTitle("Downloading");
            progressDialog.setMessage("Loading");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            String imgURL = URL[0];

            Bitmap bitmap = null;
            try {
                InputStream input = new java.net.URL(imgURL).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            }catch (Exception e){
                e.printStackTrace();
            }return bitmap;
        }

        protected void onPostExecute(Bitmap result){
            img.setImageBitmap(result);
            progressDialog.dismiss();
        }
    }
}
