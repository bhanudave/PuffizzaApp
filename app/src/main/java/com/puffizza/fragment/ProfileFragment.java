package com.puffizza.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.puffizza.Login.LoginActivity;
import com.puffizza.R;
import com.puffizza.Utills.utils;

public class ProfileFragment extends Fragment {

    private EditText edtUserfirstname,edtUserlastname,edtUseremail,edtUsermobile;
    private Button btnRegistration;
    private ImageView logout_btn;
    SharedPreferences sharedpreferences;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sharedpreferences = getContext().getSharedPreferences(utils.MyPREFERENCES, Context.MODE_PRIVATE);

        edtUserfirstname = view.findViewById(R.id.edtUserfirstname);
        edtUserlastname = view.findViewById(R.id.edtUserlastname);
        edtUseremail = view.findViewById(R.id.edtUseremail);
        edtUsermobile = view.findViewById(R.id.edtUsermobile);
        btnRegistration = view.findViewById(R.id.btnRegistration);

        logout_btn = view.findViewById(R.id.logout_btn);

        edtUserfirstname.setText(sharedpreferences.getString(utils.F_Name, ""));
        edtUserlastname.setText(sharedpreferences.getString(utils.L_Name, ""));
        edtUseremail.setText(sharedpreferences.getString(utils.Email, ""));
        edtUsermobile.setText(sharedpreferences.getString(utils.Phone, ""));

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userfirstname = edtUserfirstname.getText().toString();
                String userlastname = edtUserlastname.getText().toString();
                String useremail = edtUseremail.getText().toString();
                String usermobile = edtUsermobile.getText().toString();
                //validate form
                if (validateLogin(userfirstname, userlastname, useremail, usermobile)) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(utils.ID, "1");
                    editor.putString(utils.F_Name, userfirstname);
                    editor.putString(utils.L_Name, userlastname);
                    editor.putString(utils.Email, useremail);
                    editor.putString(utils.Phone, usermobile);
                    editor.commit();

                    Toast.makeText(getContext(), "Successfully updated profile.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(utils.ID, "");
                editor.putString(utils.F_Name, "");
                editor.putString(utils.L_Name, "");
                editor.putString(utils.Phone, "");
                editor.putString(utils.Email, "");
                editor.commit();
                Intent logout = new Intent(getContext(), LoginActivity.class);
                startActivity(logout);
                getActivity().finishAffinity();
            }
        });

        return view;
    }

    private boolean validateLogin(String userfirstname, String userlastname, String useremail, String usermobile) {
        if (userfirstname == null || userfirstname.trim().length() == 0) {
            Toast.makeText(getContext(), "User first name is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userlastname == null || userlastname.trim().length() == 0) {
            Toast.makeText(getContext(), "User last name is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (useremail == null || useremail.trim().length() == 0) {
            Toast.makeText(getContext(), "User email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (usermobile == null || usermobile.trim().length() == 0) {
            Toast.makeText(getContext(), "User mobile number is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
