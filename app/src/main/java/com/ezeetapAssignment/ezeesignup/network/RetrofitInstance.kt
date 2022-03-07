package com.ezeetapAssignment.ezeesignup.network

import com.ezeetapAssignment.ezeesignup.model.UIResponse
import com.ezeetapAssignment.ezeesignup.model.Uidata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitInstance {
    fun getDynamicUI(json: String, onResult: (UIResponse?) -> Unit) {
        val apiIntegration = RetrofitBuilder.buildService(Services::class.java)
        apiIntegration.getUiData(json).enqueue(
            object : Callback<UIResponse> {
                override fun onResponse(call: Call<UIResponse>, response: Response<UIResponse>) {
                    val addUi = response.body()
                    onResult(addUi)
                }

                override fun onFailure(call: Call<UIResponse>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }
}