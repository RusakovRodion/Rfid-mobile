package com.example.rfid_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

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

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "three"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }
}
