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

        ArrayList<ObjectClass> objects = new ArrayList<>();
        if(objects.size()==0){
            objects.add(new ObjectClass(0, "glass", "glasses", true, "ork"));
            objects.add(new ObjectClass(1, "pounce", "pouncess", false, "picle"));
            objects.add(new ObjectClass(2, "richard", "Harrison", true, "Wells"));
        }



        // получаем элемент ListView
        ListView objectsList = findViewById(R.id.objectsList);
        // создаем адаптер
        ObjectAdapter adapter = new ObjectAdapter(this,
                R.layout.object_mini, objects);
        // устанавливаем для списка адаптер
        objectsList.setAdapter(adapter);
        // добавляем для списка слушатель
    }
}