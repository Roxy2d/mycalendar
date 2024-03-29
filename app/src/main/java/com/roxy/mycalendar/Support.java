package com.roxy.mycalendar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Support extends AppCompatActivity {
    private Button towechat;
    private ImageView back;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        towechat = findViewById(R.id.towechat);
        towechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoWeChat();
            }
        });
        back = findViewById(R.id.open_nav);
        back.setImageResource(R.drawable.ic_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = findViewById(R.id.title);
        title.setText("Sponsor the author");
    }

    private void gotoWeChat() {
        Intent intent = new Intent("com.tencent.mm.action.BIZSHORTCUT");
        intent.setPackage("com.tencent.mm");
        intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        try {
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(this, "Wechat has not installed", Toast.LENGTH_SHORT).show();
        }
    }
}
