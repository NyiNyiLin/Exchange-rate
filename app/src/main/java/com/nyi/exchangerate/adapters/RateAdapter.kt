package com.nyi.exchangerate.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nyi.exchangerate.R
import com.nyi.exchangerate.viewholder.RateVH
import com.nyi.exchangerate.vos.RateItemVO

class RateAdapter(val itemList : List<RateItemVO>, val controller: RateVH.ControllerRateItem) : RecyclerView.Adapter<RateVH>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RateVH {
        val v = LayoutInflater.from(p0.getContext()).inflate(R.layout.item_rate, p0, false)
        return RateVH(v, controller)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(p0: RateVH, p1: Int) {
        p0.bindData(itemList.get(p1))
    }

}