package com.i032114.tallertres.Parser;

import com.i032114.tallertres.Model.PostsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinez on 14/10/17.
 */

public class PostsParser {



    public static List<PostsModel> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<PostsModel> postsModelList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            PostsModel postsModel= new PostsModel();
            postsModel.setUserid(item.getInt("userId"));
            postsModel.setId(item.getInt("id"));
            postsModel.setTitle(item.getString("title"));
            postsModel.setBody(item.getString("body"));
            postsModelList.add(postsModel);

        }
        return postsModelList;
    }




}
