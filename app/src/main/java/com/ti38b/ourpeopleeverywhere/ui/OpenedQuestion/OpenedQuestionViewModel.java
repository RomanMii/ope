package com.ti38b.ourpeopleeverywhere.ui.OpenedQuestion;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ti38b.ourpeopleeverywhere.dataBase.ApiClient;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;
import com.ti38b.ourpeopleeverywhere.dataBase.answer.Answer;
import com.ti38b.ourpeopleeverywhere.dataBase.answer.AnswerDAO;

import java.util.ArrayList;

public class OpenedQuestionViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Answer>> answerList;
    private ArrayList<Answer> answerArrayList;

    public OpenedQuestionViewModel() {
        Log.i("answersRead", "viewModel created");
        answerList = new MutableLiveData<>();
    }


    public void readAnswers(int questionId, int userId){
        Call<ApiResponse> call = ApiClient.getApiClient()
                .create(AnswerDAO.class)
                .readAnswers(questionId,userId);

        Log.i("answersRead","start - questionId = " + questionId + " userId = " + userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code() == 200){
                    Log.i("answersRead", "response code: " + response.code() + " questionId = " + questionId);
                    if(response.body().getStatus().equals("ok")){
                        answerArrayList = response.body().getAnswers();
                        Log.i("answersRead","answerList: " + answerArrayList);
                        answerList.setValue(answerArrayList);
                    }else{
                        Log.e("answersRead", response.body().getStatus());
                    }
                }else{
                    Log.e("answersRead", "response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("answersRead", "onFailure " + t.getMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<Answer>> getAnswerList() {
        Log.e("answersRead", "to return list " + answerList);
        return answerList;
    }
}
