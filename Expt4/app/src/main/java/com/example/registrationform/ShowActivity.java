package com.example.registrationform;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity  extends AppCompatActivity {
    private TextView showDataText;
    private Button backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        showDataText = findViewById(R.id.showDatatext);
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String subject = intent.getStringExtra("subject");
        String gender = intent.getStringExtra("gender");
        String qualifications = intent.getStringExtra("qualifications");

        String data = "Name: "+name+"\n"+"Subject: "+subject +"\n"+"Gender: "+gender+"\n"+"Qualifications: "+qualifications;
        showDataText.setText(data);

        backButton.setOnClickListener(v -> {
            finish();
        });

    }
}
