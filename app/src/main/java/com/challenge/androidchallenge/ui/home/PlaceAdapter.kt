package com.challenge.androidchallenge.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.androidchallenge.R
import com.challenge.androidchallenge.entity.PlacesModel
import com.challenge.androidchallenge.repository.utils.loadImageUrlPicasso
import kotlinx.android.synthetic.main.row_place.view.*

class PlaceAdapter(var onClickItem: ((PlacesModel) -> Unit)? = null) :
    RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    var parameterList: List<PlacesModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_place,parent,false))
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val model = parameterList[position]
        holder.itemView.textRowName.text = model.name
        holder.itemView.textRowZone.text = model.timezone
        if (model.categories.isNotEmpty())holder.itemView.imgRow.loadImageUrlPicasso("${model.categories[0].icon.prefix}${model.categories[0].icon.suffix}}")
        holder.itemView.setOnClickListener { onClickItem?.invoke(model) }
    }

    override fun getItemCount(): Int = parameterList.size

    class PlaceViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)
}