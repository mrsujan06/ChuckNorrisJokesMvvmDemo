package com.example.mvvmdemo.model;

import io.reactivex.Observable;

public interface JokesInteractor {
    Observable<Response> getJokes();

}
