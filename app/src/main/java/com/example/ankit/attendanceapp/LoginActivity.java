package com.example.ankit.attendanceapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login Activity";
    private static final String URL_FOR_LOGIN = "";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    private EditText loginInputEmail;
    private EditText loginInputPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private TextView forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginInputEmail = findViewById(R.id.log_email);
        loginInputPassword = findViewById(R.id.log_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_signup);
        forgotPass = findViewById(R.id.tv_forgotPass);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginInputEmail.getText().toString().equals("admin") && loginInputPassword.getText().toString().equals("password")) {
                    startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                }
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                if(loginInputEmail.getText().toString().isEmpty()){
                    builder.setMessage("Please Enter your registered mail in the specified field so that recovery password could be sent.");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();

                }else{
                    builder.setMessage("Reset password sent to your registered Email Id. ");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();

                }
            }
        });
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        if (password.length() < 5 && password.length() > 10) {
            return false;
        } else {
            return true;
        }
    }
}
