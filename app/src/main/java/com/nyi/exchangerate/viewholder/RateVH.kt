package com.nyi.exchangerate.viewholder

import android.view.View
import com.nyi.exchangerate.vos.RateItemVO
import kotlinx.android.synthetic.main.item_rate.view.*

class RateVH(itemView: View, controller: ControllerRateItem) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {


    lateinit var item: RateItemVO


    init {

        itemView.setOnClickListener {
            controller.onClickRateItem(item)
        }
    }

    fun bindData(item: RateItemVO){
        this.item = item

        itemView.tvItemCountry.text = item.country
        itemView.tvItemRate.text = item.rate

    }

    interface ControllerRateItem{
        fun onClickRateItem(rateItem : RateItemVO)
    }
}