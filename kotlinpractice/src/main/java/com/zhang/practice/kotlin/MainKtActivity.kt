package com.zhang.practice.kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainKtActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kt)

        findViewById<TextView>(R.id.tv_text).setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show()
        })
    }
}