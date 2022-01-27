package com.ti38b.ourpeopleeverywhere.dataBase.answer;

import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AnswerDAO {
    @FormUrlEncoded
    @POST("read_answer.php")
    Call<ApiResponse> readAnswers(@Field("id") int id,
                                  @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("add_answer.php")
    Call<ApiResponse> addAnswer(@Field("text") String text,
                                  @Field("user_id") int user_id,
                                  @Field("date") String date,
                                  @Field("question_id") int question_id);
}
