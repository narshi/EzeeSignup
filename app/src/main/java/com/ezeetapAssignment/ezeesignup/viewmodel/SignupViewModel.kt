package com.ezeetapAssignment.ezeesignup.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezeetapAssignment.ezeesignup.model.LoginInfo

class SignupViewModel:ViewModel {

    constructor(): super()

    var name =  ObservableField<String>("")
    var pNo =  ObservableField<String>("")
    var city =  ObservableField<String>("")

    var resultData = MutableLiveData<String>()

    fun sumbitData(userDetails : LoginInfo){
        if (userDetails!=null){
            resultData.postValue("Success")
        }else{
            resultData.postValue("Failed")
        }
    }

    fun getUserDetails():MutableLiveData<String>{
        return resultData
    }

}