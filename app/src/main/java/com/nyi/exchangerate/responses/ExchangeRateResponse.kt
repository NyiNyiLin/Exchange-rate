package com.nyi.exchangerate.responses

import com.google.gson.annotations.SerializedName
import com.nyi.exchangerate.vos.RateVO

class ExchangeRateResponse(
    @SerializedName("info")
    var info : String,

    @SerializedName("description")
    var description : String,

    @SerializedName("timestamp")
    var timestamp : Long,

    @SerializedName("rates")
    var rates : RateVO
) {
}