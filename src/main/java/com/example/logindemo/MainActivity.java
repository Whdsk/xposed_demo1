package com.example.logindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String ACCOUNT="2";
    private final String PASSWORD="2";
    private EditText etAccount, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etAccount=(EditText)findViewById(R.id.et_username);

        etPassword=(EditText)findViewById(R.id.et_password);

        btnLogin=(Button)findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOK(etAccount.getText().toString(), etPassword.getText().toString())) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isOK(String account, String password) {
        Log.i("aaa",account);
        Log.i("aaa",password);
        return account.equals(ACCOUNT) && password.equals(PASSWORD);
    }


}
