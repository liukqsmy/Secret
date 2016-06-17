package com.example.liuk.secret.atys;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liuk.secret.R;
import com.example.liuk.secret.net.GetCode;

public class AtyLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_login);

        etPhone = (EditText)findViewById(R.id.etPhoneNum);

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

    }

    private EditText etPhone = null;
}
