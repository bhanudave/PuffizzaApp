package com.puffizza.Forgot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.puffizza.Login.LoginActivity;
import com.puffizza.MainActivity;
import com.puffizza.R;
import com.puffizza.Regisrtation.RegistrationActivity;
import com.puffizza.retrofit.ApiUtils;
import com.puffizza.retrofit.UserService;

public class ForgotActivity extends Activity {

    private EditText edtForgot_password;
    private Button btnforgot;
    private TextView header_name;
    private ImageView back_arrow,home_icon,menu_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);


        edtForgot_password = (EditText) findViewById(R.id.edtForgot_password);
        btnforgot = (Button) findViewById(R.id.btnforgot);

        back_arrow = findViewById(R.id.back_arrow);
        menu_icon = findViewById(R.id.menu_icon);
        home_icon = findViewById(R.id.home_icon);
        header_name = findViewById(R.id.header_name);

        header_name.setText(R.string.title_forgot);
        menu_icon.setVisibility(View.GONE);
        home_icon.setVisibility(View.INVISIBLE);
        back_arrow.setVisibility(View.VISIBLE);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Forgot_password = edtForgot_password.getText().toString();
                //validate form
                if (validateLogin(Forgot_password)) {
                    //do login
//                    doLogin(username, password);
                    Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private boolean validateLogin(String forgot_password) {
        if (forgot_password == null || forgot_password.trim().length() == 0) {
            Toast.makeText(this, "Username or Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}