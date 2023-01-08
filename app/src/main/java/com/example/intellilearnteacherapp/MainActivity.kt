package com.example.intellilearnteacherapp

import android.app.Activity
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mcqItemAdapter = AdapterMcqItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mcqItemAdapter

        faAddNewMcq.setOnClickListener(){
            //Toast.makeText(this, "Clicked", Toast.LENGTH_LONG)
            AddNewMcqActivity.startActivity(this)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)

        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){

            if(requestCode == AddNewMcqActivity.REQUEST_CODE_ADD_MCQ){


                data?.let {

                    val item = data.getParcelableExtra<McqItem>(AddNewMcqActivity.EXTRA_MCQ)
                    mcqItemAdapter.addItem(item!!)

                }


            }
        }
    }
}