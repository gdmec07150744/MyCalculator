package com.example.min123.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText weight;
    private Button count;
    private RadioButton male;
    private RadioButton female;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weight= (EditText) findViewById(R.id.weight);
        count= (Button) findViewById(R.id.count);
        male= (RadioButton) findViewById(R.id.male);
        female= (RadioButton) findViewById(R.id.female);
        result= (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }
    private void registerEvent(){
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!weight.getText().toString().trim().equals("")){
                    if(male.isChecked()||female.isChecked()){
                        Double wei=Double.parseDouble(weight.getText().toString());
                        StringBuffer sb=new StringBuffer();
                        if(male.isChecked()){
                            sb.append("男性标准身高");
                            double re=evaluateHeight(wei,"男");
                            sb.append(re+"厘米");
                        }else{
                            sb.append("女标准身高");
                            double re=evaluateHeight(wei,"女");
                            sb.append(re+"厘米");
                        }
                            result.setText(sb.toString());
                    }else{
                        showMessage("请选择性别");
                    }
                }else{
                    showMessage("请输入体重");
                }
            }
        });
    }
    private void showMessage(String message){
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
    private double evaluateHeight(double wei,String sex){
        double height;
        if(sex=="男"){
            height=170-(62-wei)/0.6;
        }else{
            height=158-(52-wei)/0.5;
        }
        return  height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
