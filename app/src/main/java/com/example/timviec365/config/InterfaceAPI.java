package com.example.timviec365.config;


import com.example.timviec365.model.Categories;
import com.example.timviec365.model.DataCompany;
import com.example.timviec365.model.DataCompanyNumberOne;
import com.example.timviec365.model.DataNews;
import com.example.timviec365.model.DataSearchSalary;
import com.example.timviec365.model.Datachart;
import com.example.timviec365.model.Datacolumn;
import com.example.timviec365.model.ExmapleMain;
import com.example.timviec365.model.GetPost;
import com.example.timviec365.model.abc;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Windows 10 Now on 11/16/2017.
 */

public interface InterfaceAPI {
    @GET("services/rest/")
    Call<ExmapleMain> getAllData(@Query("api_key") String api_key, @Query("user_id") String user_id,
                                 @Query("format") String format, @Query("nojsoncallback") int nojsoncallback,
                                 @Query("method") String method, @Query("extras") String extras,
                                 @Query("page") int page, @Query("per_page") int per_page);







    @FormUrlEncoded
    @POST("ssl/site/api_ajaxcomparesalary")
    Call<Datachart> getDatacolumn(@FieldMap Map <String, String> param);

    @FormUrlEncoded
    @POST("ssl/site/api_listfiltercv")
    Call<List<DataCompany>> getDataCompany(@FieldMap Map <String, String> getDataCompany);

    @FormUrlEncoded
    @POST("ssl/site/AjaxResultEstimateSalary")
    Call<DataSearchSalary> getDataSearchSalary(@FieldMap Map <String, String> getdataSearchSalary);


    @FormUrlEncoded
    @POST("ssl/site/ajax_cv_luong")
    Call<List<DataCompanyNumberOne>> getDataCompanyNumberOne(@FieldMap Map <String, String> getDataCompanyNumberOne);


    @GET("ssl/site/ajax_tintuc_luong")
    Call<List<DataNews>> getDataNews();
}
