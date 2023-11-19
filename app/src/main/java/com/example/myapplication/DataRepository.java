package com.example.myapplication;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataRepository {

    private static DataRepository instance;
    private static List<Model> itemList;

    private DataRepository() {
        itemList = new ArrayList<>();
    }

    public static synchronized DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public List<Model> getItemList() {
        return itemList;
    }
    public static Model getItem(String id) {
        for (Model item : itemList) {
            if (Objects.equals(item.id, id)) {
                return item;
            }
        }
        return null;
    }


    public static void addItem(Model item) {
        itemList.add(item);
    }

    public static void deleteItem(Model item) {
        itemList.remove(item);
    }

    public static void updateItem(Model item) {
        Model oldItem = DataRepository.getItem(item.getId());
        DataRepository.deleteItem(oldItem);
        DataRepository.addItem(item);
    }
}
