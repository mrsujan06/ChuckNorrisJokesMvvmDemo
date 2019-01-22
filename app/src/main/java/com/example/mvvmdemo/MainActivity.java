package com.example.mvvmdemo;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.mvvmdemo.model.JokesInteractorImp;
import com.example.mvvmdemo.model.Response;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MESSAGE ->";

    JokesAdapter mJokesAdapter;
    RecyclerView mRecyclerView;
    private JokesViewModel mJokesViewModel;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mCompositeDisposable = new CompositeDisposable();
        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        mJokesViewModel = new JokesViewModel(new JokesInteractorImp(), AndroidSchedulers.mainThread());

        loadJokes();

    }

    @SuppressLint("CheckResult")
    private void loadJokes() {

        mCompositeDisposable.add(mJokesViewModel.getJokes()
                .subscribeWith(new DisposableObserver<Response>() {
                    @Override
                    public void onNext(Response response) {
                        updateUi(response);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();

                    }
                }));

    }

    private void updateUi(Response mResponse) {
        mJokesAdapter = new JokesAdapter(mResponse, this);
        mRecyclerView.setAdapter(mJokesAdapter);
    }

    @Override
    protected void onDestroy() {
        mCompositeDisposable.clear();
        mCompositeDisposable.dispose();
        Log.i(TAG, "disposed: " + mCompositeDisposable.isDisposed());
        super.onDestroy();

    }


}
