package com.example.intellilearnteacherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//an activity to let the teacher choose the class for checking the performance
class SelectClassForPerformanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_performance_type)
    }
}