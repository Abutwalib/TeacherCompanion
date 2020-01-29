package com.example.teachercompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ReminderAdd extends AppCompatActivity implements View.OnClickListener {
EditText subjec,fom;
ProgressDialog pd;
    private  int notificationId=1;
TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_add);
        subjec=findViewById(R.id.subject);
        fom=findViewById(R.id.form);
        findViewById(R.id.savebtn).setOnClickListener(this);
        findViewById(R.id.viewbtn).setOnClickListener(this);
        pd=new ProgressDialog(this);
        timePicker=findViewById(R.id.timePicker);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(ReminderAdd.this,AlarmReceiver.class);
        intent.putExtra("notificationId",notificationId);
        intent.putExtra("SUBJECT",subjec.getText().toString());
        intent.putExtra("CLASS",fom.getText().toString());
        //get broadcast (context,request code,intent,flags)
        PendingIntent alarmintent=PendingIntent.getBroadcast(ReminderAdd.this,0,
       intent,PendingIntent.FLAG_CANCEL_CURRENT );
        AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
        switch (v.getId()){
            case R.id.savebtn:
                int hour=timePicker.getCurrentHour();
                int minute=timePicker.getCurrentMinute();
                //create time
                Calendar startTime= Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY,hour);
                startTime.set(Calendar.MINUTE,minute);
                startTime.set(Calendar.SECOND,0);
                long alarmstart=startTime.getTimeInMillis();
                final int id= (int) System.currentTimeMillis();
                PendingIntent alarminten= PendingIntent.getBroadcast(ReminderAdd.this,id,
                        intent,PendingIntent.FLAG_CANCEL_CURRENT);
                //set Alarm
                AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP, alarmstart,alarminten);
                Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();

                break;
            case R.id.viewbtn:
                /*alarm.cancel(alarmintent);
                Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show();*/

        }

    }
}
