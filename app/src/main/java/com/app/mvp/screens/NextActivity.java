package com.app.mvp.screens;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.app.mvp.adapter.StackOverflowQuestionAdapter;
import com.app.mvp.demo.R;
import com.app.mvp.retrofit.Question;
import com.app.mvp.retrofit.StackOverflowAPI;
import com.app.mvp.retrofit.StackOverflowQuestions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NextActivity extends AppCompatActivity implements Callback<StackOverflowQuestions> {

    private static final String TAG = NextActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private StackOverflowQuestionAdapter adapter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        initComponents();
        showDialog();
        requestQuestionsList();
    }

    private void initComponents(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void requestQuestionsList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StackOverflowAPI api = retrofit.create(StackOverflowAPI.class);
        Call<StackOverflowQuestions> questionsCall = api.loadQuestions("desc", "creation", "android", "stackoverflow");
        questionsCall.enqueue(this);
    }

    private void showDialog(){
        if(dialog == null || (!dialog.isShowing())){
            dialog = new ProgressDialog(this);
            dialog.setMessage("loading...");
            dialog.show();
        }
    }

    private void dismissDialog(){
        if(dialog != null && (dialog.isShowing())){
            dialog.dismiss();
        }
    }

    @Override
    public void onResponse(Call<StackOverflowQuestions> call, Response<StackOverflowQuestions> response) {
        if(response.body().getItems() != null){
            Log.e(TAG, response.body().getItems().toString());
            adapter = new StackOverflowQuestionAdapter(response.body().getItems());
            recyclerView.setAdapter(adapter);
        }
        dismissDialog();
    }

    @Override
    public void onFailure(Call<StackOverflowQuestions> call, Throwable t) {
        Log.e(TAG, t.getLocalizedMessage());
        dismissDialog();
    }
}
