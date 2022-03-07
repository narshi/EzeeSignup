package com.ezeetapAssignment.ezeesignup.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class LoginInfo{
    var name : String=""
    var pNo : Int
    var city : String=""

    constructor(name: String, pNo: Int, city: String) {
        this.name = name
        this.pNo = pNo
        this.city = city
    }
}