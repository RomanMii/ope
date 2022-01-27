package com.ti38b.ourpeopleeverywhere.dataBase.question;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;


public class Question implements Parcelable {

    private int id;
    private String question_text;
    private String create_date;
    private String author;
    private int rating;
    private int liked;

    public boolean getIsLikedByUser() {
        if(liked == 1){
            return true;
        }else{
            return false;
        }
    }

    protected Question(Parcel in) {
        id = in.readInt();
        question_text = in.readString();
        create_date = in.readString();
        author = in.readString();
        rating = in.readInt();
        liked = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "\nid| " + id + " text| " + question_text +
                " author| " + author + " rating| " + rating + " liked| " + liked;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question_text);
        dest.writeString(create_date);
        dest.writeString(author);
        dest.writeInt(rating);
        dest.writeInt(liked);
    }
}
