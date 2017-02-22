package edu.washington.srloftis.arewethereyet;

/**
 * Created by Sarah on 2/21/2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.d("AlarmReceiver", "message received");
        String text = intent.getStringExtra(MainActivity.MESSAGE);
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}