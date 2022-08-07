package com.poplarhomes.browsehomes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poplarhomes.browsehomes.databinding.ItemSearchFilterBinding

class SearchFiltersAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<SearchFiltersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchFilterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(
        private val binding: ItemSearchFilterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(filter: String) {
            binding.textFilter.text = filter
        }

    }

}
