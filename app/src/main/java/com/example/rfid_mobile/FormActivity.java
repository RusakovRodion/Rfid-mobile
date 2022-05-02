package com.example.rfid_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity {

    ObjectClass object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        String type = arguments.getString("type");
        Integer id = arguments.getInt("id");

        setContentView(R.layout.form_update);
        this.object = Logic.getObjectById(id);

        ArrayList<String> categories = Logic.getCategories();
        // Получаем ссылку на элемент AutoCompleteTextView в разметке
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.inputCategory);
        // Создаем адаптер для автозаполнения элемента AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter (this, R.layout.dropdown_item, categories);
        autoCompleteTextView.setAdapter(adapter);
    }
}
