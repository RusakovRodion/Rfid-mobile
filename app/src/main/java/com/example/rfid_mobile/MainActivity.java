package com.example.rfid_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ObjectClass> objects = new ArrayList<ObjectClass>();
        if(objects.size()==0){
            objects.add(new ObjectClass(0, "Очки", "Прекрасные  очки", true, "Очки"));
            objects.add(new ObjectClass(1, "Очки", "Волшебные  очки", true, "Очки"));
            objects.add(new ObjectClass(2, "Очки", "Великолепные  очки", true, "Очки"));
        }

        // получаем элемент ListView
        ListView objectsList = findViewById(R.id.objectsList);
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, objects);
        // устанавливаем для списка адаптер
        objectsList.setAdapter(adapter);
        // добавляем для списка слушатель
    }
}