package com.ti38b.ourpeopleeverywhere.dataBase.like;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LikeDAO {

    @FormUrlEncoded
    @POST("like_question.php")
    Call<ApiResponse> likeQuestion(@Field("question_id") int answer_id,
                                   @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("dislike_question.php")
    Call<ApiResponse> dislikeQuestion(@Field("question_id") int answer_id,
                                      @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("like_answer.php")
    Call<ApiResponse> likeAnswer(@Field("answer_id") int answer_id,
                                 @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("dislike_answer.php")
    Call<ApiResponse> disLikeAnswer(@Field("answer_id") int answer_id,
                                    @Field("user_id") int user_id);
}
