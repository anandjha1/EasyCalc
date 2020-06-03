package com.example.easyCalc;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anandjha.EasyCalc.R;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity{

    private Button bt0;
    private Button bt00;
    private Button btDot;
    private Button btEqual;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;
    private Button bt6;
    private Button bt7;
    private Button bt8;
    private Button bt9;
    private Button btClear;
    private Button btPlus;
    private Button btMin;
    private Button btDiv;
    private Button btPer;
    private Button btMul;
    private Button btBack;
    private TextView tvResult;
    private String temp="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        tvResult= findViewById(R.id.tvResult);

        bt0= findViewById(R.id.bt0);
        bt00= findViewById(R.id.bt00);
        bt1= findViewById(R.id.bt1);
        bt2= findViewById(R.id.bt2);
        bt3= findViewById(R.id.bt3);
        bt4= findViewById(R.id.bt4);
        bt5= findViewById(R.id.bt5);
        bt6= findViewById(R.id.bt6);
        bt7= findViewById(R.id.bt7);
        bt8= findViewById(R.id.bt8);
        bt9= findViewById(R.id.bt9);
        btDot= findViewById(R.id.btDot);
        btDiv= findViewById(R.id.btDiv);
        btPer= findViewById(R.id.btPer);
        btPlus= findViewById(R.id.btPlus);
        btMin= findViewById(R.id.btMin);
        btMul= findViewById(R.id.btMul);
        btBack= findViewById(R.id.btBack);
        btClear= findViewById(R.id.btClear);
        btEqual= findViewById(R.id.btEqual);

        btClear.setOnClickListener(v -> {
            tvResult.setText("");
            temp="";
        });

        bt0.setOnClickListener(v -> {
            if (!temp.equals("0")){                      //Check only zero
                temp+="0";
                tvResult.setText(temp);
            }
        });

        bt1.setOnClickListener(v -> {
            temp+="1";
            tvResult.setText(temp);
        });
        bt2.setOnClickListener(v -> {
            temp+="2";
            tvResult.setText(temp);
        });
        bt3.setOnClickListener(v -> {
            temp+="3";
            tvResult.setText(temp);
        });
        bt4.setOnClickListener(v -> {
            temp+="4";
            tvResult.setText(temp);
        });
        bt5.setOnClickListener(v -> {
            temp+="5";
            tvResult.setText(temp);
        });
        bt6.setOnClickListener(v -> {
            temp+="6";
            tvResult.setText(temp);
        });
        bt7.setOnClickListener(v -> {
            temp+="7";
            tvResult.setText(temp);
        });
        bt8.setOnClickListener(v -> {
            temp+="8";
            tvResult.setText(temp);
        });
        bt9.setOnClickListener(v -> {
            temp+="9";
            tvResult.setText(temp);
        });
        bt00.setOnClickListener(v -> {
            if (temp.equals("")||temp.equals("0")){
                temp="0";
            }else {
               temp=temp+"00";
            }
            tvResult.setText(temp);
        });
        btDot.setOnClickListener(v -> {
            if (!temp.endsWith(".")){
                if (temp.equals("")) {
                    temp=temp + "0.";
                } else {
                    temp=temp + ".";
                }
                tvResult.setText(temp);
            }
        });


        //Number Keys Completed

        // Operator Keys Events Starts Here

        btPer.setOnClickListener(v -> {
            if(!(temp.equals("")||temp.endsWith("%"))){
                temp=temp+"%";
                tvResult.setText(temp);
            }
        });
        btPlus.setOnClickListener(v -> {

            if (!(temp.endsWith("+")||temp.equals("")||(temp.length()==1&&temp.equals("-")))){
                if (temp.endsWith("-")||temp.endsWith("/")||temp.endsWith("x")){ // Check for other operands to replace
                    temp = temp.substring(0,temp.length()-1)+"+";
                }else{
                    temp=temp+"+";
                }
                tvResult.setText(temp);
            }
        });
        btMin.setOnClickListener(v -> {
            if (!(temp.endsWith("-"))){
                if (temp.endsWith("+")||temp.endsWith("/")||temp.endsWith("x")){ // Check for other operands to replace
                    temp = temp.substring(0,temp.length()-1)+"-";
                }else{
                    temp=temp+"-";
                }
                tvResult.setText(temp);
            }
        });
        btMul.setOnClickListener(v -> {
            if (!(temp.endsWith("x")||temp.equals("")||(temp.length()==1&&temp.equals("-")))){
                if (temp.endsWith("-")||temp.endsWith("/")||temp.endsWith("+")){ // Check for other operands to replace
                    temp = temp.substring(0,temp.length()-1)+"x";
                }else{
                    temp=temp+"x";
                }
                tvResult.setText(temp);
            }
        });

        btDiv.setOnClickListener(v -> {
            if (!(temp.endsWith("/")||temp.equals("")||(temp.length()==1&&temp.equals("-")))){
                if (temp.endsWith("-")||temp.endsWith("+")||temp.endsWith("x")){ // Check for other operands to replace
                    temp = temp.substring(0,temp.length()-1)+"/";
                }else{
                    temp=temp+"/";
                }
                tvResult.setText(temp);
            }
        });

        //Calculation of expression should be done here

        btEqual.setOnClickListener(v -> {
            if (!(temp.equals("")||temp.endsWith("+")||temp.endsWith("-")||temp.endsWith("x")||temp.endsWith("/"))){
                temp = temp.replace("x", "*");
                temp = temp.replace("%", "/100");
                Context context = Context.enter(); //
                context.setOptimizationLevel(-1); // this is required[2]
                Scriptable scope = context.initStandardObjects();
                String finalresult = null;
                try {
                    finalresult = context.evaluateString(scope, temp, "javascript", 1, null).toString();
                } catch (Exception e) {
                    System.out.println(e);
                }
                int indexD = finalresult.indexOf(".");   //converting to integer
                String temp1 = finalresult.substring(0,indexD);
                if (finalresult.substring(indexD).equals(".0")){
                    tvResult.setText(temp1);
                }else {
                    tvResult.setText(finalresult);
                }

            }
        });


        btBack.setOnClickListener(v -> {
            if (!temp.equals("")&&temp.length()>0){
                temp=temp.substring(0,temp.length()-1);
                tvResult.setText(temp);
            }
        });


        }

    }
