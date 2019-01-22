package com.example.mvvmdemo.model;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokesInteractorImp implements JokesInteractor {

    JokesApiService mService;

    public JokesInteractorImp() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.icndb.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(JokesApiService.class);

    }

    @Override
    public Observable<Response> getJokes() {
        return mService.getJokes().subscribeOn(Schedulers.io());
    }
}
