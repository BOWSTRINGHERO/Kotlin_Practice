package com.hj.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hj.activity.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySubBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.to1.text = intent.getStringExtra("from1")
        binding.to2.text = "${intent.getStringExtra(" from2 ", 0)}"



    }
}