package com.knz21.recyclerviewplayground.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.knz21.recyclerviewplayground.EventBus
import com.knz21.recyclerviewplayground.ViewNameHolder
import com.knz21.recyclerviewplayground.databinding.ActivityMainBinding
import com.knz21.recyclerviewplayground.entity.Entity
import com.knz21.recyclerviewplayground.event.ItemViewTap
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {

        private const val itemSize = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = Adapter(lifecycleScope).apply { submitList(entities) }

        lifecycleScope.launch {
            EventBus.events<ItemViewTap>().collect { event ->
                entities = entities.map {
                    if (it.id == event.id) it.copy(selected = !it.selected) else it
                }
                (binding.list.adapter as Adapter).submitList(entities)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ViewNameHolder.names.clear()
    }

    private var entities: List<Entity> = mutableListOf<Entity>().also { list ->
        repeat(itemSize) { index -> list.add(Entity(index.toString())) }
    }
}