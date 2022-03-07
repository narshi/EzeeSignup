package com.ezeetapAssignment.ezeesignup.network

import com.ezeetapAssignment.ezeesignup.model.UIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface Services {
    @Headers("Content-Type: application/json")
    @GET("/mobileapps/{uiJson}")
    fun getUiData(
        @Path("uiJson") jsonPath:String
    ): Call<UIResponse>
}