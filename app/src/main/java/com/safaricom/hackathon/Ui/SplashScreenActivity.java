package com.safaricom.hackathon.Ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import com.safaricom.hackathon.MainActivity;
import com.safaricom.hackathon.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity {

    //Initialization
    private Timer timer;
    private ProgressBar progressBar;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar= findViewById(R.id.pdialog);
        progressBar.setProgress(0);

        final long period =50;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 50 ms
                if (i<50){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                }else{
                    //closing the timer
                    timer.cancel();
                    Intent intent =new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    // close splash screen activity
                    finish();
                }
            }
        }, 0, period);
    }

}