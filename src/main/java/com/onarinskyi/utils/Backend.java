package com.onarinskyi.utils;

import org.json.JSONObject;
import com.onarinskyi.springConstructors.UserData;

public class Backend {


    private static JSONObject prepareDataMap(UserData userData) {
        JSONObject dataMap = new JSONObject();
        //*dataMap.put("bonusgroupid", 20);
        dataMap.put("currencyCode", userData.getCurrency());
        dataMap.put("countrycode", userData.getCountry());
        dataMap.put("city", userData.getCity());
        dataMap.put("zip", userData.getPostCode());
        dataMap.put("phone", userData.getPhone());
        dataMap.put("cellphone", userData.getPhone());
        dataMap.put("address", userData.getAddress());
        //dataMap.put("address", (userData.getHouse() + " " + userData.getAddress() + " " + userData.getAddress2()).trim());
        dataMap.put("birthdate", userData.getBirthYear() + "-" + userData.getBirthMonth() + "-" + userData.getBirthDay());
        dataMap.put("userName", userData.getUsername());
        dataMap.put("password", userData.getPassword());
        dataMap.put("title", userData.getTitle());
        dataMap.put("firstname", userData.getFirstName());
        dataMap.put("lastname", userData.getLastName());
        dataMap.put("email", userData.getEmail());

        //dataMap.put("tcVersion", .getDefaults().getTcVersion());
        dataMap.put("language", "EN");

        return dataMap;
    }


}
