package com.ti38b.ourpeopleeverywhere.dataBase.answer;

public class Answer {

    private int id;
    private String answer_text;
    private String author;
    private String create_date;
    private int question_id;
    private int rating;
    private int liked;

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean getIsLikedByUser() {
        if(liked == 1){
            return true;
        }else{
            return false;
        }
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }
}
