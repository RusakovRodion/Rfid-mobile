package com.example.rfid_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_rfid);
        Button addObjectButton = findViewById(R.id.addObjectButton);
        addObjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addObject();
            }
        });
    }
    void addObject() {
        Intent intent = new Intent(this, FormActivity.class);
        intent.putExtra("type", "add");
        startActivity(intent);
        finish();
    }
}