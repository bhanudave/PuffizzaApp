package com.puffizza.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.puffizza.Forgot.ForgotActivity;
import com.puffizza.MainActivity;
import com.puffizza.R;
import com.puffizza.Regisrtation.RegistrationActivity;
import com.puffizza.Utills.utils;
import com.puffizza.retrofit.ApiUtils;
import com.puffizza.retrofit.UserService;

public class LoginActivity extends Activity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private TextView registration_txt,forgot_txt;
    UserService userService;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(utils.MyPREFERENCES, Context.MODE_PRIVATE);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        registration_txt = findViewById(R.id.registration_txt);
        forgot_txt = findViewById(R.id.forgot_txt);
        userService = ApiUtils.getUserService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                //validate form
                if (validateLogin(username, password)) {

                    if (sharedpreferences.getString(utils.ID, "").isEmpty() &&
                            sharedpreferences.getString(utils.ID, "").equalsIgnoreCase("")) {
                        Toast.makeText(LoginActivity.this, "User Not Register.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    /*SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(utills.ID, "1");
                    editor.putString(utills.F_Name, username);
                    editor.commit();*/
                    //do login
//                    doLogin(username, password);
                }
            }
        });

        registration_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        forgot_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private boolean validateLogin(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}