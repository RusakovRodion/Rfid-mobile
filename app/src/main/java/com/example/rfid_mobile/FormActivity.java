package com.example.rfid_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

public class FormActivity extends AppCompatActivity {

    ObjectClass object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        String type = arguments.getString("type");
        Integer id = arguments.getInt("id");
        setContentView(R.layout.update_form);
        this.object = Logic.getObjectById(id);

        TextInputLayout nameView = findViewById(R.id.inputNameBar);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        TextInputLayout descView = findViewById(R.id.inputDescBar);

        Objects.requireNonNull(nameView.getEditText()).setText(this.object.name);
        Objects.requireNonNull(descView.getEditText()).setText(this.object.description);


        ArrayList<String> categories = Logic.getCategories();
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
//set the spinners adapter to the previously created one.
        spinner.setAdapter(adapter);

        for (int i=0; i<categories.size(); i++)
        {
            if (categories.get(i).equals(this.object.category)){
                spinner.setSelection(i);
                break;
            }
        }
        Button saveEditButton = findViewById(R.id.saveEditButton);
        saveEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name = (Objects.requireNonNull((nameView).getEditText())).getText().toString().trim();
                String name = (Objects.requireNonNull((nameView).getEditText())).getText().toString().trim();
                String description = (Objects.requireNonNull((descView).getEditText())).getText().toString().trim();
                boolean status = Objects.requireNonNull(Logic.getObjectById(id)).status;
                String category = spinner.getSelectedItem().toString();

                ObjectClass obj = new ObjectClass(id, name, description, status, category);
                if (Logic.saveEdit(obj))
                {
                    //report success request
                }else{
                    //report error request
                }
                finish();
            }
        });
    }
}
