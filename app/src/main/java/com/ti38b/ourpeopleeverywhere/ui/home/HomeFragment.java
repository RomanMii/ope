package com.ti38b.ourpeopleeverywhere.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ti38b.ourpeopleeverywhere.R;
import com.ti38b.ourpeopleeverywhere.apputil.AppConfig;
import com.ti38b.ourpeopleeverywhere.dataBase.question.Question;
import com.ti38b.ourpeopleeverywhere.databinding.FragmentHomeBinding;


import java.util.ArrayList;

public class HomeFragment extends Fragment implements LifecycleOwner {

    Context context;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private AppConfig appConfig;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private QuestionRecyclerViewAdapter questionRecyclerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setViewModel(new HomeViewModel());

//        BlockNewQuestionBinding blockNewQuestionBinding = BlockNewQuestionBinding
//                .inflate(getLayoutInflater(),container,false);
//        blockNewQuestionBinding.setViewModel(new AddQuestionViewModel());
//        BlockNewQuestionBinding blockNewQuestionBinding = DataBindingUtil.inflate(inflater,
//                R.layout.block_new_question, binding.linearLayout,true);
//        blockNewQuestionBinding.setViewModel(new AddQuestionViewModel());

        View root = binding.getRoot();

        appConfig = new AppConfig(root.getContext());
        homeViewModel.setId(appConfig.getUserId());

        context = root.getContext();

        refreshLayout = root.findViewById(R.id.questionListSwipeRefreshLayout);
        recyclerView = root.findViewById(R.id.questionList);

        homeViewModel.readQuestions(appConfig.getUserId());
        homeViewModel.getQuestionsList().observe(getViewLifecycleOwner(),questionUpdateObserver);

        refreshLayout.setOnRefreshListener(() -> {
            homeViewModel.readQuestions(appConfig.getUserId());
            refreshLayout.setRefreshing(false);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    Observer<ArrayList<Question>> questionUpdateObserver = new Observer<ArrayList<Question>>() {
        @Override
        public void onChanged(ArrayList<Question> questions) {
            questionRecyclerAdapter = new QuestionRecyclerViewAdapter(context,questions);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(questionRecyclerAdapter);
        }
    };
}