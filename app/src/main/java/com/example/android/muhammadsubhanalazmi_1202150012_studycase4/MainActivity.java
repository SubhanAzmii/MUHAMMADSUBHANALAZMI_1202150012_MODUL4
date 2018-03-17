package com.example.android.muhammadsubhanalazmi_1202150012_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //onclick button untuk berpindah ke halaman list nama mahasiswa
    public void lsmhs(View view) {
        Intent intent = new Intent(this, listnamamahasiswa.class );
        startActivity(intent);
    }
    //onclick buton untuk berpindah ke halaman pencarian gambar
    public void carigmbr(View view) {
        Intent intent = new Intent(this, pencarigambar.class);
        startActivity(intent);
    }
}
