package com.example.timviec365.config;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobRetrofit {
    public static InterfaceAPI postService;

    public static InterfaceAPI getInstance() {
        if (postService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://timviec365.vn")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(InterfaceAPI.class);
        }
        return postService;
    }
}
