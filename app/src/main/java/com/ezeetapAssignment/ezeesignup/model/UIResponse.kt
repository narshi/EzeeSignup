package com.ezeetapAssignment.ezeesignup.model

import com.google.gson.annotations.SerializedName

class UIResponse (
    @SerializedName("logo-url") val logo : String,
    @SerializedName("heading-text") val heading : String,
    @SerializedName("uidata") val uidata : List<Uidata>
)