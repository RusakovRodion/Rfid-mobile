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
        String parent = arguments.getString("parent");
        this.object = Logic.getObjectById(id);
        if (parent.equals("MainActivity")) {
            setContentView(R.layout.object_from_catalog);
        }else{
            setContentView(R.layout.object_from_rfid);
        }
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
        if (parent.equals("Logic"))
        {
            Button rentObjectButton = findViewById(R.id.rentObjectButton);
            if (object.status) {
                rentObjectButton.setText("Взять в аренду");
                rentObjectButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rentObject();
                    }
                });
            } else {
                rentObjectButton.setText("Вернуть на склад");
                rentObjectButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Logic.returnObject(object.id)){
                            builder.setTitle("Внимание")
                                    .setMessage("Объект успешно возвращен на склад")
                                    .setCancelable(true)
                                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                                    .show();
                        }else{
                            builder.setTitle("Внимание")
                                    .setMessage("Ошибка при возвращении на склад.")
                                    .setCancelable(true)
                                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            finish();
                                        }
                                    })
                                    .show();
                        }
                    }
                });
            }
        }
    }

    void editObject() {
        Intent intent = new Intent(this, FormActivity.class);
        intent.putExtra("id", object.id);
        intent.putExtra("type", "edit");
        startActivity(intent);
    }

    void rentObject() {
        Intent intent = new Intent(this, FormActivity.class);
        intent.putExtra("id", object.id);
        intent.putExtra("type", "rent");
        startActivity(intent);
    }
}

