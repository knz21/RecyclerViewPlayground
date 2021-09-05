package com.knz21.recyclerviewplayground.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.knz21.recyclerviewplayground.entity.Entity
import com.knz21.recyclerviewplayground.EventBus
import com.knz21.recyclerviewplayground.R
import com.knz21.recyclerviewplayground.databinding.ViewItemBinding
import com.knz21.recyclerviewplayground.event.ItemViewTap
import kotlinx.coroutines.launch

class Adapter(
    private val lifecycleScope: LifecycleCoroutineScope
) : ListAdapter<Entity, Adapter.ViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {
            lifecycleScope.launch {
                EventBus.post(ItemViewTap(getItem(viewHolder.bindingAdapterPosition).id))
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = getItem(position)
        holder.binding.root.itemId = entity.id
        holder.binding.text.text = entity.id
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val entity = getItem(position)
            holder.binding.text.setTextAppearance(
                if (entity.selected) R.style.TextSelected else R.style.TextNormal
            )
        }
    }

    class ViewHolder(val binding: ViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    private class ItemCallback : DiffUtil.ItemCallback<Entity>() {

        override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem.selected == newItem.selected
        }

        override fun getChangePayload(oldItem: Entity, newItem: Entity): Any {
            return true
        }
    }
}