package com.example.android.muhammadsubhanalazmi_1202150012_studycase4;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Timer;

public class listnamamahasiswa extends AppCompatActivity {
    //list variable dan array
    String[] arry = {
            "Aan",
            "Samid",
            "Topan",
            "Ejak",
            "Sidiq",
            "Anas",
            "Ujang",
            "kampret"
    };
    ListView listview;
    ProgressBar mprogressbar;
    Button btn;
    private task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.listnamamahasiswa);
        //inisialisi variable pada komponen item
        mprogressbar = (ProgressBar) findViewById(R.id.progress);
        listview = (ListView) findViewById(R.id.list_item);
        btn = (Button) findViewById(R.id.AsynTask);
        //melakukan set array pada array adapter
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        //set button onclick untuk menampilan isi list view
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new task();
                task.execute();
            }
        });
    }

    class task extends AsyncTask <Void, String , Void> {
        //daftar variable baru khusus pada asynctask
        private ArrayAdapter<String> adapter;
        private int count = 0;
        ProgressDialog mProgressDialog =  new ProgressDialog(listnamamahasiswa.this);
        @Override
        protected void onPreExecute() {
            //melakukan set pada variable mprogressDialog
            adapter = (ArrayAdapter<String>) listview.getAdapter();
            setProgressBarIndeterminate(false);
            setProgressBarVisibility(true);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);
            //membuat listener untuk menampilan progres dialog pada saat di button di tekan
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //untuk cancel dialog bar
                    task.cancel(true);
                    //menghilangan progressbar
                    mprogressbar.setVisibility(View.VISIBLE);
                    //menghilangkan progresbar pada saat dialog bar hilang
                    dialogInterface.dismiss();
                    mprogressbar.setVisibility(View.GONE);
                }
            });
            mProgressDialog.show(); //menampilkan progressdialog
        }

        @Override
        protected Void doInBackground(Void... params) {
            //menampilalkan
            for(String item : arry){
                publishProgress(item);
                try {
                    //memberikan delay untuk loading sebanyak 2detik
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isCancelled()){
                    //memberikan kondisi jika dilakuaan cancel pada saat load data
                    task.cancel(true);
                    mprogressbar.setVisibility(View.GONE);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //meberikan value pada adapter
            adapter.add(values[0]);
            //melakukan perhitungan untuk status
            Integer status = (int) (((double)count/arry.length)*100);
            mprogressbar.setProgress(status);
            //memalkuan pesensetan status pada progresdialog
            mProgressDialog.setProgress(status);
            //menampilkan message pada progressdialog
            mProgressDialog.setMessage(String.valueOf(status+" %"));
            count++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            setProgressBarVisibility(false);
            //mengilanggan progres dialog setelah di cancel
            mProgressDialog.dismiss();
            listview.setVisibility(View.VISIBLE);
        }
    }

}