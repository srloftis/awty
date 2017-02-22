package edu.washington.srloftis.arewethereyet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;


public class MainActivity extends Activity{

    Button startBtn;
    EditText messageEdit;
    EditText phoneEdit;
    EditText minsEdit;
    int interval;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    public final static String MESSAGE = "awty.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = (Button) findViewById(R.id.startBtn);
        messageEdit = (EditText) findViewById(R.id.messageEdit);
        phoneEdit = (EditText) findViewById(R.id.phoneEdit);
        minsEdit = (EditText) findViewById(R.id.minsEdit);
        alarmMgr = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (messageEdit.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "You must enter a message", Toast.LENGTH_LONG).show();
                else if (phoneEdit.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "You must enter a phone number", Toast.LENGTH_LONG).show();
                else if (minsEdit.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "You must enter a frequency", Toast.LENGTH_LONG).show();
                else {
                    try{
                        interval = Integer.parseInt(minsEdit.getText().toString());
                        if (interval <= 0)
                           Toast.makeText(MainActivity.this, "Minutes must be a positive integer", Toast.LENGTH_LONG).show();
                       else{
                            if(startBtn.getText().equals("Start")) {
                                startBtn.setText("Stop");
                                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                                intent.putExtra(MESSAGE, phoneEdit.getText() + ": " + messageEdit.getText());
                                alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                                interval = interval * 60 * 1000;
                                alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                        SystemClock.elapsedRealtime() + interval, interval, alarmIntent);
                            }else if(startBtn.getText().equals("Stop")){
                                startBtn.setText("Start");
                                if (alarmMgr!= null) {
                                    alarmMgr.cancel(alarmIntent);
                                }
                            }
                        }
                    }catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Minutes must be a positive integer", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
