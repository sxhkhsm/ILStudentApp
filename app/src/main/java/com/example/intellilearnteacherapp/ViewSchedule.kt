package com.example.intellilearnteacherapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intellilearnteacherapp.models.TeacherAttendanceItem
import com.example.intellilearnteacherapp.models.TeacherScheduleItem
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import storage.SharedPrefManager

class ViewSchedule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_schedule)

        val teacherID = SharedPrefManager.getInstance(this@ViewSchedule).teacher.teacher_ID

        MyApp.getInstance().getApiServices().getTeacherSchedule(teacherID)
            .enqueue(object: Callback<List<TeacherScheduleItem>> {
                override fun onResponse(

                    call: Call<List<TeacherScheduleItem>>,
                    response: Response<List<TeacherScheduleItem>>

                )
                {

                    if(response.isSuccessful && !response.body().isNullOrEmpty()){

                        val scheduleList: List<TeacherScheduleItem>? = response.body()
                        Log.e("resp", response.body().toString())

                        //create the table
                        val recyclerView = findViewById<RecyclerView>(R.id.schedule_table)
                        val adapter = scheduleList?.let { TeacherScheduleAdapter(it) }
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(this@ViewSchedule)


                    }
                    else {

                        Toast.makeText(applicationContext, "No schedule data!", Toast.LENGTH_LONG).show()

                    }

                }

                override fun onFailure(call: Call<List<TeacherScheduleItem>>, t: Throwable) {

                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }


            })



    }
}