package com.example.rsakeygeneration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText num1,num2;
    private ImageButton reloadBtn;
    private TextView op1,op2;
    private Button submitBtn;
    int i,n,p,q,m,a1,a2,c,e,a,b,d;
    boolean flag1, flag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        reloadBtn = findViewById(R.id.reloadBtn);
        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);
        reloadBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitBtn:
                op1.setText("");
                op2.setText("");
                flag1=false;
                flag2=false;
                if(num1.getText().toString().matches("")||num2.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"Empty Input Field",Toast.LENGTH_LONG).show();
                }
                else{
                    for(i=2;i<=(Integer.parseInt(num1.getText().toString()))/2;i++){
                        if(Integer.parseInt(num1.getText().toString()) % i==0){
                            flag1 = true;
                            break;
                        }
                    }
                    for(i=2;i<=(Integer.parseInt(num2.getText().toString()))/2;i++){
                        if(Integer.parseInt(num2.getText().toString()) % i==0){
                            flag2 = true;
                            break;
                        }
                    }
                    if(!flag1&&!flag2){
                        p = Integer.parseInt(num1.getText().toString());
                        q = Integer.parseInt(num2.getText().toString());
                        n=p*q;
                        m=(p-1)*(q-1);
                        for(i=2;i<m;i++){
                            a1=coprimeCheck(n,i);
                            a2=coprimeCheck(m,i);
                            if(a1==1 && a2==1){
                                e=i; break;
                            }
                        }
                        d=modInverse(e,m,n);
                        op1.setText("Encryption Key : ("+e+" , "+n+")");
                        op2.setText("Decryption Key : ("+d+" , "+n+")");
                    }
                    else{
                        if(flag1){
                            Toast.makeText(getApplicationContext(),"Please Enter Valid Prime Number in Input Field 1",Toast.LENGTH_LONG).show();}
                        if(flag2){
                            Toast.makeText(getApplicationContext(),"Please Enter Valid Prime Number in Input Field 2",Toast.LENGTH_LONG).show();}
                    }
                }
                break;
            case R.id.reloadBtn:
                Toast.makeText(getApplicationContext(),"Application Reloaded",Toast.LENGTH_LONG).show();
                op1.setText("");
                op2.setText("");
                num1.getText().clear();
                num2.getText().clear();
                break;
        }
    }

    private int modInverse(int e, int m, int n) {
        c = e%m;
        for(i=1;i<(n*n);i++){
            if((c*i) % m == 1){
                if(i==e)
                {
                    continue;
                }
                else{
                    return i;
                }
            }
        }
        return 1;
    }

    private int coprimeCheck(int n, int i) {
        a=n; b=i;
        if(g_c_d(a,b)==1){
            return 1;
        }
        else
        {
            return 0;
        }
    }

    private int g_c_d(int a, int b) {
        if(a==0 || b==0){
            return 0;
        }
        if(a==b){
            return a;
        }
        if(a>b){
            return g_c_d(a-b,b);
        }
        return g_c_d(a,b-a);
    }
}
