package com.example.studytimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.security.auth.Subject;

public class MainActivity extends AppCompatActivity {

    Intent mintent;
    Data[] INDEX_DATA;
    private final int ONE_DAY = 24 * 60 * 60 * 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("스터디 타이머");

        mintent = new Intent(this, MusicService.class);
        startService(mintent);
        android.util.Log.i("음악서비스","startService()");


        ActivityCompat.requestPermissions(
                this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        try {
            FileOutputStream outFs = openFileOutput("data.txt", Context.MODE_PRIVATE);
            String str = "최민식/201858034";
            outFs.write(str.getBytes());
            outFs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        final String[] SubName = {"국어","수학","영어","탐구1","탐구2"};
        ListView listview = (ListView) findViewById(R.id.SubjectView);
        TextView cDay = (TextView) findViewById(R.id.cDay);
        Button dsbtn = (Button) findViewById(R.id.dsbtn);


        INDEX_DATA = new Data[]{
                new Data("국어",ContextCompat.getDrawable(this,R.drawable.korean)),
                new Data("수학",ContextCompat.getDrawable(this,R.drawable.math)),
                new Data("영어",ContextCompat.getDrawable(this,R.drawable.english)),
                new Data("탐구1",ContextCompat.getDrawable(this,R.drawable.science1)),
                new Data("탐구2",ContextCompat.getDrawable(this,R.drawable.science2))
        };

        List<Data> list = new ArrayList<Data>();
        for(int i=0; i<INDEX_DATA.length; i++){
            list.add(INDEX_DATA[i]);
        }

        CustomAdapter adapter = new CustomAdapter(this,0,list);

        listview.setAdapter(adapter);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cDay.setVisibility(View.VISIBLE);
                cDay.setText(getDday(year, month, dayOfMonth));
            }
        }, mYear, mMonth, mDay);

        dsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dsbtn.isClickable()) {
                    datePickerDialog.show();
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),TimerActivity.class);
                intent.putExtra("subject",SubName[i]);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.iteminfo:
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("앱 정보");
                dlg.setMessage("이 앱은 수능 수험생들을 위한 모의고사 타이머 입니다");
                dlg.setPositiveButton("확인",null);
                dlg.show();
                return true;

            case R.id.itemdvl:
                try {
                    FileInputStream inFs = openFileInput("data.txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String sno = new String(txt);
                    Toast.makeText(getApplicationContext(),sno,Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            case R.id.stopmusic:
                stopService(mintent);
                android.util.Log.i("음악서비스","stopService()");
        }

        return false;
    }

    private String getDday(int a_year, int a_monthOfYear, int a_dayOfMonth) {
        // D-day 설정
        final Calendar ddayCalendar = Calendar.getInstance();
        ddayCalendar.set(a_year, a_monthOfYear, a_dayOfMonth);

        // D-day 를 구하기 위해 millisecond 으로 환산하여 d-day 에서 today 의 차를 구한다.
        final long dday = ddayCalendar.getTimeInMillis() / ONE_DAY;
        final long today = Calendar.getInstance().getTimeInMillis() / ONE_DAY;
        long result = dday - today;

        // 출력 시 d-day 에 맞게 표시
        final String strFormat;
        if (result > 0) {
            strFormat = "D-%d";
        } else if (result == 0) {
            strFormat = "D-Day";
        } else {
            result *= -1;
            strFormat = "D+%d";
        }

        final String strCount = (String.format(strFormat, result));
        return strCount;
    }
}