package com.ti38b.ourpeopleeverywhere.dataBase.question;

import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionsDAO {

    @FormUrlEncoded
    @POST("read_question.php")
    Call<ApiResponse> readQuestions(@Field("user_id") int id);

    @FormUrlEncoded
    @POST("add_question.php")
    Call<ApiResponse> addQuestion(@Field("text") String text,
                                  @Field("date") String date,
                                  @Field("user_id") int id);
}
