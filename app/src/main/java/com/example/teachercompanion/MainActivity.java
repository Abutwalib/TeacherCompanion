package com.example.teachercompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd=new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.getWindow();
        pd.setMessage("Loading");
        pd.setCancelable(false);
        pd.show();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

               pd.setProgress(1);
                Intent intent=new Intent(MainActivity.this,Menu.class);
                startActivity(intent);
                pd.dismiss();
            }
        },5000);
    }
}
