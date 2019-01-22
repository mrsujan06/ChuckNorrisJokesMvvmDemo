package com.example.mvvmdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvvmdemo.model.Response;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesAdapterViewHolder> {

    Response mResponse;
    Context mContext;

    public JokesAdapter(Response mApiResponse, Context mContext) {
        this.mResponse = mApiResponse;
        this.mContext = mContext;
    }

    @Override
    public JokesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_jokes, parent, false);
        return new JokesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokesAdapterViewHolder holder, int position) {
        holder.jokes.setText(mResponse.getValue().get(position).getJoke());
    }


    @Override
    public int getItemCount() {
        return mResponse.getValue().size();
    }

    class JokesAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView jokes;

        public JokesAdapterViewHolder(View itemView) {
            super(itemView);
            jokes = itemView.findViewById(R.id.jokestv);

        }

    }

}
