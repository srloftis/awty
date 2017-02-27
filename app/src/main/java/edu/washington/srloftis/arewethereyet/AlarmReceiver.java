package edu.washington.srloftis.arewethereyet;

/**
 * Created by Sarah on 2/21/2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.util.Log;
import android.widget.Toast;
import android.net.Uri;
import java.util.ArrayList;
import android.telephony.SmsManager;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.d("AlarmReceiver", "message received");
        ArrayList<String> message = intent.getStringArrayListExtra(MainActivity.MESSAGE);
        //Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        String phone = message.get(0);
        String text = message.get(1);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone, null, text, null, null);
    }
}