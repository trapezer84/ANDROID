package com.ktds.leina.addressbook.db;

import com.ktds.leina.addressbook.vo.AddressVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 206-025 on 2016-06-08.
 */
public class SimpleDB {

    private static Map<String, AddressVO> db = new HashMap<String, AddressVO>();

    public static void addArticle(String index, AddressVO addressVO) { db.put(index, addressVO); }

    public static AddressVO getArticle(String index) {
        return db.get(index);
    }

    public static List<String> getIndexs() {
        Iterator<String> keys = db.keySet().iterator();

        List<String> keyList = new ArrayList<String>();
        String key = "";
        while(keys.hasNext()) {
            key = keys.next();
            keyList.add(key);
        }

        return keyList;
    }
}
