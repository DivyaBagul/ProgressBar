package com.example.progressbar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private ProgressBar progressBar;
    private int progressStatus=0;
    private TextView textView;
    private Handler handler=new Handler();//handler is a class used to manage/handle threads


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.textView2);

        //start long running operation in a background thread
        new Thread (new Runnable() {
            @Override
            public void run() {
                while(progressStatus<100){
                    progressStatus+=1;

                    //update the progress bar and display the current value in the text view
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"/"+progressBar.getMax());

                        }
                    });
                    try{
                        Thread.sleep(100);
                        //t1.interrupt();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
               // startActivities(new Intent(Loading.this,CustomAdapterActivity.class));

            }
        }).start();
    }

}
