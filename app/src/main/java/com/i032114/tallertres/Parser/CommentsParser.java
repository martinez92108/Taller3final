package com.i032114.tallertres.Parser;

import com.i032114.tallertres.Model.CommentsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinez on 14/10/17.
 */

public class CommentsParser {

    public static List<CommentsModel> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<CommentsModel> commentsModelList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            CommentsModel commentsModel = new CommentsModel();
            commentsModel.setPostId(item.getInt("postId"));
            commentsModel.setId(item.getInt("id"));
            commentsModel.setEmail(item.getString("email"));
            commentsModel.setBody(item.getString("body"));
            commentsModelList.add(commentsModel);




        }
        return commentsModelList;
    }



}
