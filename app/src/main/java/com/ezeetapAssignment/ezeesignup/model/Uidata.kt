package com.ezeetapAssignment.ezeesignup.model

import com.google.gson.annotations.SerializedName

data class Uidata (
    @SerializedName("uitype") val uitype : String,
    @SerializedName("value") val value : String,
    @SerializedName("key") val key : String,
    @SerializedName("hint") val hint : String
)