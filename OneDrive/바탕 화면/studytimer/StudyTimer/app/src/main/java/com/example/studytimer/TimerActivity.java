package com.example.studytimer;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContainer;

public class TimerActivity extends TabActivity {

    int timerTime;
    int resetTime;
    TimerHandler timer;
    Button button, button2, Kbtn, Ebtn;
    TextView textView;
    Button backbtn;
    boolean isRunning = true;
    int status =0;
    TextView text;
    double storedValue;
    char curOperator;


    public void onStartPauseButton(View view){
        if(status == 0){
            status = 1;
            timer.sendEmptyMessage(0);
            button.setText("일시정지");
        }else if(status == 1){
            status =0;
            timer.sendEmptyMessage(1);
            button.setText("시작");
        }
    }

    public void onStop(View view){
        status = 0;
        timer.sendEmptyMessage(2);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        setTitle("타이틀");

        text = findViewById(R.id.text);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        textView = (TextView)findViewById(R.id.textView);
        backbtn = (Button) findViewById(R.id.backbtn);
        Kbtn = (Button)findViewById(R.id.Kbtn);
        Ebtn = (Button)findViewById(R.id.Ebtn);
        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");

        if(subject.equals("국어")){
            timerTime = 4800;
            resetTime = 4800;
        }
        else if(subject.equals("수학")){
            timerTime = 6000;
            resetTime = 6000;
        }
        else if(subject.equals("영어")){
            timerTime = 4200;
            resetTime = 4200;
        }
        else if(subject.equals("탐구1")){
            timerTime = 1800;
            resetTime = 1800;
        }
        else if(subject.equals("탐구2")){
            timerTime = 1800;
            resetTime = 1800;
        }

        textView.setText("남은시간 : "+timerTime/60+":"+timerTime%60);
        timer = new TimerHandler();



        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(TimerActivity.this);
                dlg.setTitle("돌아가기");
                dlg.setMessage("타이머를 종료하고 돌아가시겠습니까?");
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dlg.setNegativeButton("취소",null);
                dlg.show();
            }
        });
        Kbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://ko.dict.naver.com/#/main");
                Intent dic = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(dic);
            }
        });
        Ebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://en.dict.naver.com/#/main");
                Intent dic = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(dic);
            }
        });


        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpecTab1 = tabHost.newTabSpec("TAB1").setIndicator("달력");
        tabSpecTab1.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab1);

        TabHost.TabSpec tabSpecTab2 = tabHost.newTabSpec("TAB2").setIndicator("계산기");
        tabSpecTab2.setContent(R.id.tab2);
        tabHost.addTab(tabSpecTab2);

        TabHost.TabSpec tabSpecTab3 = tabHost.newTabSpec("TAB3").setIndicator("사전");
        tabSpecTab3.setContent(R.id.tab3);
        tabHost.addTab(tabSpecTab3);


        tabHost.setCurrentTab(0);



    }
    public void cal(View view){
        String current = text.getText().toString();
        switch(view.getId()){
            case R.id.btn0:
                text.setText(current + "0");
                break;
            case R.id.btn1:
                text.setText(current + "1");
                break;
            case R.id.btn2:
                text.setText(current + "2");
                break;
            case R.id.btn3:
                text.setText(current + "3");
                break;
            case R.id.btn4:
                text.setText(current + "4");
                break;
            case R.id.btn5:
                text.setText(current + "5");
                break;
            case R.id.btn6:
                text.setText(current + "6");
                break;
            case R.id.btn7:
                text.setText(current + "7");
                break;
            case R.id.btn8:
                text.setText(current + "8");
                break;
            case R.id.btn9:
                text.setText(current + "9");
                break;
            case R.id.btndot:
                text.setText(current + ".");
                break;
            case R.id.btnplus:
                if(current.equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    storedValue=Double.parseDouble(current);
                    curOperator='+';
                    text.setText("");
                    break;
                }
            case R.id.subbtn:
                if(current.equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    storedValue=Double.parseDouble(current);
                    curOperator='-';
                    text.setText("");
                    break;
                }
            case R.id.mulbtn:
                if(current.equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    storedValue=Double.parseDouble(current);
                    curOperator='*';
                    text.setText("");
                    break;
                }
            case R.id.divbtn:
                if(current.equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    storedValue=Double.parseDouble(current);
                    curOperator='/';
                    text.setText("");
                    break;
                }
            case R.id.btnOk:
                if(current.equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    double result = 0;
                    double thisValue = Double.parseDouble(text.getText().toString());
                    switch(curOperator){
                        case '+':
                            result = storedValue+thisValue;
                            break;
                        case '-':
                            result = storedValue - thisValue;
                            break;
                        case '*':
                            result = storedValue * thisValue;
                            break;
                        case '/':
                            result = storedValue / thisValue;
                            break;
                    }
                    text.setText(""+result);
                    storedValue=0.0;
                    break;
                }
            case R.id.clearbtn:
                text.setText("");
                storedValue=0.0;
                break;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(timer != null)
            timer.removeMessages(0);
        isRunning = false;
    }

    class TimerHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    if(timerTime == 0){
                        textView.setText("남은시간 : " + timerTime);
                        removeMessages(0);
                        break;
                    }

                    textView.setText("남은시간 : " + timerTime/60+":"+timerTime%60);
                    timerTime--;
                    sendEmptyMessageDelayed(0,1000);
                    Log.d("text","msg.shat:0 time ="+timerTime);
                    break;

                case 1:
                    removeMessages(0);
                    textView.setText("남은시간 : " + timerTime/60+":"+timerTime%60);
                    Log.d("test","msg.what:1 time =" + timerTime);
                    break;

                case 2:
                    removeMessages(0);
                    timerTime = resetTime;
                    textView.setText("남은시간 : " + timerTime/60+":"+timerTime%60);
                    button.setText("시작");
                    Log.d("test","msg.what:2 time = " + timerTime);
                    break;
            }
        }
    }

}
