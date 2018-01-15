package com.dao;

import com.google.gson.Gson;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.List;

public class DAOUtil {

    private static List<ItemBean> items = new ArrayList<>();

    static {
        items.add(new ItemBean(1, "41u8Qp5SUaL._SY346_.jpg", 16.46f));
        items.add(new ItemBean(3, "51V3zmzZZjL.jpg", 30.59f));
        items.add(new ItemBean(4, "51pTI873cAL.jpg", 23.31f));
        items.add(new ItemBean(6, "41jMHM2v8nL.jpg", 1.17f));
    }

    public static String getJsonifiedItems() {
        return new Gson().toJson(getItems());
    }

    public static String getJsonifiedItemsByIds(List<Integer> itemIds) {
        List<ItemBean> list = new ArrayList<>();
        for (Integer itemId : itemIds) {
            for (ItemBean itemBean : items) {
                if (itemBean.getId() == itemId) {
                    list.add(itemBean);
                }
            }
        }
        return new Gson().toJson(list);
    }

    public static List<ItemBean> getItems() {
        return items;
    }
}
