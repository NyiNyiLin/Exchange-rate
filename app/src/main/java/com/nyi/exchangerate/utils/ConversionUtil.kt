package com.nyi.exchangerate.utils

import com.nyi.exchangerate.vos.RateItemVO
import com.nyi.exchangerate.vos.RateVO

class ConversionUtil {

    //to convert the object response to the list for display in the adapter
    //most api response as list, but central bank api is response with object

    companion object {
        fun convertExchangeRateItemToExchangeRateList(rateVO : RateVO) : ArrayList<RateItemVO>{

            var rateItemList = ArrayList<RateItemVO>()

            rateItemList.add(RateItemVO("AUD", rateVO.aud))
            rateItemList.add(RateItemVO("CHF", rateVO.chf))
            rateItemList.add(RateItemVO("EUR", rateVO.eur))
            rateItemList.add(RateItemVO("GBP", rateVO.gbp))
            rateItemList.add(RateItemVO("INR", rateVO.inr))
            rateItemList.add(RateItemVO("JPY", rateVO.jpy))
            rateItemList.add(RateItemVO("USD", rateVO.usd))
            rateItemList.add(RateItemVO("SGD", rateVO.sgd))

            return rateItemList

        }
    }
}