package com.example.rfid_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.Layout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Button;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import android.widget.AdapterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    //Кол-во элементов
    public int TOTAL_LIST_ITEMS = 0;
    //Кол-во элементов на странице
    public int NUM_ITEMS_PAGE   = 10;
    public int TOTAL_PAGE_COUNT = 0;
    public int currentPage = 0;
    public ArrayList<ArrayList<ObjectClass>> objectsLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // получаем элемент ListView
        ListView listView = findViewById(R.id.objectsList);
        // прикрепляем footer с кнопками
        LayoutInflater inflater = LayoutInflater.from(this);
        View footerView = inflater.inflate(R.layout.buttons_mini, null);
        View headerView = inflater.inflate(R.layout.filters_mini, null);
        listView.addFooterView(footerView);
        listView.addHeaderView(headerView);
        // Получаем объекты для фильтрации
        ArrayList<CheckBox> statusCheckboxes = new ArrayList<>();
        statusCheckboxes.add(findViewById(R.id.onStorage));
        statusCheckboxes.add(findViewById(R.id.onRent));
        ArrayList<CheckBox> categoryCheckboxes = new ArrayList<>();
        categoryCheckboxes.add(findViewById(R.id.category1));
        categoryCheckboxes.add(findViewById(R.id.category2));
        categoryCheckboxes.add(findViewById(R.id.category3));
        TextInputLayout nameView = findViewById(R.id.searchBar);

        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        Button searchButton = findViewById(R.id.searchButton);

        test(statusCheckboxes, categoryCheckboxes, nameView, listView, rightButton, leftButton);

        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                ObjectClass selectedObject = (ObjectClass)parent.getItemAtPosition(position);
                openObject(selectedObject);
            }
        };
        listView.setOnItemClickListener(itemListener);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage += 1;
                testDraw(currentPage, listView);
                if (currentPage == TOTAL_PAGE_COUNT) {
                    rightButton.setClickable(false);
                    rightButton.setVisibility(View.INVISIBLE);
                }
                leftButton.setVisibility(View.VISIBLE);
                leftButton.setClickable(true);
            }
        });
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage -= 1;
                testDraw(currentPage, listView);
                if (currentPage == 1) {
                    leftButton.setClickable(false);
                    leftButton.setVisibility(View.INVISIBLE);
                }
                rightButton.setVisibility(View.VISIBLE);
                rightButton.setClickable(true);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test(statusCheckboxes, categoryCheckboxes, nameView, listView, rightButton, leftButton);
            }
        });
    }



    void test(ArrayList<CheckBox> statusCheckboxes, ArrayList<CheckBox> categoryCheckboxes, TextInputLayout nameView, ListView listView, Button rightButton, Button leftButton){

        ArrayList<Boolean> status = new ArrayList<>();
        status.add((statusCheckboxes.get(0)).isChecked());
        status.add((statusCheckboxes.get(1)).isChecked());
        ArrayList<Boolean> category = new ArrayList<>();
        category.add((categoryCheckboxes.get(0)).isChecked());
        category.add((categoryCheckboxes.get(1)).isChecked());
        category.add((categoryCheckboxes.get(2)).isChecked());
        String name = (Objects.requireNonNull((nameView).getEditText())).getText().toString().trim();
        List<ObjectClass> objects = Logic.sort(status,category,name);
        TOTAL_LIST_ITEMS = objects.size();
        TOTAL_PAGE_COUNT = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + 1;
        objectsLists.clear();
        //Разбиваем список объектов на списки заданной длины NUM_ITEMS_PAGE
        ArrayList<ObjectClass> tempObjList = new ArrayList<>();
        for (int i = 0;i<TOTAL_LIST_ITEMS;i++) {
            tempObjList.add(objects.get(i));
            if ((i+1) % NUM_ITEMS_PAGE==0){
                objectsLists.add((ArrayList<ObjectClass>) tempObjList.clone());
                tempObjList.clear();
            }
        }
        objectsLists.add((ArrayList<ObjectClass>) tempObjList.clone());
        currentPage = 1;
        rightButton.setClickable(false);
        rightButton.setVisibility(View.INVISIBLE);
        if (TOTAL_PAGE_COUNT > 1) rightButton.setClickable(true);
        leftButton.setClickable(false);
        leftButton.setVisibility(View.INVISIBLE);


        testDraw(currentPage, listView);
    }

    void openObject(ObjectClass object) {
        Intent intent = new Intent(this, ObjectActivity.class);
        intent.putExtra("id", object.id);
        startActivity(intent);
    }

    void testDraw(int currentPage, ListView listView ){

        // создаем адаптер
        ObjectAdapter adapter = new ObjectAdapter(this,
                R.layout.object_mini, objectsLists.get(currentPage-1));
        // устанавливаем для списка адаптер
        listView.setAdapter(adapter);
    }

}