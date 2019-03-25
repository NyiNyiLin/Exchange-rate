package com.nyi.exchangerate.vos

import com.google.gson.annotations.SerializedName

class RateVO (

    @SerializedName("USD")
    var usd : String,

    @SerializedName("AUD")
    var aud : String,

    @SerializedName("GBP")
    var gbp : String,

    @SerializedName("EUR")
    var eur : String,

    @SerializedName("INR")
    var inr : String,

    @SerializedName("JPY")
    var jpy : String,

    @SerializedName("CHF")
    var chf : String,

    @SerializedName("SGD")
    var sgd : String

    ){
}