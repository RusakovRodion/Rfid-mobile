package com.example.rfid_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ObjectActivity extends AppCompatActivity {

    ObjectClass object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        Integer id = arguments.getInt("id");
        this.object = Logic.getObjectById(id);
        setContentView(R.layout.activity_object);
        TextView name = findViewById(R.id.nameObject);
        name.setText(object.name);
        TextView cat = findViewById(R.id.categoryObject);
        cat.setText(object.category);
        TextView desc = findViewById(R.id.descObject);
        desc.setText(object.description);
        TextView status = findViewById(R.id.statusObject);
        if (object.status == true) {
            status.setText("Статус: на складе");
        } else {
            status.setText("Статус: арендован");
        }
    }
}
