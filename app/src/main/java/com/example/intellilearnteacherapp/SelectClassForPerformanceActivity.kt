package com.example.intellilearnteacherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.intellilearnteacherapp.models.ClassModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import storage.SharedPrefManager
import com.google.gson.reflect.TypeToken
import kotlin.math.log

//an activity to let the teacher choose the class for checking the performance
class SelectClassForPerformanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_class_for_performance)

        val buttonContainer = findViewById<LinearLayout>(R.id.button_container)

        MyApp.getInstance().getApiServices().getTeacherClasses(SharedPrefManager.getInstance(applicationContext).teacher.teacher_ID).enqueue(object :
            Callback<List<ClassModel>> {

            override fun onResponse(call: Call<List<ClassModel>>, response: Response<List<ClassModel>>) {

                if (response.isSuccessful && response.body() != null) {

                    val classModelList = response.body()!!

                    if (classModelList.isNotEmpty()) {

                        val classesList = mutableListOf<String>()

                        // iterate over the classModelList to add the class names to the new list
                        for (classModel in classModelList) {
                            classesList.add("${classModel.subject}  -  ${classModel.class_level}(${classModel.section})")
                            Log.e("className", "${classModel.subject}-${classModel.section}")
                        }

                        for (className in classesList) {
                            val button = Button(this@SelectClassForPerformanceActivity).apply {
                                layoutParams = LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                                ).apply {
                                    setMargins(0, 0, 0, 16)
                                }
                                backgroundTintList = ContextCompat.getColorStateList(
                                    this@SelectClassForPerformanceActivity,
                                    R.color.button_background_color
                                )
                                text = className
                                setTextColor(
                                    ContextCompat.getColor(
                                        this@SelectClassForPerformanceActivity,
                                        android.R.color.white
                                    )
                                )
                                textSize = 18f
                            }

                            button.setOnClickListener {
                                // Handle the button click event
                            }

                            buttonContainer.addView(button)
                        }
                    } else {
                        // Show a message to the user that there are no classes available
                        Toast.makeText(
                            this@SelectClassForPerformanceActivity,
                            "No classes available for this teacher",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    // Show a message to the user that there was an error loading the classes
                    Toast.makeText(
                        this@SelectClassForPerformanceActivity,
                        "Error loading classes for this teacher",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<ClassModel>>, t: Throwable) {
                // Show a message to the user that there was an error loading the classes
                Toast.makeText(
                    this@SelectClassForPerformanceActivity,
                    "Error loading classes for this teacher",
                    Toast.LENGTH_LONG
                ).show()
            }
        })


        // Replace this with the actual API response list


//        val classesList = listOf("Class 1 Section A", "Class 1 Section B", "Class 2 Section A")


    }
}
