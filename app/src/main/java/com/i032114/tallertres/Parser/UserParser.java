package com.i032114.tallertres.Parser;

import com.i032114.tallertres.Model.UserModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinez on 12/10/17.
 */

public class UserParser {

    public static List<UserModels> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<UserModels> userModelsList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){

            JSONObject item = jsonArray.getJSONObject(i);

            JSONObject addressitem = item.getJSONObject("addresss");
            JSONObject namecompany = item.getJSONObject("company");




            UserModels userModels = new UserModels();
            userModels.setId(item.getInt("id"));
            userModels.setName(item.getString("name"));
            userModels.setUsername(item.getString("username"));
            userModels.setAddress(addressitem.getString("street"));
            userModels.setCompany(namecompany.getString("name"));




            userModelsList.add(userModels);


        }
        return userModelsList;
    }



}
