package com.example.mvvmdemo;

import com.example.mvvmdemo.model.JokesInteractor;
import com.example.mvvmdemo.model.Response;

import io.reactivex.Observable;
import io.reactivex.Scheduler;


public class JokesViewModel {

    private JokesInteractor mJokesInteractor;
    private Scheduler mScheduler;

    public JokesViewModel(JokesInteractor mJokesInteractor, Scheduler mScheduler) {
        this.mJokesInteractor = mJokesInteractor;
        this.mScheduler = mScheduler;
    }

    public Observable<Response> getJokes() {
        return mJokesInteractor.getJokes().observeOn(mScheduler);
    }


}
