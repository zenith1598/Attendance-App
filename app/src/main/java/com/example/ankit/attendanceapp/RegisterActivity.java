package com.example.ankit.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegisterActivity extends AppCompatActivity {
    private Button regSignUp;
    private Button regLogIn;
    private EditText regName;
    private EditText regEmail;
    private EditText regPassword;
    private EditText regCPasswoord;
    private EditText regAge;
    private RadioButton regGMale;
    private RadioButton regGFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regSignUp = findViewById(R.id.btn_reg_signup);
        regLogIn = findViewById(R.id.redirect_login);
        regName = findViewById(R.id.reg_name);
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        regCPasswoord = findViewById(R.id.reg_cpassword);
        regAge = findViewById(R.id.reg_age);
        regGMale = findViewById(R.id.male_radio_btn);
        regGFemale = findViewById(R.id.female_radio_btn);

        regLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });




    }
}
