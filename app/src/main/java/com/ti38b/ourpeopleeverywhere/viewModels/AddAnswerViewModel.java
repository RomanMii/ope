package com.ti38b.ourpeopleeverywhere.viewModels;

import android.content.Intent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.ti38b.ourpeopleeverywhere.apputil.AppConfig;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiClient;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;
import com.ti38b.ourpeopleeverywhere.dataBase.answer.AnswerDAO;
import com.ti38b.ourpeopleeverywhere.dataBase.question.Question;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddAnswerViewModel extends ViewModel {


    private Question question;
    public ObservableField<String> textField = new ObservableField<>();


    public AddAnswerViewModel(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAuthor() {
        return question.getAuthor();
    }

    public String getQuestionText() {
        return question.getQuestion_text();
    }

    public String getDate() {
        return question.getCreate_date();
    }

    public String getRating() {
        return question.getRating() + "";
    }

    public void setQuestion(Question question) {
        this.question = question;
    }



    public void onAddAnswerButtonClick(View view){

        AppConfig appConfig = new AppConfig(view.getContext());
        if(appConfig.isUserLogin()){
            String text = textField.get();

            int user_id = appConfig.getUserId();

            LocalDate localDate = LocalDate.now();
            DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = dateFormater.format(localDate);

            int question_id = question.getId();

            Call<ApiResponse> call = ApiClient.getApiClient()
                    .create(AnswerDAO.class)
                    .addAnswer(text,user_id,date,question_id);

            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    Log.i("addAnswer","response code " + response.code());
                    if(response.code() == 200){
                        Log.i("addAnswer","status " + response.body().getStatus());

                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.e("addAnswer","Failed");
                }
            });
        }
        textField.set("");

    }
}
