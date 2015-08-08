package com.example.bajian.intentdemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by hgx on 2015/8/8.
 */
public class MyService extends IntentService {
    public static String DOWNLOAD_FINISH="DOWNLOAD_FINISH";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(5000);
            Intent i = new Intent();
            i.setAction(DOWNLOAD_FINISH);
            i.putExtra("name", intent.getStringExtra("name"));
            MyService.this.sendBroadcast(i);
            Log.d("sendBroadcast",intent.getStringExtra("name"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
