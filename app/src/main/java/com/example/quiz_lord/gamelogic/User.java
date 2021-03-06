package com.example.quiz_lord.gamelogic;

import com.example.quiz_lord.middleware.post.BidirectionalRequest;
import com.example.quiz_lord.middleware.post.PostMessageBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import static com.example.quiz_lord.Urls.HIGH_SCORES_BY_QUIZ_URL;
import static com.example.quiz_lord.Urls.USER_QUIZES_URL;

public class User {
    private int id;
    private String name;
    private HashMap<Quiz,Integer> highScores;
    private Quiz[] authoredQuizzes;
    public User( int id, String name)
    {
        this.id=id;
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    public void pullHighScores() throws Exception
    {
        PostMessageBuilder pm=new PostMessageBuilder();
        pm.addEntry("id",""+id);
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
    private void pullAuthoredQuizzes() throws Exception
    {
        PostMessageBuilder pm=new PostMessageBuilder();
        pm.addEntry("id",""+id);
        BidirectionalRequest br= new BidirectionalRequest(USER_QUIZES_URL,pm.getValues());
        JSONObject jsonObject = new JSONObject(br.getResponse());
        JSONArray jsonScores= jsonObject.getJSONArray("scores");
        for (int i=0;i<jsonScores.length();i++)
        {
            JSONObject scoreTuple=jsonScores.getJSONObject(i);
            Quiz quiz=new Quiz(scoreTuple.getInt("id"),scoreTuple.getString("name"));
            highScores.put(quiz,scoreTuple.getInt("score"));
        }
    }
}
