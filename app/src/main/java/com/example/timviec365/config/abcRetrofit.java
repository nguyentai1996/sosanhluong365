package com.example.timviec365.config;


import com.example.timviec365.config.InterfaceAPI;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class abcRetrofit {
    public static InterfaceAPI postService;

    public static InterfaceAPI getInstance() {
        if (postService == null) {
            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://www.flickr.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(InterfaceAPI.class);
        }
        return postService;
    }
}
