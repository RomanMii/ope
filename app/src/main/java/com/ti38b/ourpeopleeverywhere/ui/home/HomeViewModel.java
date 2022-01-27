package com.ti38b.ourpeopleeverywhere.ui.home;

import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ti38b.ourpeopleeverywhere.apputil.AppConfig;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiClient;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;
import com.ti38b.ourpeopleeverywhere.dataBase.question.Question;
import com.ti38b.ourpeopleeverywhere.dataBase.question.QuestionsDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Question>> questionsList;
    private ArrayList<Question> questionArrayList;
    private int id = 0;
    public ObservableField<String> textField = new ObservableField<>();

    public HomeViewModel() {
        questionsList = new MutableLiveData<>();
        readQuestions(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void readQuestions(int userId){
        Call<ApiResponse> call = ApiClient.getApiClient()
                .create(QuestionsDAO.class)
                .readQuestions(userId);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals("ok")){
                        questionArrayList = response.body().getQuestions();
                        Log.i("questionList", questionArrayList + "");
                        questionsList.setValue(questionArrayList);
                    }else{
                        Log.e("questionRead", response.body().getStatus());
                    }
                }else{
                    Log.e("questionRead", "response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("questionRead", "onFailure " + t.getMessage());
            }
        });
    }

    public void onAddQuestionButtonClick(View view){
        Log.i("addQuestions","add question onClick");

        AppConfig appConfig = new AppConfig(view.getContext());
        if(appConfig.isUserLogin()){

            String text = textField.get();
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = dateFormater.format(localDate);
            int user_id = appConfig.getUserId();

            Call<ApiResponse> call = ApiClient.getApiClient()
                    .create(QuestionsDAO.class)
                    .addQuestion(text,date,user_id);

            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    Log.i("addQuestion","response code " + response.code());
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.e("addQuestion","Failed");
                }
            });
        }
        textField.set("");

    }

    public MutableLiveData<ArrayList<Question>> getQuestionsList() {
        return questionsList;
    }
}