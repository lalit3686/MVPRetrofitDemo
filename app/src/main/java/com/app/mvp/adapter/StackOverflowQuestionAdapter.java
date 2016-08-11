package com.app.mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.mvp.demo.R;
import com.app.mvp.retrofit.Question;

import java.util.List;

/**
 * Created by plalit on 8/10/2016.
 */
public class StackOverflowQuestionAdapter extends RecyclerView.Adapter<StackOverflowQuestionAdapter.MyViewHolder> {

    private List<Question> questionsList;

    public StackOverflowQuestionAdapter(List<Question> questionsList){
        this.questionsList = questionsList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText("(" + (position + 1) + ".) " + questionsList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }
}
