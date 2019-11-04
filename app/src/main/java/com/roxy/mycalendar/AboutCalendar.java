package com.roxy.mycalendar;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class AboutCalendar extends AppCompatActivity {
    private ImageView back;
    private TextView title;
    private Button tosupport;
    private Button tocalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_calendar);
        back = findViewById(R.id.open_nav);
        title = findViewById(R.id.title);
        back.setImageResource(R.drawable.ic_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("About Calendar");
        tosupport = findViewById(R.id.tosupport);
        tosupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutCalendar.this,Support.class);
                startActivity(intent);
            }
        });

        tocalendar = findViewById(R.id.tocalendar);
        tocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutCalendar.this, MyCalendarView.class);
                startActivity(intent);
            }
        });

    }
}
