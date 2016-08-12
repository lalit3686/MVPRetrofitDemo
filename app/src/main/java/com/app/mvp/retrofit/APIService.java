package com.app.mvp.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    /**
     * https://api.stackexchange.com/search?order=desc&sort=activity&tagged=android&site=stackoverflow
     * @param order
     * @param sort
     * @param site
     * @param tags
     * @return
     */
    @GET("/search?")
    Call<StackOverflowQuestions> loadQuestions(@Query("order") String order, @Query("sort") String sort, @Query("tagged") String tags, @Query("site") String site);

    /**
     * http://www.thomas-bayer.com/sqlrest/CUSTOMER/
     * @return
     */
    @GET("/sqlrest/CUSTOMER")
    Call<CustomerList> loadCustomers();
}