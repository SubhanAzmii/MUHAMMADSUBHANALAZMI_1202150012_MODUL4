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
    //list variable
    EditText edit;
    Button btn;
    ImageView img;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarigambar);

        //inisialisasi variable pada komponen item
        img = (ImageView) findViewById(R.id.imageView);
        edit = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.cari);



        //onclick button untuk operasi pencariian
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String URL = edit.getText().toString();
                new downloadimg().execute(URL);
            }
        });

    }

    //method untuk operasi pencariian berdasarkan link yang di inputkan
    private class downloadimg extends AsyncTask<String, Void, Bitmap> {
        protected void onPreExecute(){
            //kondisi sebelum di eksekusi menampilkan loading untuk pencariian
            super.onPreExecute();
            progressDialog = new ProgressDialog(pencarigambar.this);
            //menalkukan pengesetan title untuk dialog
            progressDialog.setTitle("Downloading");
            //mekakukan set message dialog pada saat berjalan
            progressDialog.setMessage("Loading");
            progressDialog.setIndeterminate(false);
            //menampilkan dialog
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            //memberikan nilai untuk image url
            String imgURL = URL[0];
            //memberikan nilai untuk bitmap
            Bitmap bitmap = null;
            try {
                //melakukan download file dari link yang di inputkan
                InputStream input = new java.net.URL(imgURL).openStream();
                //merubah format(decode) hasil yang di download
                bitmap = BitmapFactory.decodeStream(input);
            }catch (Exception e){
                e.printStackTrace();
            }return bitmap;
        }

        protected void onPostExecute(Bitmap result){
            //melakukan set image pada imageview
            img.setImageBitmap(result);
            //mengilangkan proses dialog
            progressDialog.dismiss();
        }
    }
}
