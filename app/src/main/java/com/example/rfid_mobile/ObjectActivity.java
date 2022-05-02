package com.example.rfid_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ObjectActivity extends AppCompatActivity {

    ObjectClass object;
    AlertDialog.Builder builder;
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

        Button editObjectButton = findViewById(R.id.editObjectButton);
        Button deleteObjectButton = findViewById(R.id.deleteObjectButton);
        builder = new AlertDialog.Builder(this);
        editObjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Внимание")
                        .setMessage("Вы точно хотите редактировать "+name.getText()+"?")
                        .setCancelable(true)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
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
}

