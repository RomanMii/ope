package com.ti38b.ourpeopleeverywhere.dataBase;

import com.google.gson.annotations.SerializedName;
import com.ti38b.ourpeopleeverywhere.dataBase.answer.Answer;
import com.ti38b.ourpeopleeverywhere.dataBase.question.Question;
import com.ti38b.ourpeopleeverywhere.dataBase.users.User;

import java.util.ArrayList;

public class ApiResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("username")
    private String username;

    @SerializedName("id")
    private int id;

    @SerializedName("user")
    private User user;

    @SerializedName("email")
    private String email;

    @SerializedName("questions_list")
    private ArrayList<Question> questions;

    @SerializedName("answers_list")
    private ArrayList<Answer> answers;

    public String getStatus(){
        return status;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public ArrayList<Answer> getAnswers(){
        return answers;
    }
}
