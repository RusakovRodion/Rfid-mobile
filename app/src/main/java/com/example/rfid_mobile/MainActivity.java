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

import android.widget.AdapterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
public class MainActivity extends AppCompatActivity {
    //Кол-во элементов
    public int TOTAL_LIST_ITEMS = 0;
    //Кол-во элементов на странице
    public int NUM_ITEMS_PAGE   = 10;
    public int TOTAL_PAGE_COUNT = 0;
    public int currentPage = 0;
    public ArrayList<ArrayList<ObjectClass>> objectsLists = new ArrayList<ArrayList<ObjectClass>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ObjectClass> objects = Logic.getObjects();
        TOTAL_LIST_ITEMS = objects.size();
        TOTAL_PAGE_COUNT = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + 1;

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

        // получаем элемент ListView
        ListView listView = findViewById(R.id.objectsList);
        // прикрепляем footer с кнопками
        LayoutInflater inflater = LayoutInflater.from(this);
        View footerView = inflater.inflate(R.layout.buttons_mini, null);
        View headerView = inflater.inflate(R.layout.filters_mini, null);
        listView.addFooterView(footerView);
        listView.addHeaderView(headerView);
        testDraw(currentPage, listView);

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


        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        ImageButton filterButton = findViewById(R.id.filterButton);
        if (TOTAL_PAGE_COUNT > 1) rightButton.setClickable(true);
        leftButton.setClickable(false);
        leftButton.setVisibility(View.INVISIBLE);
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
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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