package com.example.intellilearnteacherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_view_attendance.*

class ViewAttendance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_attendance)

        Toast.makeText(this@ViewAttendance, intent.getStringExtra("selectedClass").toString(), Toast.LENGTH_LONG).show()

        tvTextView.text = intent.getStringExtra("selectedClass").toString()

    }
}