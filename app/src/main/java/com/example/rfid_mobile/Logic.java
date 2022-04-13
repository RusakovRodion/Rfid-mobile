package com.example.rfid_mobile;

import java.util.ArrayList;
import java.util.List;

public class Logic {

    public static List<ObjectClass> getObjects() {
        ArrayList<ObjectClass> objects = new ArrayList<>();
        //TODO Тут была проверка на нулевой список objects, но он всегда пустой :Ъ я убрал.
        objects.add(new ObjectClass(0, "Штатив", "Штатив для крепления телефона с круглой лампой", true, "Оборудование"));
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

        return objects;
    }

    public static ObjectClass getObjectById(Integer id) {
        List<ObjectClass> objects = Logic.getObjects();
        for (Integer i = 0; i < objects.size(); i++) {
            if (objects.get(i).id == id) {
                return objects.get(i);
            }
        }
        return null;
    }
}
