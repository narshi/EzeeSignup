package com.ezeetapAssignment.ezeesignup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezeetapAssignment.ezeesignup.model.LoginInfo

class DisplayViewModel:ViewModel {

    constructor():super()

    var userDetailsDisplay = MutableLiveData<String>()

    fun displayData(userDetails : LoginInfo){
        if (userDetails!=null){
            userDetailsDisplay.postValue("Success")
        }else{
            userDetailsDisplay.postValue("Failed")
        }
    }

}