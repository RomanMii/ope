package com.ti38b.ourpeopleeverywhere.ui.OpenedQuestion;

import android.content.Context;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ti38b.ourpeopleeverywhere.R;
import com.ti38b.ourpeopleeverywhere.apputil.AppConfig;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiClient;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;
import com.ti38b.ourpeopleeverywhere.dataBase.answer.Answer;
import com.ti38b.ourpeopleeverywhere.dataBase.like.LikeDAO;
import com.ti38b.ourpeopleeverywhere.ui.RecyclerViewViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AnswerRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Answer> answerArrayList;
    private AppConfig appConfig;

    public AnswerRecyclerViewAdapter(Context context, ArrayList<Answer> answerArrayList) {
        this.context = context;
        this.answerArrayList = answerArrayList;
        appConfig = new AppConfig(context);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.block_question_item, parent, false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Answer answer = answerArrayList.get(position);
        RecyclerViewViewHolder viewViewHolder = (RecyclerViewViewHolder)holder;

        viewViewHolder.author.setText(answer.getAuthor());
        viewViewHolder.text.setText(answer.getAnswer_text());
        viewViewHolder.rating.setText(answer.getRating() + "");
        viewViewHolder.date.setText(answer.getCreate_date());
        viewViewHolder.likeButtonPressed = answer.getIsLikedByUser();

        if(viewViewHolder.likeButtonPressed){
            viewViewHolder.like.setBackgroundResource(R.drawable.liked);
        }else{
            viewViewHolder.like.setBackgroundResource(R.drawable.like);
        }

        viewViewHolder.like.setOnClickListener(v -> {
            if(appConfig.isUserLogin()){
                if(viewViewHolder.likeButtonPressed){
                    dislike(answer.getId(), appConfig.getUserId());
                    viewViewHolder.likeButtonPressed = false;
                    viewViewHolder.like.setBackgroundResource(R.drawable.like);
                }else{
                    like(answer.getId(), appConfig.getUserId());
                    viewViewHolder.likeButtonPressed = true;
                    viewViewHolder.like.setBackgroundResource(R.drawable.liked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return answerArrayList.size();
    }

    public void like(int answerId, int userId){
        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(LikeDAO.class)
                .likeAnswer(answerId,userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i("likeAnswer","response code " + response.code());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i("likeAnswer","Fail");
            }
        });
    }

    public void dislike(int answerId, int userId){
        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(LikeDAO.class)
                .disLikeAnswer(answerId,userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i("dislikeAnswer","response code " + response.code());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i("dislikeAnswer","Fail");
            }
        });
    }
}
