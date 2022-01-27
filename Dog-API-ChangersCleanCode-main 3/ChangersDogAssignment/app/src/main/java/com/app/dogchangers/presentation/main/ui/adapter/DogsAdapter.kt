package com.app.dogchangers.presentation.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.dogchangers.R
import com.app.dogchangers.databinding.ItemDogsBinding
import com.app.dogchangers.domain.models.dog_model.Dogs


class DogsAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList: List<Dogs> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return InfoSectionViewHolder(
            ItemDogsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    open fun setData(dataList: List<Dogs>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as InfoSectionViewHolder).bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class InfoSectionViewHolder(
        private val itemViewBinding: ItemDogsBinding
    ) : RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(item: Dogs) {
            itemViewBinding.ivDogImage.load(item.dogImage) {
                crossfade(true)
                placeholder(R.drawable.placeholder_dog)
            }
        }
    }
}