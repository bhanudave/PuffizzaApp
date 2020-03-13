package com.puffizza.Regisrtation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.puffizza.Login.LoginActivity;
import com.puffizza.MainActivity;
import com.puffizza.R;
import com.puffizza.Utills.utils;
import com.puffizza.retrofit.ApiUtils;
import com.puffizza.retrofit.UserService;

import java.util.concurrent.ThreadLocalRandom;

import static com.puffizza.Utills.utils.EMAIL_ADDRESS_PATTERN;

public class RegistrationActivity extends Activity {

    private EditText edtUserfirstname,edtUserlastname,edtUseremail,edtUsermobile;
    private EditText edtPassword,edtRepeatpassword;
    private Button btnRegistration;
    private TextView login_txt;
    UserService userService;
    SharedPreferences sharedpreferences;
    private int min = 0, max = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        sharedpreferences = getSharedPreferences(utils.MyPREFERENCES, Context.MODE_PRIVATE);

        edtUserfirstname = (EditText) findViewById(R.id.edtUserfirstname);
        edtUserlastname = (EditText) findViewById(R.id.edtUserlastname);
        edtUseremail = (EditText) findViewById(R.id.edtUseremail);
        edtUsermobile = (EditText) findViewById(R.id.edtUsermobile);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtRepeatpassword = (EditText) findViewById(R.id.edtRepeatpassword);
        login_txt = findViewById(R.id.login_txt);
        btnRegistration = (Button) findViewById(R.id.btnRegistration);
        userService = ApiUtils.getUserService();

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String Userfirstname = edtUserfirstname.getText().toString();
                String Userlastname = edtUserlastname.getText().toString();
                String Useremail = edtUseremail.getText().toString();
                String Useremobile = edtUsermobile.getText().toString();
                String password = edtPassword.getText().toString();
                String Repeatpassword = edtRepeatpassword.getText().toString();
                //validate form
                if (validateLogin(Userfirstname, Userlastname, Useremail, Useremobile, password, Repeatpassword)) {

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    int random = ThreadLocalRandom.current().nextInt(min, max);

                    editor.putString(utils.ID, String.valueOf(random));
                    editor.putString(utils.F_Name, Userfirstname);
                    editor.putString(utils.L_Name, Userlastname);
                    editor.putString(utils.Phone, Useremobile);
                    editor.putString(utils.Email, Useremail);
                    editor.commit();
                    //do login
//                    doLogin(username, password);
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private boolean validateLogin(String userfirstname, String userlastname, String useremail, String useremobile, String password, String Repeatpassword) {
        String MobilePattern = "[0-9]{10}";
        if (userfirstname == null || userfirstname.trim().length() == 0) {
            Toast.makeText(this, "User first name is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userfirstname.length() < 5) {
            Toast.makeText(this, "User enter more than 5 character first name.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userlastname == null || userlastname.trim().length() == 0) {
            Toast.makeText(this, "User last name is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userlastname.length() < 5) {
            Toast.makeText(this, "User enter more than 5 character last name.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (useremail == null || useremail.trim().length() == 0) {
            Toast.makeText(this, "User email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!EMAIL_ADDRESS_PATTERN.matcher(useremail).matches()) {
            Toast.makeText(this, "User email is not valid", Toast.LENGTH_SHORT).show();
        }
        if (useremobile == null || useremobile.trim().length() == 0) {
            Toast.makeText(this, "User mobiles no. is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        /*if (useremobile.matches(MobilePattern)) {
            Toast.makeText(this, "Please enter valid mobiles no.", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 8) {
            Toast.makeText(this, "User enter 8 or more than 8 character password.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Repeatpassword == null || Repeatpassword.trim().length() == 0) {
            Toast.makeText(this, "Repeat password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Repeatpassword.length() < 8) {
            Toast.makeText(this, "User enter 8 or more than 8 character confirm password.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(Repeatpassword)) {
            Toast.makeText(this, "Confirm password not matched", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}