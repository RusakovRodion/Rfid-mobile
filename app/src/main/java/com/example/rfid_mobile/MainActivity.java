package com.example.rfid_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Layout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Button;
import android.view.View;
import java.util.ArrayList;
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

        ArrayList<ObjectClass> objects = new ArrayList<>();
        //TODO Тут была проверка на нулевой список objects, но он всегда пустой :Ъ я убрал.
        objects.add(new ObjectClass(0, "Штативв", "Штатив для крепления телефона с круглой лампой", true, "Оборудование"));
        objects.add(new ObjectClass(1, "Колонка JBL", "Водостойкая, портативная, среднего размера, подключение по bluetooth", false, "Колонка"));
        objects.add(new ObjectClass(2, "Лампа", "Свет для съемок", true, "Оборудование"));
        objects.add(new ObjectClass(3, "Стабилизатор", "Стабилизирует телефон при съемке", false, "Оборудование"));
        objects.add(new ObjectClass(4, "Проектор", "Проектор, кабель HDMI", true, "Проектор"));
        objects.add(new ObjectClass(5, "Штатив2", "Штатив для крепления телефона с круглой лампой", true, "Оборудование"));
        objects.add(new ObjectClass(6, "Колонка JBL2", "Водостойкая, портативная, среднего размера, подключение по bluetooth", false, "Колонка"));
        objects.add(new ObjectClass(7, "Лампа2", "Свет для съемок", true, "Оборудование"));
        objects.add(new ObjectClass(8, "Стабилизатор2", "Стабилизирует телефон при съемке", false, "Оборудование"));
        objects.add(new ObjectClass(9, "Проектор2", "Проектор, кабель HDMI", true, "Проектор"));
        objects.add(new ObjectClass(10, "Штатив3", "Штатив для крепления телефона с круглой лампой", true, "Оборудование"));
        objects.add(new ObjectClass(11, "Колонка JBL3", "Водостойкая, портативная, среднего размера, подключение по bluetooth", false, "Колонка"));
        objects.add(new ObjectClass(12, "Лампа3", "Свет для съемок", true, "Оборудование"));
        objects.add(new ObjectClass(13, "Стабилизатор3", "Стабилизирует телефон при съемке", false, "Оборудование"));
        objects.add(new ObjectClass(14, "Проектор3", "Проектор, кабель HDMI", true, "Проектор"));
        objects.add(new ObjectClass(15, "Штатив4", "Штатив для крепления телефона с круглой лампой", true, "Оборудование"));
        objects.add(new ObjectClass(16, "Колонка JBL4", "Водостойкая, портативная, среднего размера, подключение по bluetooth", false, "Колонка"));
        objects.add(new ObjectClass(17, "Лампа4", "Свет для съемок", true, "Оборудование"));
        objects.add(new ObjectClass(18, "Стабилизатор4", "Стабилизирует телефон при съемке", false, "Оборудование"));
        objects.add(new ObjectClass(19, "Проектор4", "Проектор, кабель HDMI", true, "Проектор"));
        objects.add(new ObjectClass(20, "Штатив5", "Штатив для крепления телефона с круглой лампой", true, "Оборудование"));

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

        //TODO добавляем для списка слушатель
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

    void testDraw(int currentPage, ListView listView ){
        // создаем адаптер
        ObjectAdapter adapter = new ObjectAdapter(this,
                R.layout.object_mini, objectsLists.get(currentPage-1));
        // устанавливаем для списка адаптер
        listView.setAdapter(adapter);
    }

}