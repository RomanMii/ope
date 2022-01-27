package com.ti38b.ourpeopleeverywhere.ui.home;

import android.content.Context;
import android.content.Intent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ti38b.ourpeopleeverywhere.apputil.AppConfig;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiClient;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;
import com.ti38b.ourpeopleeverywhere.dataBase.like.LikeDAO;
import com.ti38b.ourpeopleeverywhere.ui.OpenedQuestion.OpenedQuestionActivity;
import com.ti38b.ourpeopleeverywhere.R;
import com.ti38b.ourpeopleeverywhere.dataBase.question.Question;
import com.ti38b.ourpeopleeverywhere.ui.RecyclerViewViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Question> questionArrayList;
    private AppConfig appConfig;

    public QuestionRecyclerViewAdapter(Context context, ArrayList<Question> questionArrayList) {
        this.context = context;
        this.questionArrayList = questionArrayList;
        appConfig = new AppConfig(context);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.block_question_item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Question question = questionArrayList.get(position);
        RecyclerViewViewHolder viewViewHolder = (RecyclerViewViewHolder)holder;

        viewViewHolder.author.setText(question.getAuthor());
        viewViewHolder.text.setText(question.getQuestion_text());
        viewViewHolder.rating.setText(question.getRating() + "");
        viewViewHolder.date.setText(question.getCreate_date());
        viewViewHolder.likeButtonPressed = question.getIsLikedByUser();

        if(viewViewHolder.likeButtonPressed){
            viewViewHolder.like.setBackgroundResource(R.drawable.liked);
        }else{
            viewViewHolder.like.setBackgroundResource(R.drawable.like);
        }

        viewViewHolder.like.setOnClickListener(v -> {
            if(appConfig.isUserLogin()){
                if(viewViewHolder.likeButtonPressed){
                    dislike(question.getId(), appConfig.getUserId());
                    viewViewHolder.likeButtonPressed = false;
                    viewViewHolder.like.setBackgroundResource(R.drawable.like);
                }else{
                    like(question.getId(), appConfig.getUserId());
                    viewViewHolder.likeButtonPressed = true;
                    viewViewHolder.like.setBackgroundResource(R.drawable.liked);
                }
            }
        });

        viewViewHolder.text.setOnClickListener(v -> {
            Intent intent = new Intent(context, OpenedQuestionActivity.class);
            intent.putExtra("Question",question);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }

    public void like(int questionId, int userId){
        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(LikeDAO.class)
                .likeQuestion(questionId,userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i("likeQuestion","response code " + response.code());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i("likeQuestion","Fail");
            }
        });
    }

    public void dislike(int questionId, int userId){
        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(LikeDAO.class)
                .dislikeQuestion(questionId, userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i("dislikeQuestion","response code " + response.code());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i("dislikeQuestion","Fail");
            }
        });
    }

}
