package com.example.liuk.secret.atys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liuk.secret.Config;
import com.example.liuk.secret.R;
import com.example.liuk.secret.net.GetCode;
import com.example.liuk.secret.net.Login;
import com.example.liuk.secret.tools.MD5Tool;

import org.w3c.dom.Text;

public class AtyLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_login);

        etPhone = (EditText)findViewById(R.id.etPhoneNum);
        etCode = (EditText)findViewById(R.id.etCode);

        findViewById(R.id.btnGetCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etPhone.getText())){
                    Toast.makeText(AtyLogin.this, R.string.phone_num_cannot_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }

                final ProgressDialog pd = ProgressDialog.show(AtyLogin.this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
                new GetCode(etPhone.getText().toString(), new GetCode.SuccessCallback(){

                    @Override
                    public void onSuccess() {
                        pd.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.success_to_get_code, Toast.LENGTH_LONG).show();

                    }
                },new GetCode.FailCallback(){

                    @Override
                    public void onFail() {
                        pd.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.fail_to_get_code, Toast.LENGTH_LONG).show();

                    }
                });
            }


        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etPhone.getText())){
                    Toast.makeText(AtyLogin.this, R.string.phone_num_cannot_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(etCode.getText())){
                    Toast.makeText(AtyLogin.this, R.string.code_can_not_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }

                new Login(MD5Tool.md5(etPhone.getText().toString()), etCode.getText().toString(), new Login.SuccessCallback(){
                    @Override
                    public void onSuccess(String token) {
                        Config.cacheToken(AtyLogin.this, token);
                        Config.cachePhoneNum(AtyLogin.this, etPhone.getText().toString());

                        Intent i = new Intent(AtyLogin.this, AtyTimeline.class);
                        i.putExtra(Config.KEY_TOKEN, token);
                        i.putExtra(Config.KEY_PHONE_NUM, etPhone.getText().toString());

                        startActivity(i);

                        finish();
                    }
                },new Login.FailCallback(){
                    @Override
                    public void onFail() {
                        Toast.makeText(AtyLogin.this, R.string.fail_to_login,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private EditText etPhone = null,etCode;
}
