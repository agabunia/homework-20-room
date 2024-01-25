package com.example.homework_20_rooms.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework_20_rooms.R
import com.example.homework_20_rooms.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}