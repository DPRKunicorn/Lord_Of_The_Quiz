package com.example.findyourprivategrandpa.Models;

import com.example.findyourprivategrandpa.controllerinterfaces.post.BidirectionalRequest;
import com.example.findyourprivategrandpa.controllerinterfaces.post.ImageFetcher;
import com.example.findyourprivategrandpa.controllerinterfaces.post.PostMessageBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import static com.example.findyourprivategrandpa.Urls.HIGH_SCORES_BY_QUIZ_URL;
import static com.example.findyourprivategrandpa.Urls.HIGH_SCORES_BY_USER_URL;
import static com.example.findyourprivategrandpa.Urls.THUMBNAIL_URL;

public class User {
    private int id;
    private String name;
    private HashMap<Quiz,Integer> highScores;
    private Quiz[] authoredQuizzes;
    public User(String name, int id)
    {
        this.id=id;
        this.name=name;
    }
    public void setHighScores() throws Exception
    {
        PostMessageBuilder pm=new PostMessageBuilder();
        pm.addEntry("quiz_id",""+id);
        BidirectionalRequest br= new BidirectionalRequest(HIGH_SCORES_BY_QUIZ_URL,pm.getValues());
        JSONObject jsonObject = new JSONObject(br.getResponse());
        JSONArray jsonScores= jsonObject.getJSONArray("scores");
        for (int i=0;i<jsonScores.length();i++)
        {
            JSONObject scoreTuple=jsonScores.getJSONObject(i);
            Quiz quiz=new Quiz(scoreTuple.getInt("id"),scoreTuple.getString("name"));
            highScores.put(quiz,scoreTuple.getInt("score"));
        }
    }
    private void setAuthoredQuizzes()
    {


    }
}
