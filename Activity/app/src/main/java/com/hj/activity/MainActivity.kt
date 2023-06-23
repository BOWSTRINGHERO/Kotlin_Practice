package com.hj.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hj.activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, SubActivity::class.java)
        intent.putExtra("from1", "Hello Bundle")
        intent.putExtra("from2",2023)

        binding.btnStart.setOnClickListener{ startActivity(intent) }

    }
}