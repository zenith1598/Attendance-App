package com.example.ankit.attendanceapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ankit.attendanceapp.model.Config;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class VerificationActivity extends AppCompatActivity {
    EditText etVer1, etVer2, etVer3, etVer4;
    private static final String TAG = "Check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);


        final String mail = getIntent().getStringExtra("MailId");
        final String name = getIntent().getStringExtra("Name");
        Log.e(TAG, mail );

        AlertDialog.Builder builder = new AlertDialog.Builder(VerificationActivity.this);
        builder.setMessage("Verification code sent to your mail. Please enter the code below.");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                Random rand  = new Random();
                String verificationCode = String.valueOf(rand.nextInt(10))+ String.valueOf(rand.nextInt(10)) + String.valueOf(rand.nextInt(10)) + String.valueOf(rand.nextInt(10));
                String message = "Please use the following Verification code :" + verificationCode;
                String subject = "Verification Code for Attendence App.";

                SendMail sendMail = new SendMail(VerificationActivity.this,mail,name,message,subject);

                sendMail.execute();

            }
        }).show();
    }

    public class SendMail extends AsyncTask<Void, Void, Void> {

        //Declaring Variables
        private Context context;
        private Session session;

        String mail;
        String name;
        String message;
        String subject;

        public SendMail(Context context, String mail, String name, String message, String subject) {
            this.mail = mail;
            this.name = name;
            this.message = message;
            this.subject = subject;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(VerificationActivity.this, "Verification Code Sent to your registered mail", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Creating properties
            Properties props = new Properties();
            //Configuring properties for gmail
            //If you are not using gmail you may need to change the values
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            //Creating a new session
            session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        //Authenticating the password
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                        }
                    });

            try {
//Creating MimeMessage object
                MimeMessage mm = new MimeMessage(session);
//Setting sender address
                mm.setFrom(new InternetAddress(Config.EMAIL));
//Adding receiver
                mm.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
//Adding subject
                mm.setSubject(subject);
//Adding message
                mm.setText(message);

//Sending email
                Transport.send(mm);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
