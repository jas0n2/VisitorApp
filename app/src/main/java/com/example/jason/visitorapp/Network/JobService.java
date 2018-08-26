package com.example.jason.visitorapp.Network;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.util.Log;

public class JobService extends android.app.job.JobService {
    private static  final String TAG ="JobServic";
    private boolean jobcancel = false;
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG,"Started");
        backGroundJob(jobParameters);
        return true;
    }

    public  void backGroundJob(final JobParameters parameters){
        new Thread(new Runnable() {
            @Override
            public void run() {
  for(int i=0; i<10;i++){
      Log.d(TAG,"count"+i);
      if(jobcancel){
          return;
      }
      try {
          Thread.sleep(1000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
                Log.d(TAG,"finish");
  jobFinished(parameters,false);

            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG,"job cancel");
        jobcancel = true;
        return true;
    }
}
