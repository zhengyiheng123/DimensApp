package com.example.asus.dimensapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.dimensapp.view.MyEditText;
import com.example.asus.dimensapp.view.OnImputCompleteListener;

public class MainActivity extends AppCompatActivity {

    private EditText et_data;
    private TextView tv_info;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    String data= (String) msg.obj;
                    if (data.equals(et_data.getText().toString())){
                        tv_info.setText(data);
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_usb);
        initView();
    }

    private void initView() {
        tv_info = (TextView) findViewById(R.id.tv_info);
        et_data = (EditText) findViewById(R.id.et_data);
        et_data.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Message message=new Message();
                message.what=1;
                message.obj=s.toString();
                handler.sendMessageDelayed(message,200);
            }
        });
//        et_data.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.length()>3){
////                    String lastStr=s.subSequence(s.length()-1,s.length());
//                    if (s.subSequence(s.length()-1,s.length()).toString().equals("!")){
////                        Toast.makeText(getApplicationContext(),et_data.getText().toString(),Toast.LENGTH_SHORT).show();
//                        tv_info.setText(et_data.getText().toString());
//                        et_data.setText("");
//                        et_data.setEnabled(false);
//                    }
//                }
//
//            }
//        });
    }
    //输入框获取焦点
    public void getFocus(View v){
        et_data.setEnabled(true);
    }
    public void vertify(View view){
        tv_info.setText(et_data.getText().toString());
        et_data.setText("");
    }
}
