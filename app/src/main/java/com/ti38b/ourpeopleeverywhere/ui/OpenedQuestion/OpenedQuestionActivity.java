package com.ti38b.ourpeopleeverywhere.ui.OpenedQuestion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ti38b.ourpeopleeverywhere.R;
import com.ti38b.ourpeopleeverywhere.apputil.AppConfig;
import com.ti38b.ourpeopleeverywhere.dataBase.answer.Answer;
import com.ti38b.ourpeopleeverywhere.dataBase.question.Question;
import com.ti38b.ourpeopleeverywhere.databinding.ActivityOpenedQuestionBinding;
import com.ti38b.ourpeopleeverywhere.viewModels.AddAnswerViewModel;

import java.util.ArrayList;

public class OpenedQuestionActivity extends AppCompatActivity {

    private Context context;
    private OpenedQuestionViewModel viewModel;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AppConfig appConfig;
    private AnswerRecyclerViewAdapter answerRecyclerViewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Question question = intent.getParcelableExtra("Question");

        ActivityOpenedQuestionBinding activityOpenedQuestionBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_opened_question);

        activityOpenedQuestionBinding.setViewModel(new AddAnswerViewModel(question));
        activityOpenedQuestionBinding.executePendingBindings();

        viewModel = new ViewModelProvider(this).get(OpenedQuestionViewModel.class);
        appConfig = new AppConfig(this);

        context = this;

        recyclerView = findViewById(R.id.openedQuestionOutList);
        swipeRefreshLayout = findViewById(R.id.answerListSwipeRefreshLayout);
        viewModel.readAnswers(question.getId(), appConfig.getUserId());
        viewModel.getAnswerList().observe(this,answerUpdateObserver);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.readAnswers(question.getId(), appConfig.getUserId());
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    Observer<ArrayList<Answer>> answerUpdateObserver = new Observer<ArrayList<Answer>>() {
        @Override
        public void onChanged(ArrayList<Answer> answers) {
            Log.i("questionRead","activity on change (answer list length = " + answers.size() + ")");
            answerRecyclerViewAdapter = new AnswerRecyclerViewAdapter(context,answers);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(answerRecyclerViewAdapter);
        }
    };
}