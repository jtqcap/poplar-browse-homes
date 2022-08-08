package com.poplarhomes.browsehomes.ui.main

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.poplarhomes.browsehomes.GetBuildingsQuery
import com.poplarhomes.browsehomes.R
import com.poplarhomes.browsehomes.databinding.ItemBuildingBinding
import com.poplarhomes.browsehomes.ext.price
import com.squareup.picasso.Picasso

class BuildingsAdapter : RecyclerView.Adapter<BuildingsAdapter.ViewHolder>() {

    private val items = mutableListOf<GetBuildingsQuery.Building?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBuildingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.size

    fun setItems(buildings: List<GetBuildingsQuery.Building?>) {
        this.items.clear()
        this.items.addAll(buildings)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemBuildingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(building: GetBuildingsQuery.Building?, position: Int) {
            building?.let {
                val rent = building.rent ?: 0.0

                Picasso.get()
                    .load(building.imageUrl)
                    .fit()
                    .centerCrop()
                    .into(binding.imageBuilding)
                binding.textBuildingType.text = context.getString(
                    R.string.x_bedroom_building,
                    building.beds
                )
                binding.textRentPrice.text = rent.price
                binding.textRentPrice.setTextColor(
                    context.getColor(
                        if (position % 2 == 0) R.color.mine
                        else R.color.sunset
                    )
                )
                binding.viewStrikethrough.isVisible = position % 2 != 0
                binding.textOldPrice.text = (rent * 0.1).price
                binding.textOldPrice.isVisible = position % 2 != 0
                binding.textAddress.text = Html.fromHtml(
                    context.getString(R.string.space_x, building.address),
                    Html.FROM_HTML_MODE_LEGACY
                )
            }
        }

    }

}
