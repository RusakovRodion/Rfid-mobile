package com.example.rfid_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ObjectActivity extends AppCompatActivity {

    ObjectClass object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        Integer id = arguments.getInt("id");
        this.object = Logic.getObjectById(id);
        setContentView(R.layout.object_from_catalog);
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

        Button editObjectButton = findViewById(R.id.editObjectButton);
        Button deleteObjectButton = findViewById(R.id.deleteObjectButton);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        editObjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               editObject();
            }
        });
        deleteObjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Внимание")
                        .setMessage("Вы точно хотите удалить "+name.getText() +"?")
                        .setCancelable(true)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                        .show();
            }
        });
    }

    void editObject() {
        Intent intent = new Intent(this, FormActivity.class);
        intent.putExtra("id", object.id);
        intent.putExtra("type", "edit");
        startActivity(intent);
    }
}

