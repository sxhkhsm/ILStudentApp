package com.example.intellilearnteacherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_navigation_screen.*

class NavigationScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_screen)

        btnShowMcqList.setOnClickListener(){

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}