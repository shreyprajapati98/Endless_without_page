package com.demo.endless_without_page.rest;

import com.demo.endless_without_page.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("posts")
    Call<List<Message>> getMessageDetails();
}
