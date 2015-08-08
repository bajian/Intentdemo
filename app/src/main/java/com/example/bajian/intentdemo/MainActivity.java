package com.example.bajian.intentdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static String DOWNLOAD_BROADCAST="DOWNLOAD_BROADCAST";
    private int count=0;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini();
    }

    private void ini(){
         ll = (LinearLayout)findViewById(R.id.ll);

        //×¢²á¹ã²¥
        IntentFilter iF = new IntentFilter();
        iF.addAction(MyService.DOWNLOAD_FINISH);
        registerReceiver(receiver,iF);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void send(View view){

        Intent i = new Intent(MainActivity.this,MyService.class);
        i.setAction(DOWNLOAD_BROADCAST);
        count++;
        String str = "down" + count;
        i.putExtra("name", str);
        MainActivity.this.startService(i);
        TextView tv = new TextView(this);
        tv.setText(str+" begin...");
        tv.setTag(str);
        ll.addView(tv);
//        MainActivity.this.sendBroadcast(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("onReceive ",intent.getStringExtra("name"));
            TextView tv = (TextView)ll.findViewWithTag(intent.getStringExtra("name"));
            tv.setText(intent.getStringExtra("name")+"download finish!");
        }
    };
}
