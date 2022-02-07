package com.challenge.androidchallenge.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.androidchallenge.R
import com.challenge.androidchallenge.entity.ModelGeneric
import kotlinx.android.synthetic.main.row_detail.view.*

class DetailAdapter :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    var parameterList: List<ModelGeneric> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_detail,parent,false))
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val model = parameterList[position]
        holder.itemView.textRowValueDetail.text = model.key
        holder.itemView.textRowKeyDetail.text = model.value
    }


    override fun getItemCount(): Int = parameterList.size


    class DetailViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)
}