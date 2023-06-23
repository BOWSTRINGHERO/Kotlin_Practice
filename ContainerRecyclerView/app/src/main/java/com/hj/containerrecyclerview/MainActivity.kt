package com.hj.containerrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hj.containerrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data: MutableList<Memo> = loadData()
        var adapter = CustomAdapter()
        adapt.listData = data
        binding.recyclerView = data
        binding.reccyclerView.layoutManager = ListenLayoutManager(this)
    }
        fun loadData(): MutableList<Memo>{
            val data: MutableList<Memo> = mutableListOf()
            for (no in 1..100) {
                val title = "이것이 안드로이드다 $(no)"
                val date = System.currentTimeMillis()
                var memo = Memo(no, title, date)
                data.add(memo)
            }
            return data
        }
    }
}