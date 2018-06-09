package com.example.ankit.attendanceapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private TextInputLayout nameWrapper, emailWrapper, passWrapper, confWrapper, ageWrapper;


    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

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
        nameWrapper = findViewById(R.id.signup_input_layout_name);
        emailWrapper = findViewById(R.id.signup_input_layout_email);
        passWrapper = findViewById(R.id.signup_input_layout_password);
        confWrapper = findViewById(R.id.signup_input_layout_confirmpassword);
        ageWrapper = findViewById(R.id.signup_input_layout_age);

        regLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });

        regSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//

                if (regName.getText().toString().isEmpty()) {
                    nameWrapper.setErrorEnabled(true);
                    nameWrapper.setError("Enter your name. This cannot be left empty.");
                }
                if (regEmail.getText().toString().isEmpty()) {
                    emailWrapper.setErrorEnabled(true);
                    emailWrapper.setError("Enter the Email Id. This cannot be left empty.");
                }
                if (regPassword.getText().toString().isEmpty()) {
                    passWrapper.setErrorEnabled(true);
                    passWrapper.setError("Please set your password.");
                }
                if (regCPasswoord.getText().toString().isEmpty()) {
                    confWrapper.setErrorEnabled(true);
                    confWrapper.setError("Please again enter your password for confirmation.");
                }

                if (!(regCPasswoord.getText().toString()).equals(regPassword.getText().toString())) {
                    confWrapper.setErrorEnabled(true);
                    confWrapper.setError("Confirm Password Mismatch");
                }

                if (regAge.getText().toString().isEmpty()) {
                    ageWrapper.setErrorEnabled(true);
                    ageWrapper.setError("Please enter your age for complete information.");
                }
                if (!validateEmail(regEmail.getText().toString())) {
                    emailWrapper.setErrorEnabled(true);
                    emailWrapper.setError("Enter correct email Id");
                }

                if (!(regName.getText().toString().isEmpty()) && !(regEmail.getText().toString().isEmpty()) && !(regPassword.getText().toString().isEmpty()) && !(regCPasswoord.getText().toString().isEmpty())
                        && (regCPasswoord.getText().toString().equals(regPassword.getText().toString())) && !regAge.getText().toString().isEmpty() && validateEmail(regEmail.getText().toString())) {

                    //startActivity(new Intent(RegisterActivity.this, DashBoardActivity.class));
                    Intent i = new Intent(RegisterActivity.this, VerificationActivity.class);
                    i.putExtra("MailId", regEmail.getText().toString());
                    i.putExtra("Name", regName.getText().toString());
                    startActivity(i);

                }


            }
        });


    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
