package com.example.ankit.attendanceapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;

public class SplashActivity extends FancyWalkthroughActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FancyWalkthroughCard card1 = new FancyWalkthroughCard("Welcome to Attendence App!", "An easy to use attendence app beneficial for both the employer and the employee");

        FancyWalkthroughCard card2 = new FancyWalkthroughCard("Email Id", "Use your Email Id to login"

        );


        FancyWalkthroughCard card3 = new FancyWalkthroughCard("Mark Attendance", "Employee can tap to mark the attendance which will be instantly verified by the employer");
        FancyWalkthroughCard card4 = new FancyWalkthroughCard("Attendance Report", "Can be exported and sent via mail");
        FancyWalkthroughCard card5 = new FancyWalkthroughCard("Explore and Simplify!", "Dump the old attendance registers and simplify your work! ");


        card1.setBackgroundColor(R.color.white);

        card1.setIconLayoutParams(400, 400, 0, 0, 0, 0);


        card2.setBackgroundColor(R.color.white);

        card2.setIconLayoutParams(400, 400, 0, 0, 0, 0);

        card3.setBackgroundColor(R.color.white);

        card3.setIconLayoutParams(400, 400, 0, 0, 0, 0);

        card4.setBackgroundColor(R.color.white);

        card4.setIconLayoutParams(400, 400, 0, 0, 0, 0);

        card5.setBackgroundColor(R.color.white);

        card5.setIconLayoutParams(400, 400, 0, 0, 0, 0);


        ArrayList<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(card1);

        pages.add(card2);

        pages.add(card3);
        pages.add(card4);
        pages.add(card5);


        for (FancyWalkthroughCard card : pages) {

            card.setDescriptionColor(R.color.black);

            card.setTitleColor(R.color.black);

        }

        setFinishButtonTitle("Start Now !");

        showNavigationControls(true);

        setOnboardPages(pages);

        setColorBackground(R.color.colorPrimary);
        setImageBackground(R.drawable.attendanceimage);


    }

    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(SplashActivity.this, RegisterActivity.class));

    }
}
