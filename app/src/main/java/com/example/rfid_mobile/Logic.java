package com.example.rfid_mobile;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logic {

    public static List<ObjectClass> getObjects() {
        String msg = "all_object";
        ArrayList<ObjectClass> objects = new ArrayList<>();
        String answer = con(msg);
        String[] tempList = answer.split("\\|");
        for (String i : tempList) {
            String[] temp = i.split("&");
            objects.add(new ObjectClass(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3].equals("1"), temp[4]));
        }

        return objects;
    }

    public static int scanRfid(){
//        // TODO отсканировать метку
//        String msg = "check_rfid";
//        String answer = con(msg);
//        int tempAnswer = Integer.parseInt(answer);
//        if (tempAnswer>0){
//            return tempAnswer;
//        }
        //TODO получить правильный ответ (метка не привязана/кортеж)
        return -1;
    }


    public static ObjectClass getObjectById(Integer id) {
        String msg = "find_object|"+id.toString();
        String answer = con(msg);
        String[] temp = answer.split("&");
        return new ObjectClass(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3].equals("1"), temp[4]);
    }

    public static Boolean saveEdit(ObjectClass obj){
        String msg = "update_object|"+obj.id+"|"+obj.name+"|"+obj.description+"|"+obj.category;
        String answer = con(msg);
        return !answer.regionMatches(0, "False", 0, 5);
    }

    public static Boolean returnObject(int id){
        String msg = "return_object|"+id;
        String answer = con(msg);
        return !answer.regionMatches(0, "False", 0, 5);
    }

    public static Boolean newRental(RentalClass obj){
        String msg = "new_rent|"+obj.name+"|"+obj.startDate+"|"+obj.endDate+"|"+obj.objectID;
        String answer = con(msg);
        return answer.equals("true");
    }

    public static Boolean deleteObject(int id){
        String msg = "delete_object|"+id;
        String answer = con(msg);
        return answer.equals("true");
    }

    public static Boolean rentalObject(int id) {
        String msg = "rental_object|"+id;
        String answer = con(msg);
        return answer.equals("true");
    }

    public static Boolean addObject(ObjectClass obj){
        String msg = "new_object|"+obj.id+"|"+obj.name+"|"+obj.name+"|"+obj.description+"|"+obj.category;
        String answer = con(msg);
        return !answer.regionMatches(0, "False", 0, 5);
    }



    public static ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("wires");
        categories.add("microphone");
        categories.add("headphones");
        categories.add("music_column");
        return categories;
    }

    public static List<ObjectClass> sort(String status,List<Boolean> category, String name) {
        StringBuilder msg = new StringBuilder("filter_object|" + status + "|");
        boolean start = true;
        ArrayList<String> categories = getCategories();
        StringBuilder temp2 = new StringBuilder();
        for (int i =0; i<categories.size();i++){
            if (category.get(i)) {
                if (!start) temp2.append("&");
                temp2.append(categories.get(i));
                start = false;
            }
        }
        msg.append(temp2);
        msg.append("|").append("");

        ArrayList<ObjectClass> objects = new ArrayList<>();
        String answer = con(msg.toString());
        String[] tempList = answer.split("\\|");
        for (String i : tempList) {
            String[] temp = i.split("&");
            objects.add(new ObjectClass(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3].equals("1"), temp[4]));
        }
        return objects;
        //return getObjects();
    }
//        List<ObjectClass> filteredObjects = new ArrayList<>();
//        String[] categories = getCategories().toArray(new String[0]);
//        Boolean statusCheck = null;
//        if (status.get(0) && !status.get(1)) statusCheck = true;
//        else if (!status.get(0) && status.get(1)) statusCheck = false;
//        boolean categoryCheck = false;
//        boolean nameCheck = false;
//        name = name.toLowerCase();
//        for (Boolean cat:category) {
//            if (cat)
//            {
//                categoryCheck = true;
//                break;
//            }
//        }
//        if (!name.isEmpty()) {
//            nameCheck = true;
//        }
//        for (ObjectClass object:getObjects())
//        {
//            if ( statusCheck == null || object.status == statusCheck)
//            {
//                if (categoryCheck) {
//                    for (int i = 0; i < category.size(); i++) {
//                        if (category.get(i) && object.category.equals(categories[i])) {
//                            if (nameCheck) {
//                                if (object.name.toLowerCase().contains(name)) filteredObjects.add(object);
//                            }else filteredObjects.add(object);
//                        }
//                    }
//                }else if (nameCheck) {
//                    if (object.name.toLowerCase().contains(name)) filteredObjects.add(object);
//                }else filteredObjects.add(object);
//            }
//        }
//        return filteredObjects;
//    }
    public static String con(String msg) {
        Socket socket;
        BufferedReader dis;
        DataOutputStream dos;
        String host = "25.66.192.245";
        int port = 777;
        String answer = "False";
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            socket = new Socket(host, port);

            dos = new DataOutputStream(socket.getOutputStream());
            dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dos.write(msg.getBytes());
            while (!dis.ready()){}
            answer = dis.readLine();
            dis.close();
            dos.flush();
            dos.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return answer;
    }
}
