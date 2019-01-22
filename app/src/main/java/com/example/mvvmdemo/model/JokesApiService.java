package com.example.mvvmdemo.model;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface JokesApiService {
    @GET("/jokes")
    Observable<Response> getJokes();
}
